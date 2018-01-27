package com.rhyno.misedirty.controller;

import com.rhyno.misedirty.apis.AirPollutionApi;
import com.rhyno.misedirty.model.AirPollution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.then;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirPollutionControllerTest {
    private static final String BASE_POLLUTION_URL = "/api/v1/pollution";
    private static final String ANY_STATION = "성북구";

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private AirPollutionApi mockAirPollutionApi;

    @Test
    public void whenGetGetPollution_callOpenApiGetPollution() throws Exception {
        restTemplate.getForEntity(BASE_POLLUTION_URL
                + "?station=" + ANY_STATION,
                AirPollution.class);

        then(mockAirPollutionApi).should().getPollution("성북구");
    }
}