package com.rhyno.misedirty.model;

import lombok.*;

@Builder
@Data
public class Matter {
    private Double value;
    private Double predicatedValueAfter24H;
    private Integer grade;
}
