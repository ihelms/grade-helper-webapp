package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.simple.GradeType;
import com.grade.helper.businesslogic.repositories.GradeTypeRepository;
import org.springframework.stereotype.Service;


@Service
public class GradeTypeService {

    GradeTypeRepository gradeTypeRepository;

    public GradeTypeService(GradeTypeRepository gradeTypeRepository) {
        this.gradeTypeRepository = gradeTypeRepository;
    }

    public GradeType findGradeTypeByValue(String value) {
        return gradeTypeRepository.findByGradeType(value);
    }

}
