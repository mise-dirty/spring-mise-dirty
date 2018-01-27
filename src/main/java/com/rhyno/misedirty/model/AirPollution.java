package com.rhyno.misedirty.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AirPollution {
    private Matter pm10;
    private Matter pm25;
    private Matter so2;
    private Matter co;
    private Matter o3;
    private Matter no2;
    private String measuringTimestamp;
}
