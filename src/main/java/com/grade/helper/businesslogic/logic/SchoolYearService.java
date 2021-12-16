package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.simple.SchoolYear;
import com.grade.helper.businesslogic.repositories.SchoolYearRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SchoolYearService {
    SchoolYearRepository schoolYearRepository;

    public SchoolYearService(SchoolYearRepository schoolYearRepository) {
        this.schoolYearRepository = schoolYearRepository;
    }

    public SchoolYear getSchoolYearByValue(String schoolYear) {
        return schoolYearRepository.findSchoolYearDAOByValue(schoolYear);
    }

    public SchoolYear getSchoolYearById(Long id) {
        return schoolYearRepository.findSchoolYearDAOById(id);
    }
}
