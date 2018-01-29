package com.rhyno.misedirty.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URLDecoder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirPollutionWebClientTest {
    @Autowired
    private AirPollutionWebClient airPollutionWebClient;

    @Value("${application.openApi.serviceKey}")
    private String serviceKey;

    @Autowired
    private AirPollutionApi airPollutionApi;

    @Test(expected = UnsupportedOperationException.class)
    public void whenPmClientGetPollutions_thenReturnPollutions() throws Exception {
        airPollutionWebClient.getPollution(
                "성북구",
                airPollutionApi.getDataTerm(),
                airPollutionApi.getPageNo(),
                airPollutionApi.getNumOfRows(),
                URLDecoder.decode(airPollutionApi.getServiceKey(), "UTF-8"),
                airPollutionApi.getVersion());

        /**
         * TODO. https://docs.spring.io/spring/docs/5.0.0.BUILD-SNAPSHOT/spring-framework-reference/html/web-reactive.html
         * Flux, Observable 등 stream type을 사용하려면 request/response에 media type 이 아래와 같이 정해져 있어야 한다
         * ex. application/json, application/stream+json, application/event-stream
         *
         */
    }
}