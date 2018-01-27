package com.rhyno.misedirty.apis;

import com.rhyno.misedirty.apis.model.*;
import com.rhyno.misedirty.exception.NotFoundException;
import com.rhyno.misedirty.model.AirPollution;
import com.rhyno.misedirty.model.Matter;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AirPollutionApiTest {
    private static final String ANY_STATION = "any station";
    private static final String ANY_BASE_URL = "any base url";
    private static final String ANY_SERVICE_KEY = "any service key";
    private static final double ANY_VERSION = 0.3;
    private static final String ANY_DATA_TERM = "any data term";
    private static final int ANY_PAGE_NO = 0;
    private static final int ANY_NUM_OF_ROWS = 10;

    private AirPollutionApi airPollutionApi;
    private AirPollutionClient mockAirPollutionClient;

    @Before
    public void setUp() throws Exception {
        mockAirPollutionClient = mock(AirPollutionClient.class);
        airPollutionApi = new AirPollutionApi(
                ANY_BASE_URL,
                ANY_SERVICE_KEY,
                ANY_VERSION,
                ANY_DATA_TERM,
                ANY_PAGE_NO,
                ANY_NUM_OF_ROWS,
                mockAirPollutionClient);
    }

    @Test
    public void whenGetPollution_returnAirPollution() throws Exception {
        //given
        when(mockAirPollutionClient.getPollution(eq(ANY_STATION),
                eq(ANY_DATA_TERM),
                eq(ANY_PAGE_NO),
                eq(ANY_NUM_OF_ROWS),
                eq(ANY_SERVICE_KEY),
                eq(ANY_VERSION)))
                .thenReturn(AirPollutionResponse.builder()
                        .header(ResponseHeader.builder()
                                .resultCode(Status.SUCCESS)
                                .build())
                        .body(ResponseBody.builder()
                                .airs(Lists.newArrayList(
                                        Air.builder()
                                                .dataTime("2016-04-20 14:00")
                                                .pm10Value(0.3)
                                                .pm10Value24(0.5)
                                                .pm25Value(0.2)
                                                .pm25Value24(0.5)
                                                .coValue(0.1)
                                                .so2Value(0.3)
                                                .no2Value(0.2)
                                                .o3Value(0.5)
                                                .build()
                                ))
                                .build())
                        .build());

        //when
        AirPollution airPollution = airPollutionApi.getPollution("any station");

        //then
        then(mockAirPollutionClient).should().getPollution(eq("any station"),
                eq("any data term"),
                eq(0),
                eq(10),
                eq("any service key"),
                eq(0.3));

        assertThat(airPollution.getPm10()).isEqualTo(
                Matter.builder().value(0.3).predicatedValueAfter24H(0.5).build());
        assertThat(airPollution.getPm25()).isEqualTo(
                Matter.builder().value(0.2).predicatedValueAfter24H(0.5).build());
        assertThat(airPollution.getCo()).isEqualTo(Matter.builder().value(0.1).build());
        assertThat(airPollution.getSo2()).isEqualTo(Matter.builder().value(0.3).build());
        assertThat(airPollution.getNo2()).isEqualTo(Matter.builder().value(0.2).build());
        assertThat(airPollution.getO3()).isEqualTo(Matter.builder().value(0.5).build());
        assertThat(airPollution.getMeasuringTimestamp()).isEqualTo("2016-04-20 14:00");
    }

    @Test(expected = NotFoundException.class)
    public void whenResultStatusIsNotSuccess_thenThrowNotFoundException() throws Exception {
        when(mockAirPollutionClient.getPollution(eq(ANY_STATION),
                eq(ANY_DATA_TERM),
                eq(ANY_PAGE_NO),
                eq(ANY_NUM_OF_ROWS),
                eq(ANY_SERVICE_KEY),
                eq(ANY_VERSION)))
                .thenReturn(AirPollutionResponse.builder()
                        .header(ResponseHeader.builder()
                                .resultCode(Status.SERVICE_KEY_IS_NOT_REGISTERED)
                                .build())
                        .build());

        airPollutionApi.getPollution("any station");
    }

    @Test(expected = NotFoundException.class)
    public void whenReturnAirPollutionIsEmpty_thenThrowNotFoundException() throws Exception {
        when(mockAirPollutionClient.getPollution(eq(ANY_STATION),
                eq(ANY_DATA_TERM),
                eq(ANY_PAGE_NO),
                eq(ANY_NUM_OF_ROWS),
                eq(ANY_SERVICE_KEY),
                eq(ANY_VERSION)))
                .thenReturn(AirPollutionResponse.builder()
                        .header(ResponseHeader.builder()
                                .resultCode(Status.SUCCESS)
                                .build())
                        .body(ResponseBody.builder()
                                .airs(null)
                                .build())
                        .build());

        airPollutionApi.getPollution("any station");
    }
}