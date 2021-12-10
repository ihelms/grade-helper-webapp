package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.resource.SubjectAverage;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * created by ihelms on 02.12.2021
 */

@Service
public class SubjectAverageService {

    public Set<SubjectAverage> getSubjectAverageForSchoolYear() {
        return new HashSet<>();
    }

}
