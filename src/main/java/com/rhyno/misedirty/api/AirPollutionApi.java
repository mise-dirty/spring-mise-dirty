package com.rhyno.misedirty.api;

import com.rhyno.misedirty.api.model.AirPollutionResponse;
import com.rhyno.misedirty.api.model.Status;
import com.rhyno.misedirty.config.GradeProperties;
import com.rhyno.misedirty.exception.NotFoundException;
import com.rhyno.misedirty.model.AirPollution;
import com.rhyno.misedirty.model.Matter;
import com.rhyno.misedirty.util.GradeManager;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Getter
@Setter
public class AirPollutionApi {
    private final String baseUrl;
    private final String serviceKey;
    private final Double version;
    private final String dataTerm;
    private final Integer pageNo;
    private final Integer numOfRows;
    private final AirPollutionClient airPollutionClient;
    private final GradeProperties gradeProperties;

    public AirPollutionApi(@Value("${application.openApi.baseUrl}") String baseUrl,
                           @Value("${application.openApi.serviceKey}") String serviceKey,
                           @Value("${application.openApi.version}") Double version,
                           @Value("${application.openApi.dataTerm}") String dataTerm,
                           @Value("${application.openApi.pageNo}") Integer pageNo,
                           @Value("${application.openApi.numOfRows}") Integer numOfRows,
                           AirPollutionClient airPollutionClient,
                           GradeProperties gradeProperties) {
        this.baseUrl = baseUrl;
        this.serviceKey = serviceKey;
        this.version = version;
        this.dataTerm = dataTerm;
        this.pageNo = pageNo;
        this.numOfRows = numOfRows;
        this.airPollutionClient = airPollutionClient;
        this.gradeProperties = gradeProperties;
    }

    public AirPollution getPollution(String station) throws UnsupportedEncodingException {
        AirPollutionResponse airPollutionResponse = airPollutionClient.getPollution(station,
                this.dataTerm,
                this.pageNo,
                this.numOfRows,
                URLDecoder.decode(this.getServiceKey(), "UTF-8"),
                this.version);

        final Optional<AirPollution> airPollution = Optional.ofNullable(airPollutionResponse)
                .map(res -> {
                    if (!Status.SUCCESS.equals(res.getHeader().getResultCode()) ||
                            res.getBody() == null || res.getBody().getAirs() == null ||
                            res.getBody().getAirs().isEmpty()) {
                        throw new NotFoundException("Air Pollution");
                    }
                    return getCurrentAirPollution(res);
                });

        if (!airPollution.isPresent()) {
            throw new NotFoundException("Air Pollution");
        }

        return airPollution.get();
    }

    private AirPollution getCurrentAirPollution(AirPollutionResponse res) {
        final List<AirPollution> airPollutions = res.getBody().getAirs().stream()
                .map(air -> {
                    final Matter pm10 = Matter.builder().value(air.getPm10Value())
                            .predicatedValueAfter24H(air.getPm10Value24())
                            .grade(GradeManager.judge(gradeProperties.getPm10(), air.getPm10Value())).build();
                    final Matter pm25 = Matter.builder().value(air.getPm25Value())
                            .predicatedValueAfter24H(air.getPm25Value24())
                            .grade(GradeManager.judge(gradeProperties.getPm25(), air.getPm25Value())).build();
                    final Matter co = Matter.builder().value(air.getCoValue())
                            .grade(GradeManager.judge(gradeProperties.getCo(), air.getCoValue())).build();
                    final Matter so2 = Matter.builder().value(air.getSo2Value())
                            .grade(GradeManager.judge(gradeProperties.getSo2(), air.getSo2Value())).build();
                    final Matter no2 = Matter.builder().value(air.getNo2Value())
                            .grade(GradeManager.judge(gradeProperties.getNo2(), air.getNo2Value())).build();
                    final Matter o3 = Matter.builder().value(air.getO3Value())
                            .grade(GradeManager.judge(gradeProperties.getO3(), air.getO3Value())).build();

                    final Integer totalGrade = Stream.of(pm10.getGrade(), pm25.getGrade(), co.getGrade(), so2.getGrade(),
                            no2.getGrade(), o3.getGrade())
                            .sorted(Comparator.reverseOrder())
                            .findFirst()
                            .orElse(0);

                    return AirPollution.builder()
                            .pm10(pm10).pm25(pm25).co(co).so2(so2)
                            .no2(no2).o3(o3)
                            .totalGrade(totalGrade)
                            .measuringTimestamp(air.getDataTime()).build();
                })
                .collect(Collectors.toList());
        return airPollutions.get(0);
    }
}
