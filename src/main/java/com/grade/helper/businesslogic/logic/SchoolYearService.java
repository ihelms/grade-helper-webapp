package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.SchoolYearDAO;
import com.grade.helper.businesslogic.repositories.PseudoClasses;
import com.grade.helper.businesslogic.repositories.SchoolYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    PseudoClasses pseudoClasses;
    SchoolYearRepository schoolYearRepository;

    public SchoolYearService(SchoolYearRepository schoolYearRepository) {
        this.schoolYearRepository = schoolYearRepository;
        this.pseudoClasses = new PseudoClasses();
    }

    public Set<String> getSchoolYearValuesByUser() {
        Set<String> schoolYearDAOSet = new HashSet<>();

        //TODO: Repo-Aufruf
        schoolYearDAOSet.addAll(pseudoClasses.getSchoolYearValues());

        return schoolYearDAOSet;
    }

    public List<String> getAllSchoolYears() {
        List<String> schoolYearName = new LinkedList<>();

        List<SchoolYearDAO> schoolYearDAOList = schoolYearRepository.findAll();
        schoolYearDAOList.forEach(schoolYearDAO -> schoolYearName.add(schoolYearDAO.getValue()));

        return schoolYearName;
    }

    public void addSchoolYear(SchoolYearDAO schoolYearDAO) {
        //TODO
        //schoolYearRepository.addSchoolYear(SchoolYearDAO schoolYearDAO);
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
                        SchoolYearDAO customer = new SchoolYearDAO();
                        customer.setValue(name);
                        return customer;
                    }).collect(Collectors.toList())
            );
        }
    }
}
