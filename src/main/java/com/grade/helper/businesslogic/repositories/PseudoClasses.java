package com.grade.helper.businesslogic.repositories;

import com.grade.helper.businesslogic.entities.GradeDAO;
import com.grade.helper.businesslogic.entities.SchoolYearDAO;
import com.grade.helper.businesslogic.enums.GRADE_TYPE;
import com.grade.helper.businesslogic.enums.SUBJECT;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Set;

/**
 * created by ihelms on 26.11.2021
 */

public class PseudoClasses {

    public List<SchoolYearDAO> getSchoolYears() {
        SchoolYearDAO schoolYearDAO_1 = new SchoolYearDAO();
        schoolYearDAO_1.setId(1L);
        schoolYearDAO_1.setValue("8. Klasse");

        SchoolYearDAO schoolYearDAO_2 = new SchoolYearDAO();
        schoolYearDAO_2.setId(2L);
        schoolYearDAO_2.setValue("9. Klasse");

        return List.of(schoolYearDAO_1, schoolYearDAO_2);
    }

    public List<String> getSchoolYearValues() {
        return List.of("8.Klasse", "9.Klasse");
    }

    public Set<GradeDAO> getGradesForSubject(SUBJECT subject) {
        if(subject == SUBJECT.BIOLOGIE || subject == SUBJECT.BWL) {
            GradeDAO gradeDAO = new GradeDAO();
            gradeDAO.setId(1L);
            gradeDAO.setDate(Timestamp.from(Instant.now()));
            gradeDAO.setGrade_type(GRADE_TYPE.KLAUSUR);

            return Set.of(gradeDAO);
        }
        return Set.of(new GradeDAO());
    }
}
