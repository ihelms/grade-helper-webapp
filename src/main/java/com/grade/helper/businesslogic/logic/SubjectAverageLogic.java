package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.SchoolYearDAO;
import com.grade.helper.businesslogic.repositories.PseudoClasses;
import com.grade.helper.businesslogic.resource.SubjectAverage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * created by ihelms on 02.12.2021
 */

@Service
public class SubjectAverageLogic {

    public Set<SubjectAverage> getSubjectAverageForSchoolYear() {
        PseudoClasses pseudoClasses = new PseudoClasses();
        return pseudoClasses.getSubjectAverage();
    }

}
