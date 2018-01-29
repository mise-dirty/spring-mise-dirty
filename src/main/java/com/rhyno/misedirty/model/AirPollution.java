package com.rhyno.misedirty.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class AirPollution {
    @Id
    private String id;

    private Matter pm10;
    private Matter pm25;
    private Matter so2;
    private Matter co;
    private Matter o3;
    private Matter no2;
    private String measuringTimestamp;
    private Integer totalGrade;
}
