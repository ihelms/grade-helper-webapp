package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.GradeDAO;
import com.grade.helper.businesslogic.enums.GRADE_TYPE;
import com.grade.helper.businesslogic.enums.SUBJECT;
import com.grade.helper.businesslogic.repositories.GradeRepository;
import com.grade.helper.businesslogic.repositories.PseudoClasses;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Set;

/**
 * created by ihelms on 26.11.2021
 */

@Service
public class GradeService {

    GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public Set<GradeDAO> getGradesBySubjectAndYear(SUBJECT subject) {
        PseudoClasses pseudoClasses = new PseudoClasses();
        return pseudoClasses.getGradesForSubject(subject);
    }

    //generating test data
    @PostConstruct
    public void populateTestData() {
        if (gradeRepository.count() == 0) {
            gradeRepository.saveAll(
                    List.of(
                            new GradeDAO(1L, GRADE_TYPE.EPO, 1, 0.25, Timestamp.from(Instant.now()), SUBJECT.CHEMIE),
                            new GradeDAO(2L, GRADE_TYPE.KLAUSUR, 1, 0.5, Timestamp.from(Instant.now()), SUBJECT.CHEMIE),
                            new GradeDAO(3L, GRADE_TYPE.TEST, 1, 0.25, Timestamp.from(Instant.now()), SUBJECT.MATHEMATIK)
                    )
            );
        }
    }

}
