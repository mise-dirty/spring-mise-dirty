package com.rhyno.misedirty.api;

import com.rhyno.misedirty.api.model.AirPollutionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AirPollutionWebClient {
    @Value("${application.openApi.baseUrl}")
    private String baseUrl;

    public AirPollutionResponse getPollution(String stationName,
                                             String dataTerm,
                                             Integer pageNo,
                                             Integer numOfRows,
                                             String key,
                                             Double ver) {

        return WebClient.create(baseUrl)
                .get()
                .uri("/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?" +
                        "stationName=" + stationName + "&" +
                        "dataTerm=" + dataTerm + "&" +
                        "pageNo=" + pageNo + "&" +
                        "numOfRows=" + numOfRows + "&" +
                        "ServiceKey=" + key + "&" +
                        "ver=" + ver)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AirPollutionResponse.class)
                .block();
    }
}
