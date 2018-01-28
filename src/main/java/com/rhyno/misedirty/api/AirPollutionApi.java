package com.rhyno.misedirty.api;

import com.rhyno.misedirty.api.model.AirPollutionResponse;
import com.rhyno.misedirty.api.model.Status;
import com.rhyno.misedirty.exception.NotFoundException;
import com.rhyno.misedirty.model.AirPollution;
import com.rhyno.misedirty.model.Matter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public AirPollutionApi(@Value("${application.openApi.baseUrl}") String baseUrl,
                           @Value("${application.openApi.serviceKey}") String serviceKey,
                           @Value("${application.openApi.version}") Double version,
                           @Value("${application.openApi.dataTerm}") String dataTerm,
                           @Value("${application.openApi.pageNo}") Integer pageNo,
                           @Value("${application.openApi.numOfRows}") Integer numOfRows,
                           AirPollutionClient airPollutionClient) {
        this.baseUrl = baseUrl;
        this.serviceKey = serviceKey;
        this.version = version;
        this.dataTerm = dataTerm;
        this.pageNo = pageNo;
        this.numOfRows = numOfRows;
        this.airPollutionClient = airPollutionClient;
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
                .map(air -> AirPollution.builder()
                        .pm10(Matter.builder()
                                .value(air.getPm10Value())
                                .predicatedValueAfter24H(air.getPm10Value24())
                                .build())
                        .pm25(Matter.builder()
                                .value(air.getPm25Value())
                                .predicatedValueAfter24H(air.getPm25Value24())
                                .build())
                        .co(Matter.builder().value(air.getCoValue()).build())
                        .so2(Matter.builder().value(air.getSo2Value()).build())
                        .no2(Matter.builder().value(air.getNo2Value()).build())
                        .o3(Matter.builder().value(air.getO3Value()).build())
                        .measuringTimestamp(air.getDataTime())
                        .build())
                .collect(Collectors.toList());
        return airPollutions.get(0);
    }
}
