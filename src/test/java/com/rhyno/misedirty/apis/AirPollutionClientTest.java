package com.rhyno.misedirty.apis;

import com.rhyno.misedirty.apis.model.AirPollution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirPollutionClientTest {
    @Autowired
    private AirPollutionClient airPollutionClient;

    @Value("${application.openApi.serviceKey}")
    private String serviceKey;

    @Test
    public void whenPmClientGetPollutions_thenReturnPollutions() throws Exception {
        AirPollution result = airPollutionClient.getPollution(
                "json",
                "성북구",
                "DAILY",
                1,
                10,
                serviceKey,
                1.3
        );

        assertThat(result).isNotNull();
    }
}
