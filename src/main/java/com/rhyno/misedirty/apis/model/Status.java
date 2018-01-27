package com.rhyno.misedirty.apis.model;

public enum Status {
    SUCCESS("00"),
    SERVICE_ACCESS_DENIED("20"),
    SERVICE_KEY_IS_NOT_REGISTERED("30");

    private String code;

    Status(String code) {
        this.code = code;
    }
}
