package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.repositories.PseudoClasses;
import com.grade.helper.businesslogic.resource.SubjectAverage;

import java.util.Set;

/**
 * created by ihelms on 02.12.2021
 */

public class SubjectAverageLogic {

    public Set<SubjectAverage> getSubjectAverageForSchoolYear() {
        PseudoClasses pseudoClasses = new PseudoClasses();
        return pseudoClasses.getSubjectAverage();
    }

}
