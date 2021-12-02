package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.enums.SUBJECT;

import java.util.List;

/**
 * created by ihelms on 26.11.2021
 */

public class SubjectLogic {

    public List<SUBJECT> getSubjectForSchoolYear() {
        return List.of(SUBJECT.BIOLOGIE, SUBJECT.BWL);
    }

    public void addSubject(SUBJECT subject) {
        //TODO
    }
}
