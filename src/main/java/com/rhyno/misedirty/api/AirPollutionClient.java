package com.rhyno.misedirty.api;

import com.rhyno.misedirty.api.model.AirPollutionResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="openApi", url = "${application.openApi.baseUrl}")
public interface AirPollutionClient {
    @RequestMapping(method = RequestMethod.GET,
            value = "/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty")
    AirPollutionResponse getPollution(@RequestParam(value = "stationName") String stationName,
                              @RequestParam(value = "dataTerm") String dataTerm,
                              @RequestParam(value = "pageNo") Integer pageNo,
                              @RequestParam(value = "numOfRows") Integer numOfRows,
                              @RequestParam(value = "ServiceKey") String key,
                              @RequestParam(value = "ver") Double ver);
}
