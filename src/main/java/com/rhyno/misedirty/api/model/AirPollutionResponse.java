package com.rhyno.misedirty.api.model;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@XmlRootElement(name = "response")
public class AirPollutionResponse {
    @XmlElement(name = "header")
    private ResponseHeader header;

    @XmlElement(name = "body")
    private ResponseBody body;
}
