package com.grade.helper.businesslogic.service;

import com.grade.helper.businesslogic.entities.simple.SchoolYear;
import com.grade.helper.businesslogic.repositories.SchoolYearRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * created by ihelms on 26.11.2021
 */

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

    public void addSchoolYear(SchoolYear schoolYear) {


    }

    //generating test data
    @PostConstruct
    public void populateTestData() {
        if (schoolYearRepository.count() == 0) {
            schoolYearRepository.saveAll(
                    Stream.of(
                            "1. Klasse",
                            "2. Klasse",
                            "3. Klasse",
                            "4. Klasse",
                            "5. Klasse",
                            "6. Klasse",
                            "7. Klasse",
                            "8. Klasse",
                            "9. Klasse",
                            "10. Klasse"
                    ).map(name -> {
                        SchoolYear schoolYear = new SchoolYear();
                        schoolYear.setValue(name);
                        return schoolYear;
                    }).collect(Collectors.toList())
            );
        }
    }
}
