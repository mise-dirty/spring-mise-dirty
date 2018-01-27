package com.rhyno.misedirty.apis.model;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "header")
public class ResponseHeader {
    private Status resultCode;
    private String resultMsg;
}
