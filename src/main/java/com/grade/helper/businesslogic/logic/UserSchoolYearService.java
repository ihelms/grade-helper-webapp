package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.joined.UserSchoolYear;
import com.grade.helper.businesslogic.entities.simple.User;
import com.grade.helper.businesslogic.repositories.UserSchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by ihelms on 10.12.2021
 */

@Service
public class UserSchoolYearService {

    UserSchoolRepository userSchoolRepository;

    public UserSchoolYearService(UserSchoolRepository userSchoolRepository) {
        this.userSchoolRepository = userSchoolRepository;
    }

    public List<UserSchoolYear> getAllSchoolYearsByUser() {
        userSchoolRepository.findAll().forEach(userSchoolYear -> {
            System.out.println(userSchoolYear.toString());
        });

        return userSchoolRepository.findAllByUserIdId(1L);

    }
}
