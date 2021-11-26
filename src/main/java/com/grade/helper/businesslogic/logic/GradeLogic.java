package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.GradeDAO;
import com.grade.helper.businesslogic.enums.SUBJECT;
import com.grade.helper.businesslogic.repositories.PseudoClasses;

import java.util.Set;

/**
 * created by ihelms on 26.11.2021
 */

public class GradeLogic {

    public Set<GradeDAO> getGradesBySubjectAndYear(SUBJECT subject) {
        PseudoClasses pseudoClasses = new PseudoClasses();
        return pseudoClasses.getGradesForSubject(subject);
    }

}
