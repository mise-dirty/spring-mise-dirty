package com.rhyno.misedirty.config;

import com.rhyno.misedirty.model.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GradePropertiesTest {
    @Autowired
    private GradeProperties gradeProperties;

    @Test
    public void returnPm10Criteria() throws Exception {
        final Criteria pm10 = gradeProperties.getPm10();
        assertThat(pm10.getLevel1().getGrade()).isEqualTo(1);
        assertThat(pm10.getLevel1().getStart()).isEqualTo(0);
        assertThat(pm10.getLevel1().getEnd()).isEqualTo(15);

        assertThat(pm10.getLevel2().getGrade()).isEqualTo(2);
        assertThat(pm10.getLevel2().getStart()).isEqualTo(16);
        assertThat(pm10.getLevel2().getEnd()).isEqualTo(30);

        assertThat(pm10.getLevel3().getGrade()).isEqualTo(3);
        assertThat(pm10.getLevel3().getStart()).isEqualTo(31);
        assertThat(pm10.getLevel3().getEnd()).isEqualTo(40);

        assertThat(pm10.getLevel4().getGrade()).isEqualTo(4);
        assertThat(pm10.getLevel4().getStart()).isEqualTo(41);
        assertThat(pm10.getLevel4().getEnd()).isEqualTo(50);

        assertThat(pm10.getLevel5().getGrade()).isEqualTo(5);
        assertThat(pm10.getLevel5().getStart()).isEqualTo(51);
        assertThat(pm10.getLevel5().getEnd()).isEqualTo(75);

        assertThat(pm10.getLevel6().getGrade()).isEqualTo(6);
        assertThat(pm10.getLevel6().getStart()).isEqualTo(76);
        assertThat(pm10.getLevel6().getEnd()).isEqualTo(100);

        assertThat(pm10.getLevel7().getGrade()).isEqualTo(7);
        assertThat(pm10.getLevel7().getStart()).isEqualTo(101);
        assertThat(pm10.getLevel7().getEnd()).isEqualTo(150);

        assertThat(pm10.getLevel8().getGrade()).isEqualTo(8);
        assertThat(pm10.getLevel8().getStart()).isEqualTo(151);
        assertThat(pm10.getLevel8().getEnd()).isEqualTo(999);
    }

    @Test
    public void returnPm25Criteria() throws Exception {
        final Criteria pm25 = gradeProperties.getPm25();
        assertThat(pm25.getLevel1().getGrade()).isEqualTo(1);
        assertThat(pm25.getLevel1().getStart()).isEqualTo(0);
        assertThat(pm25.getLevel1().getEnd()).isEqualTo(8);

        assertThat(pm25.getLevel2().getGrade()).isEqualTo(2);
        assertThat(pm25.getLevel2().getStart()).isEqualTo(9);
        assertThat(pm25.getLevel2().getEnd()).isEqualTo(15);

        assertThat(pm25.getLevel3().getGrade()).isEqualTo(3);
        assertThat(pm25.getLevel3().getStart()).isEqualTo(16);
        assertThat(pm25.getLevel3().getEnd()).isEqualTo(20);

        assertThat(pm25.getLevel4().getGrade()).isEqualTo(4);
        assertThat(pm25.getLevel4().getStart()).isEqualTo(21);
        assertThat(pm25.getLevel4().getEnd()).isEqualTo(25);

        assertThat(pm25.getLevel5().getGrade()).isEqualTo(5);
        assertThat(pm25.getLevel5().getStart()).isEqualTo(26);
        assertThat(pm25.getLevel5().getEnd()).isEqualTo(37);

        assertThat(pm25.getLevel6().getGrade()).isEqualTo(6);
        assertThat(pm25.getLevel6().getStart()).isEqualTo(38);
        assertThat(pm25.getLevel6().getEnd()).isEqualTo(50);

        assertThat(pm25.getLevel7().getGrade()).isEqualTo(7);
        assertThat(pm25.getLevel7().getStart()).isEqualTo(51);
        assertThat(pm25.getLevel7().getEnd()).isEqualTo(75);

        assertThat(pm25.getLevel8().getGrade()).isEqualTo(8);
        assertThat(pm25.getLevel8().getStart()).isEqualTo(76);
        assertThat(pm25.getLevel8().getEnd()).isEqualTo(999);
    }

    @Test
    public void returnSo2Criteria() throws Exception {
        final Criteria so2 = gradeProperties.getSo2();

        assertThat(so2.getLevel1().getGrade()).isEqualTo(1);
        assertThat(so2.getLevel1().getStart()).isEqualTo(0);
        assertThat(so2.getLevel1().getEnd()).isEqualTo(0.01);

        assertThat(so2.getLevel2().getGrade()).isEqualTo(2);
        assertThat(so2.getLevel2().getStart()).isEqualTo(0.01);
        assertThat(so2.getLevel2().getEnd()).isEqualTo(0.02);

        assertThat(so2.getLevel3().getGrade()).isEqualTo(3);
        assertThat(so2.getLevel3().getStart()).isEqualTo(0.02);
        assertThat(so2.getLevel3().getEnd()).isEqualTo(0.04);

        assertThat(so2.getLevel4().getGrade()).isEqualTo(4);
        assertThat(so2.getLevel4().getStart()).isEqualTo(0.04);
        assertThat(so2.getLevel4().getEnd()).isEqualTo(0.05);

        assertThat(so2.getLevel5().getGrade()).isEqualTo(5);
        assertThat(so2.getLevel5().getStart()).isEqualTo(0.05);
        assertThat(so2.getLevel5().getEnd()).isEqualTo(0.1);

        assertThat(so2.getLevel6().getGrade()).isEqualTo(6);
        assertThat(so2.getLevel6().getStart()).isEqualTo(0.1);
        assertThat(so2.getLevel6().getEnd()).isEqualTo(0.15);

        assertThat(so2.getLevel7().getGrade()).isEqualTo(7);
        assertThat(so2.getLevel7().getStart()).isEqualTo(0.15);
        assertThat(so2.getLevel7().getEnd()).isEqualTo(0.6);

        assertThat(so2.getLevel8().getGrade()).isEqualTo(8);
        assertThat(so2.getLevel8().getStart()).isEqualTo(0.6);
        assertThat(so2.getLevel8().getEnd()).isEqualTo(999);
    }

    @Test
    public void returnCoCriteria() throws Exception {
        final Criteria co = gradeProperties.getCo();

        assertThat(co.getLevel1().getGrade()).isEqualTo(1);
        assertThat(co.getLevel1().getStart()).isEqualTo(0);
        assertThat(co.getLevel1().getEnd()).isEqualTo(1);

        assertThat(co.getLevel2().getGrade()).isEqualTo(2);
        assertThat(co.getLevel2().getStart()).isEqualTo(1);
        assertThat(co.getLevel2().getEnd()).isEqualTo(2);

        assertThat(co.getLevel3().getGrade()).isEqualTo(3);
        assertThat(co.getLevel3().getStart()).isEqualTo(2);
        assertThat(co.getLevel3().getEnd()).isEqualTo(5.5);

        assertThat(co.getLevel4().getGrade()).isEqualTo(4);
        assertThat(co.getLevel4().getStart()).isEqualTo(5.5);
        assertThat(co.getLevel4().getEnd()).isEqualTo(9);

        assertThat(co.getLevel5().getGrade()).isEqualTo(5);
        assertThat(co.getLevel5().getStart()).isEqualTo(9);
        assertThat(co.getLevel5().getEnd()).isEqualTo(12);

        assertThat(co.getLevel6().getGrade()).isEqualTo(6);
        assertThat(co.getLevel6().getStart()).isEqualTo(12);
        assertThat(co.getLevel6().getEnd()).isEqualTo(15);

        assertThat(co.getLevel7().getGrade()).isEqualTo(7);
        assertThat(co.getLevel7().getStart()).isEqualTo(15);
        assertThat(co.getLevel7().getEnd()).isEqualTo(32);

        assertThat(co.getLevel8().getGrade()).isEqualTo(8);
        assertThat(co.getLevel8().getStart()).isEqualTo(32);
        assertThat(co.getLevel8().getEnd()).isEqualTo(999);
    }

    @Test
    public void returnO3Criteria() throws Exception {
        final Criteria o3 = gradeProperties.getO3();
        assertThat(o3.getLevel1().getGrade()).isEqualTo(1);
        assertThat(o3.getLevel1().getStart()).isEqualTo(0);
        assertThat(o3.getLevel1().getEnd()).isEqualTo(0.02);

        assertThat(o3.getLevel2().getGrade()).isEqualTo(2);
        assertThat(o3.getLevel2().getStart()).isEqualTo(0.02);
        assertThat(o3.getLevel2().getEnd()).isEqualTo(0.03);

        assertThat(o3.getLevel3().getGrade()).isEqualTo(3);
        assertThat(o3.getLevel3().getStart()).isEqualTo(0.03);
        assertThat(o3.getLevel3().getEnd()).isEqualTo(0.06);

        assertThat(o3.getLevel4().getGrade()).isEqualTo(4);
        assertThat(o3.getLevel4().getStart()).isEqualTo(0.06);
        assertThat(o3.getLevel4().getEnd()).isEqualTo(0.09);

        assertThat(o3.getLevel5().getGrade()).isEqualTo(5);
        assertThat(o3.getLevel5().getStart()).isEqualTo(0.09);
        assertThat(o3.getLevel5().getEnd()).isEqualTo(0.12);

        assertThat(o3.getLevel6().getGrade()).isEqualTo(6);
        assertThat(o3.getLevel6().getStart()).isEqualTo(0.12);
        assertThat(o3.getLevel6().getEnd()).isEqualTo(0.15);

        assertThat(o3.getLevel7().getGrade()).isEqualTo(7);
        assertThat(o3.getLevel7().getStart()).isEqualTo(0.15);
        assertThat(o3.getLevel7().getEnd()).isEqualTo(0.38);

        assertThat(o3.getLevel8().getGrade()).isEqualTo(8);
        assertThat(o3.getLevel8().getStart()).isEqualTo(0.38);
        assertThat(o3.getLevel8().getEnd()).isEqualTo(999);
    }

    @Test
    public void returnNo2Criteria() throws Exception {
        final Criteria no2 = gradeProperties.getNo2();
        assertThat(no2.getLevel1().getGrade()).isEqualTo(1);
        assertThat(no2.getLevel1().getStart()).isEqualTo(0);
        assertThat(no2.getLevel1().getEnd()).isEqualTo(0.02);

        assertThat(no2.getLevel2().getGrade()).isEqualTo(2);
        assertThat(no2.getLevel2().getStart()).isEqualTo(0.02);
        assertThat(no2.getLevel2().getEnd()).isEqualTo(0.03);

        assertThat(no2.getLevel3().getGrade()).isEqualTo(3);
        assertThat(no2.getLevel3().getStart()).isEqualTo(0.03);
        assertThat(no2.getLevel3().getEnd()).isEqualTo(0.05);

        assertThat(no2.getLevel4().getGrade()).isEqualTo(4);
        assertThat(no2.getLevel4().getStart()).isEqualTo(0.05);
        assertThat(no2.getLevel4().getEnd()).isEqualTo(0.06);

        assertThat(no2.getLevel5().getGrade()).isEqualTo(5);
        assertThat(no2.getLevel5().getStart()).isEqualTo(0.06);
        assertThat(no2.getLevel5().getEnd()).isEqualTo(0.13);

        assertThat(no2.getLevel6().getGrade()).isEqualTo(6);
        assertThat(no2.getLevel6().getStart()).isEqualTo(0.13);
        assertThat(no2.getLevel6().getEnd()).isEqualTo(0.2);

        assertThat(no2.getLevel7().getGrade()).isEqualTo(7);
        assertThat(no2.getLevel7().getStart()).isEqualTo(0.2);
        assertThat(no2.getLevel7().getEnd()).isEqualTo(1.1);

        assertThat(no2.getLevel8().getGrade()).isEqualTo(8);
        assertThat(no2.getLevel8().getStart()).isEqualTo(1.1);
        assertThat(no2.getLevel8().getEnd()).isEqualTo(2);
    }
}