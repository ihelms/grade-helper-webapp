package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.simple.*;
import com.grade.helper.businesslogic.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
