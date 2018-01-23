package com.rhyno.misedirty.apis;

import com.google.gson.Gson;
import com.rhyno.misedirty.apis.model.AirPollution;
import com.rhyno.misedirty.apis.model.OpenApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Service
public class AirPollutionApi {
    private static final Logger logger = LoggerFactory.getLogger(AirPollutionApi.class);

    private RestTemplate restTemplate;

    private OpenApi openApi;

    @Autowired
    public AirPollutionApi(RestTemplate restTemplate, OpenApi openApi) {
        this.restTemplate = restTemplate;
        this.openApi = openApi;
    }

    public AirPollution getPollution(String station) {
        return Optional.ofNullable(getPollutionUrl(station))
                .map(pollutionUrl -> {
                    ResponseEntity<String> response = restTemplate.getForEntity(pollutionUrl,
                            String.class);

                    logger.debug("[AirPollutionApi] response: statusCode={}, body={}",
                            response.getStatusCode(),
                            response.getBody().toString());

                    return response.getBody();
                })
                .map(strAirPollution -> {
                    Gson gson = new Gson();
                    return gson.fromJson(strAirPollution, AirPollution.class);
                })
                .orElse(null);
    }

    private URI getPollutionUrl(String station) {
        String GET_POLLUTION_URL = openApi.getBaseUrl() + "/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?" +
                "stationName=" + station +
                "&dataTerm=" + openApi.getDataTerm() +
                "&pageNo=" + openApi.getPageNo() +
                "&numOfRows=" + openApi.getNumOfRows() +
                "&ServiceKey=" + openApi.getServiceKey() +
                "&ver=" + openApi.getVersion() +
                "&_returnType=" + openApi.getContentType();
        try {
            return new URI(GET_POLLUTION_URL);
        } catch (URISyntaxException e) {
            return null;
        }
    }
}
