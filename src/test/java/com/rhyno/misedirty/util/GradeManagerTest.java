package com.rhyno.misedirty.util;

import com.rhyno.misedirty.config.GradeProperties;
import com.rhyno.misedirty.model.Criteria;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GradeManagerTest {
    @Autowired
    private GradeProperties gradeProperties;

    private Criteria pm10Criteria;
    private Criteria pm25Criteria;
    private Criteria so2Criteria;
    private Criteria coCriteria;
    private Criteria o3Criteria;
    private Criteria no2Criteria;

    @Before
    public void setUp() throws Exception {
        pm10Criteria = gradeProperties.getPm10();
        pm25Criteria = gradeProperties.getPm25();
        so2Criteria = gradeProperties.getSo2();
        coCriteria = gradeProperties.getCo();
        o3Criteria = gradeProperties.getO3();
        no2Criteria = gradeProperties.getNo2();
    }

    @Test
    public void whenJudgeWithPm10Criteria_thenReturnCorrectGrade() throws Exception {
        assertThat(GradeManager.judge(pm10Criteria, 5d)).isEqualTo(1);
        assertThat(GradeManager.judge(pm10Criteria, 29d)).isEqualTo(2);
        assertThat(GradeManager.judge(pm10Criteria, 32d)).isEqualTo(3);
        assertThat(GradeManager.judge(pm10Criteria, 42d)).isEqualTo(4);
        assertThat(GradeManager.judge(pm10Criteria, 52d)).isEqualTo(5);
        assertThat(GradeManager.judge(pm10Criteria, 99d)).isEqualTo(6);
        assertThat(GradeManager.judge(pm10Criteria, 130d)).isEqualTo(7);
        assertThat(GradeManager.judge(pm10Criteria, 151d)).isEqualTo(8);
    }

    @Test
    public void whenJudgeWithPm25Criteria_returnCorrectGrade() throws Exception {
        assertThat(GradeManager.judge(pm25Criteria, 8d)).isEqualTo(1);
        assertThat(GradeManager.judge(pm25Criteria, 10d)).isEqualTo(2);
        assertThat(GradeManager.judge(pm25Criteria, 16d)).isEqualTo(3);
        assertThat(GradeManager.judge(pm25Criteria, 21d)).isEqualTo(4);
        assertThat(GradeManager.judge(pm25Criteria, 37d)).isEqualTo(5);
        assertThat(GradeManager.judge(pm25Criteria, 49d)).isEqualTo(6);
        assertThat(GradeManager.judge(pm25Criteria, 75d)).isEqualTo(7);
        assertThat(GradeManager.judge(pm25Criteria, 771d)).isEqualTo(8);
    }

    @Test
    public void whenJudgeWithSo2Criteria_returnCorrectGrade() throws Exception {
        assertThat(GradeManager.judge(so2Criteria, 0.01d)).isEqualTo(1);
        assertThat(GradeManager.judge(so2Criteria, 0.02d)).isEqualTo(2);
        assertThat(GradeManager.judge(so2Criteria, 0.03d)).isEqualTo(3);
        assertThat(GradeManager.judge(so2Criteria, 0.041d)).isEqualTo(4);
        assertThat(GradeManager.judge(so2Criteria, 0.07d)).isEqualTo(5);
        assertThat(GradeManager.judge(so2Criteria, 0.12d)).isEqualTo(6);
        assertThat(GradeManager.judge(so2Criteria, 0.5d)).isEqualTo(7);
        assertThat(GradeManager.judge(so2Criteria, 1d)).isEqualTo(8);
    }

    @Test
    public void whenJudgeWithCoCriteria_returnCorrectGrade() throws Exception {
        assertThat(GradeManager.judge(coCriteria, 0.5d)).isEqualTo(1);
        assertThat(GradeManager.judge(coCriteria, 1.5d)).isEqualTo(2);
        assertThat(GradeManager.judge(coCriteria, 3.4d)).isEqualTo(3);
        assertThat(GradeManager.judge(coCriteria, 5.6d)).isEqualTo(4);
        assertThat(GradeManager.judge(coCriteria, 10d)).isEqualTo(5);
        assertThat(GradeManager.judge(coCriteria, 13d)).isEqualTo(6);
        assertThat(GradeManager.judge(coCriteria, 20d)).isEqualTo(7);
        assertThat(GradeManager.judge(coCriteria, 33.1)).isEqualTo(8);
    }

    @Test
    public void whenJudgeWithO3Criteria_returnCorrectGrade() throws Exception {
        assertThat(GradeManager.judge(o3Criteria, 0.01d)).isEqualTo(1);
        assertThat(GradeManager.judge(o3Criteria, 0.022d)).isEqualTo(2);
        assertThat(GradeManager.judge(o3Criteria, 0.033d)).isEqualTo(3);
        assertThat(GradeManager.judge(o3Criteria, 0.061d)).isEqualTo(4);
        assertThat(GradeManager.judge(o3Criteria, 0.098d)).isEqualTo(5);
        assertThat(GradeManager.judge(o3Criteria, 0.123d)).isEqualTo(6);
        assertThat(GradeManager.judge(o3Criteria, 0.20d)).isEqualTo(7);
        assertThat(GradeManager.judge(o3Criteria, 0.40d)).isEqualTo(8);
    }

    @Test
    public void whenJudgeWithNo2Criteria_returnCorrectGrade() throws Exception {
        assertThat(GradeManager.judge(no2Criteria, 0.01d)).isEqualTo(1);
        assertThat(GradeManager.judge(no2Criteria, 0.022d)).isEqualTo(2);
        assertThat(GradeManager.judge(no2Criteria, 0.033d)).isEqualTo(3);
        assertThat(GradeManager.judge(no2Criteria, 0.051d)).isEqualTo(4);
        assertThat(GradeManager.judge(no2Criteria, 0.10d)).isEqualTo(5);
        assertThat(GradeManager.judge(no2Criteria, 0.15d)).isEqualTo(6);
        assertThat(GradeManager.judge(no2Criteria, 1.0d)).isEqualTo(7);
        assertThat(GradeManager.judge(no2Criteria, 1.15d)).isEqualTo(8);
    }
}