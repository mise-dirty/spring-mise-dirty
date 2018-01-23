package com.rhyno.misedirty.apis.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
public class AirPollution {
    @Getter
    @Setter
    private List<Air> list;
}
