package com.rhyno.misedirty.apis;

import com.rhyno.misedirty.apis.model.AirPollutionResponse;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirPollutionApiTest {
    @Autowired
    private AirPollutionApi airPollutionApi;

    @Ignore
    @Test
    public void whenAirPollutionApiGetPollution_thenReturnAirPollution() throws Exception {
        AirPollutionResponse airPollution = airPollutionApi.getPollution("성북구");

        assertThat(airPollution).isNotNull();
    }
}