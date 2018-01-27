//package com.rhyno.misedirty.controller;
//
//import com.rhyno.misedirty.apis.AirPollutionApi;
//import com.rhyno.misedirty.model.Matter;
//import com.rhyno.misedirty.model.AirPollution;
//import org.assertj.core.util.Lists;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.BDDMockito.then;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class PollutionControllerTest {
//    private static final String POLLUTION_BASE_URL = "/api/v1/controller";
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private AirPollutionController pollutionController;
//
//    @MockBean
//    private AirPollutionApi mockAirPollutionApi;
//    private AirPollution firstPollution;
//    private AirPollution secondPollution;
//
//    @Before
//    public void setUp() throws Exception {
//        firstPollution = AirPollution.builder()
//                .pm10(Matter.builder().value(25).predicated(30).build())
//                .pm25(Matter.builder().value(10).predicated(15).build())
//                .build();
//        secondPollution = AirPollution.builder()
//                .pm10(Matter.builder().value(25).predicated(30).build())
//                .pm25(Matter.builder().value(10).predicated(15).build())
//                .build();
//    }
//
//    @Test
//    public void whenGet_pollutionWithStationAndPeriod_thenCallOpenApiGetPollutions() throws Exception {
//        pollutionController.getPollution("성북구");
//
//        then(mockAirPollutionApi).should().getPollution(eq("성북구"));
//    }
//
//    @Test
//    public void whenGET_pollution_thenReturnPollution() throws Exception {
//        when(mockAirPollutionApi.getPollution(eq("성북구")))
//                .thenReturn(Lists.newArrayList(firstPollution, secondPollution));
//
//        ResponseEntity<List<AirPollution>> response = restTemplate.exchange(
//                POLLUTION_BASE_URL + "?station=성북구&period=DAILY",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<AirPollution>>() {
//                });
//
//        HttpStatus statusCode = response.getStatusCode();
//        assertThat(statusCode).isEqualTo(HttpStatus.OK);
//        List<AirPollution> pollutions = response.getBody();
//        assertThat(pollutions).containsExactly(firstPollution, secondPollution);
//    }
//}