package com.rhyno.misedirty.api;

import com.google.gson.Gson;
import com.rhyno.misedirty.api.model.AirPollutionResponse;
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
@Deprecated
public class AirPollutionRestTemplateApi {
    /**
     *  공공API는 xml기본으로 response 리턴한다 returnType=jsond하면 response가 text/plain으로 리턴된다
     *
     */
    private static final Logger logger = LoggerFactory.getLogger(AirPollutionRestTemplateApi.class);

    private RestTemplate restTemplate;

    private AirPollutionApi airPollutionApi;

    @Autowired
    public AirPollutionRestTemplateApi(RestTemplate restTemplate, AirPollutionApi airPollutionApi) {
        this.restTemplate = restTemplate;
        this.airPollutionApi = airPollutionApi;
    }

    public AirPollutionResponse getPollution(String station) {
        return Optional.ofNullable(getPollutionUrl(station))
                .map(pollutionUrl -> {
                    ResponseEntity<String> response = restTemplate.getForEntity(pollutionUrl,
                            String.class);

                    logger.debug("[AirPollutionApi] response: statusCode={}, body={}",
                            response.getStatusCode(), response.getBody());

                    return response.getBody();
                })
                .map(strAirPollution -> {
                    Gson gson = new Gson();
                    return gson.fromJson(strAirPollution, AirPollutionResponse.class);
                })
                .orElse(null);
    }

    private URI getPollutionUrl(String station) {
        String GET_POLLUTION_URL = airPollutionApi.getBaseUrl() + "/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?" +
                "stationName=" + station +
                "&dataTerm=" + airPollutionApi.getDataTerm() +
                "&pageNo=" + airPollutionApi.getPageNo() +
                "&numOfRows=" + airPollutionApi.getNumOfRows() +
                "&ServiceKey=" + airPollutionApi.getServiceKey() +
                "&ver=" + airPollutionApi.getVersion();
        try {
            return new URI(GET_POLLUTION_URL);
        } catch (URISyntaxException e) {
            return null;
        }
    }
}
