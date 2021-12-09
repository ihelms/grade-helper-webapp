package com.grade.helper.businesslogic.service;

import com.grade.helper.businesslogic.entities.simple.*;
import com.grade.helper.businesslogic.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

/**
 * created by ihelms on 26.11.2021
 */

@Service
public class GradeService {

    GradeRepository gradeRepository;
    GradeTypeRepository gradeTypeRepository;
    SubjectRepository subjectRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository,
                        GradeTypeRepository gradeTypeRepository,
                        SubjectRepository subjectRepository) {
        this.gradeRepository = gradeRepository;
        this.gradeTypeRepository = gradeTypeRepository;
        this.subjectRepository = subjectRepository;
    }

    public Set<Grade> getGradesBySubjectAndYear(Subject subject) {
        return new HashSet<>();
    }

    public List<GradeType> getAllGradeTypes() {
        return gradeTypeRepository.findAll();
    }

    //generating test data
    @PostConstruct
    public void populateTestData() {
        if (subjectRepository.count() == 0) {
            subjectRepository.saveAll(
                    List.of(
                            new Subject(1L, "Chemie"),
                            new Subject(2L, "Biologie"),
                            new Subject(3L, "Sport"),
                            new Subject(4L, "BWL")
                    )
            );
        }

        if (gradeTypeRepository.count() == 0) {
            gradeTypeRepository.saveAll(
                    List.of(
                            new GradeType(1L, "Epo"),
                            new GradeType(2L, "Klausur"),
                            new GradeType(3L, "Test")
                    )
            );
        }

        if (gradeRepository.count() == 0) {
            gradeRepository.saveAll(
                    List.of(
                            new Grade(new GradeType(1L, "Epo"), 1, 0.25, Timestamp.from(Instant.now()), new Subject(1L, "Chemie")),
                            new Grade(new GradeType(2L, "Klausur"), 1, 0.5, Timestamp.from(Instant.now()), new Subject(1L, "Chemie")),
                            new Grade(new GradeType(3L, "Test"), 1, 0.25, Timestamp.from(Instant.now()), new Subject(2L, "Biologie"))
                    )
            );
        }
    }
}
