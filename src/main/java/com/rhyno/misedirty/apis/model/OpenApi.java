package com.rhyno.misedirty.apis.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class OpenApi {
    @Value("${application.openApi.baseUrl}")
    private String baseUrl;

    @Value("${application.openApi.serviceKey}")
    private String serviceKey;

    @Value("${application.openApi.version}")
    private Double version;

    @Value("${application.openApi.contentType}")
    private String contentType;

    @Value("${application.openApi.dataTerm}")
    private String dataTerm;

    @Value("${application.openApi.pageNo}")
    private Integer pageNo;

    @Value("${application.openApi.numOfRows}")
    private Integer numOfRows;
}
