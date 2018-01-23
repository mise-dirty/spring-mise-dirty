package com.rhyno.misedirty.apis;

import com.rhyno.misedirty.apis.model.AirPollution;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="openApi", url = "${application.openApi.baseUrl}")
@Deprecated
public interface AirPollutionClient {

    @RequestMapping(method = RequestMethod.GET,
            value = "/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    AirPollution getPollution(@RequestParam(value = "_returnType") String _returnType,
                              @RequestParam(value = "stationName") String stationName,
                              @RequestParam(value = "dataTerm") String dataTerm,
                              @RequestParam(value = "pageNo") Integer pageNo,
                              @RequestParam(value = "numOfRows") Integer numOfRows,
                              @RequestParam(value = "ServiceKey") String key,
                              @RequestParam(value = "ver") Double ver);
}
