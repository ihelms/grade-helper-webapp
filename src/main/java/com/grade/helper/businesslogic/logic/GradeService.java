package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.simple.*;
import com.grade.helper.businesslogic.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GradeService {

    GradeRepository gradeRepository;
    SubjectRepository subjectRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository,
                        SubjectRepository subjectRepository) {
        this.gradeRepository = gradeRepository;
        this.subjectRepository = subjectRepository;
    }

    public void saveGrade(Grade grade) {
        gradeRepository.save(grade);
    }

    public void removeGrade(Grade grade) {
        gradeRepository.delete(grade);
    }
}
