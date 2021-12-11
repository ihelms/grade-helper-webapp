package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.simple.*;
import com.grade.helper.businesslogic.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


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

    public List<GradeType> getAllGradeTypes() {
        return gradeTypeRepository.findAll();
    }

    public void saveGrade(Grade grade) {
        gradeRepository.save(grade);
    }

    public void removeGrade(Grade grade) {
        gradeRepository.delete(grade);
    }

    public List<String> getAllGradeTypesAsString() {
        List<String> values = new LinkedList<>();
        List<GradeType> gradeTypeList = getAllGradeTypes();
        gradeTypeList.forEach(gradeType -> values.add(gradeType.getGradeType()));
        return values;
    }
}
