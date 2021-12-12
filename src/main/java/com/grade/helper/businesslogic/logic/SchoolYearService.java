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

    public Set<String> getSchoolYearValuesByUser() {
        return new HashSet<>();
    }

    public List<String> getAllSchoolYearsAsString() {
        List<String> schoolYearName = new LinkedList<>();

        List<SchoolYear> schoolYearList = schoolYearRepository.findAll();
        schoolYearList.forEach(schoolYearDAO -> schoolYearName.add(schoolYearDAO.getValue()));

        return schoolYearName;
    }

    public SchoolYear getSchoolYearByValue(String schoolYear) {
        return schoolYearRepository.findSchoolYearDAOByValue(schoolYear);
    }

    public SchoolYear getSchoolYearById(Long id) {
        return schoolYearRepository.findSchoolYearDAOById(id);
    }
}
