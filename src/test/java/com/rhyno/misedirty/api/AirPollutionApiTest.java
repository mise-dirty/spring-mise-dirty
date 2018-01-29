package com.rhyno.misedirty.api;

import com.rhyno.misedirty.api.model.*;
import com.rhyno.misedirty.exception.NotFoundException;
import com.rhyno.misedirty.model.AirPollution;
import com.rhyno.misedirty.model.Matter;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirPollutionApiTest {
    private static final String ANY_STATION = "any station";

    @Autowired
    private AirPollutionApi airPollutionApi;

    @MockBean
    private AirPollutionClient mockAirPollutionClient;

    @Test
    public void whenGetPollution_returnAirPollution() throws Exception {
        //given
        when(mockAirPollutionClient.getPollution(eq(ANY_STATION),
                eq("any data term"),
                eq(0),
                eq(10),
                eq("any service key"),
                eq(0.3)))
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

        assertThat(airPollution.getPm10()).isEqualTo(Matter.builder()
                .value(0.3)
                .predicatedValueAfter24H(0.5)
                .grade(1)
                .build());
        assertThat(airPollution.getPm25()).isEqualTo(Matter.builder()
                .value(0.2)
                .predicatedValueAfter24H(0.5)
                .grade(1)
                .build());
        assertThat(airPollution.getCo()).isEqualTo(Matter.builder()
                .value(0.1)
                .grade(1)
                .build());
        assertThat(airPollution.getSo2()).isEqualTo(Matter.builder()
                .value(0.3)
                .grade(7)
                .build());
        assertThat(airPollution.getNo2()).isEqualTo(Matter.builder()
                .value(0.2)
                .grade(6)
                .build());
        assertThat(airPollution.getO3()).isEqualTo(Matter.builder()
                .value(0.5)
                .grade(8)
                .build());
        assertThat(airPollution.getMeasuringTimestamp()).isEqualTo("2016-04-20 14:00");
    }

    @Test(expected = NotFoundException.class)
    public void whenResultStatusIsNotSuccess_thenThrowNotFoundException() throws Exception {
        when(mockAirPollutionClient.getPollution(eq(ANY_STATION),
                eq("any data term"),
                eq(0),
                eq(10),
                eq("any service key"),
                eq(0.3)))
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
                eq("any data term"),
                eq(0),
                eq(10),
                eq("any service key"),
                eq(0.3)))
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