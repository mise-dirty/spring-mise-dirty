package com.rhyno.misedirty.pollution;

import com.rhyno.misedirty.apis.PmApi;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PollutionControllerTest {
    private static final String POLLUTION_BASE_URL = "/api/v1/pollution";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PollutionController pollutionController;

    @MockBean
    private PmApi mockPmApi;
    private Pollution firstPollution;
    private Pollution secondPollution;

    @Before
    public void setUp() throws Exception {
        firstPollution = Pollution.builder()
                .pm10(ParticularMatter.builder().value(25).predicated(30).build())
                .pm25(ParticularMatter.builder().value(10).predicated(15).build())
                .build();
        secondPollution = Pollution.builder()
                .pm10(ParticularMatter.builder().value(25).predicated(30).build())
                .pm25(ParticularMatter.builder().value(10).predicated(15).build())
                .build();
    }

    @Test
    public void whenGet_pollutionWithStationAndPeriod_thenCallOpenApiGetPollutions() throws Exception {
        pollutionController.getPollution("성북구", "DAILY");

        then(mockPmApi).should().getPollution(eq("성북구"), eq("DAILY"));
    }

    @Test
    public void whenGET_pollution_thenReturnPollution() throws Exception {
        when(mockPmApi.getPollution(eq("성북구"), eq("DAILY")))
                .thenReturn(Lists.newArrayList(firstPollution, secondPollution));

        ResponseEntity<List<Pollution>> response = restTemplate.exchange(
                POLLUTION_BASE_URL + "?station=성북구&period=DAILY",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Pollution>>() {
                });

        HttpStatus statusCode = response.getStatusCode();
        assertThat(statusCode).isEqualTo(HttpStatus.OK);
        List<Pollution> pollutions = response.getBody();
        assertThat(pollutions).containsExactly(firstPollution, secondPollution);
    }
}