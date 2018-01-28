package com.rhyno.misedirty.api.model;

import lombok.Getter;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum Status {
    @XmlEnumValue("00")
    SUCCESS("00"),

    @XmlEnumValue("20")
    SERVICE_ACCESS_DENIED("20"),

    @XmlEnumValue("30")
    SERVICE_KEY_IS_NOT_REGISTERED("30");

    @Getter
    private String code;

    Status(String code) {
        this.code = code;
    }

}