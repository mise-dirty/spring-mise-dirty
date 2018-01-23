package com.rhyno.misedirty.apis.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Air {
    private Integer coGrade;
    private Double coValue;
    private String dataTerm;
    private String dataTime;
    private Integer khaiGrade;
    private Integer khaiValue;
    private String mangName;
    private Integer no2Grade;
    private Double no2Value;
    private Integer numOfRows;
    private Integer o3Grade;
    private Double o3Value;
    private Integer pageNo;
    private Integer pm10Grade;
    private Integer pm10Grade1h;
    private Double pm10Value;
    private Double pm10Value24;
    private Integer pm25Grade;
    private Integer pm25Grade1h;
    private Double pm25Value;
    private Double pm25Value24;
    private String resultCode;
    private String resultMsg;
    private Integer rnum;
    private Integer so2Grade;
    private Double so2Value;
}
