package com.grade.helper.businesslogic.repositories;

import com.grade.helper.businesslogic.entities.GradeDAO;
import com.grade.helper.businesslogic.enums.GRADE_TYPE;
import com.grade.helper.businesslogic.enums.SUBJECT;
import com.grade.helper.businesslogic.resource.SubjectAverage;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Set;

/**
 * created by ihelms on 26.11.2021
 */

public class PseudoClasses {

    public List<String> getSchoolYearValues() {
        return List.of("8.Klasse", "9.Klasse");
    }

    public Set<GradeDAO> getGradesForSubject(SUBJECT subject) {
        if(subject == SUBJECT.BIOLOGIE || subject == SUBJECT.BWL) {
            GradeDAO gradeDAO = new GradeDAO();
            gradeDAO.setId(1L);
            gradeDAO.setDate(Timestamp.from(Instant.now()));
            gradeDAO.setGrade_type(GRADE_TYPE.KLAUSUR);
            gradeDAO.setGrade(1);
            gradeDAO.setPrioritisation(12.5);
            gradeDAO.setSubject(subject);

            return Set.of(gradeDAO);
        }
        return Set.of(new GradeDAO());
    }

    public Set<SubjectAverage> getSubjectAverage() {
        SubjectAverage subjectAverage = new SubjectAverage();
        subjectAverage.setAverage(1.2);
        subjectAverage.setSubject(SUBJECT.BWL);
        SubjectAverage subjectAverage2 = new SubjectAverage();
        subjectAverage2.setAverage(2.3);
        subjectAverage2.setSubject(SUBJECT.BIOLOGIE);
        return Set.of(subjectAverage, subjectAverage2);
    }
}
