package com.rhyno.misedirty.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pollution {
    private ParticularMatter pm10;
    private ParticularMatter pm25;
    private String measuringTimestamp;
}
