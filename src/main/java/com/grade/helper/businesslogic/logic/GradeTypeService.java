package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.simple.GradeType;
import com.grade.helper.businesslogic.repositories.GradeTypeRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class GradeTypeService {

    GradeTypeRepository gradeTypeRepository;

    public GradeTypeService(GradeTypeRepository gradeTypeRepository) {
        this.gradeTypeRepository = gradeTypeRepository;
    }

    public GradeType findGradeTypeByValue(String value) {
        return gradeTypeRepository.findByGradeType(value);
    }


    public List<GradeType> getAllGradeTypes() {
        return gradeTypeRepository.findAll();
    }

    public List<String> getAllGradeTypesAsString() {
        List<String> values = new LinkedList<>();
        List<GradeType> gradeTypeList = getAllGradeTypes();
        gradeTypeList.forEach(gradeType -> values.add(gradeType.getGradeType()));
        return values;
    }

}
