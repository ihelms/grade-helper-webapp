package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.SchoolYearDAO;
import com.grade.helper.businesslogic.repositories.PseudoClasses;

import java.util.HashSet;
import java.util.Set;

/**
 * created by ihelms on 26.11.2021
 */

public class SchoolYearLogic {
    PseudoClasses pseudoClasses;

    public SchoolYearLogic() {
        this.pseudoClasses = new PseudoClasses();
    }

    public Set<String> getSchoolYearValuesByUser() {
        Set<String> schoolYearDAOSet = new HashSet<>();

        //TODO: Repo-Aufruf
        schoolYearDAOSet.addAll(pseudoClasses.getSchoolYearValues());

        return schoolYearDAOSet;
    }

    public void addSchoolYear(SchoolYearDAO schoolYearDAO) {
        //TODO
    }
}
