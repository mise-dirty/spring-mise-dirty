package com.rhyno.misedirty.apis;

import com.rhyno.misedirty.apis.model.AirPollutionResponse;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URLDecoder;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirPollutionClientTest {
    @Autowired
    private AirPollutionClient airPollutionClient;

    @Value("${application.openApi.serviceKey}")
    private String serviceKey;

    @Autowired
    private AirPollutionApi airPollutionApi;

    @Ignore
    @Test
    public void whenPmClientGetPollutions_thenReturnPollutions() throws Exception {
        AirPollutionResponse result = airPollutionClient.getPollution(
                "성북구",
                airPollutionApi.getDataTerm(),
                airPollutionApi.getPageNo(),
                airPollutionApi.getNumOfRows(),
                URLDecoder.decode(airPollutionApi.getServiceKey(), "UTF-8"),
                airPollutionApi.getVersion());

        assertThat(result.getHeader().getResultCode()).isEqualTo("00");
        assertThat(result.getHeader().getResultMsg()).isEqualTo("NORMAL SERVICE.");
    }

    @Ignore
    @Test
    public void whenGetPollutionsWithWrongServiceKey_thenReturnServiceKeyIsNotRegisteredError() throws Exception {
        AirPollutionResponse error = airPollutionClient.getPollution("성북구",
                airPollutionApi.getDataTerm(),
                airPollutionApi.getPageNo(),
                airPollutionApi.getNumOfRows(),
                "not registered service key",
                airPollutionApi.getVersion());

        assertThat(error.getHeader().getResultCode()).isEqualTo("30");
        assertThat(error.getHeader().getResultMsg()).isEqualTo("SERVICE KEY IS NOT REGISTERED ERROR.");
    }
}
