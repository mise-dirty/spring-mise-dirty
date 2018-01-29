package com.rhyno.misedirty.util;

import com.rhyno.misedirty.model.Criteria;
import com.rhyno.misedirty.model.Grade;

import java.util.Arrays;
import java.util.List;

public class GradeManager {
    public static int judge(Criteria criteria, Double value) {
        final List<Grade> grades = Arrays.asList(criteria.getLevel1(), criteria.getLevel2(),
                criteria.getLevel3(), criteria.getLevel4(),
                criteria.getLevel5(), criteria.getLevel6(),
                criteria.getLevel7(), criteria.getLevel8());

        int currentGrade = 0;
        for (Grade grade : grades) {
            if (grade.getStart() <= value &&
                    grade.getEnd() >= value) {
                currentGrade = grade.getGrade();
                break;
            }
        }
        return currentGrade;
    }
}
