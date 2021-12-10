package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.joined.UserSchoolYear;
import com.grade.helper.businesslogic.entities.simple.SchoolYear;
import com.grade.helper.businesslogic.entities.simple.User;
import com.grade.helper.businesslogic.repositories.UserSchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@SuppressWarnings("OptionalGetWithoutIsPresent")
@Service
public class UserSchoolYearService {

    UserSchoolRepository userSchoolRepository;

    public UserSchoolYearService(UserSchoolRepository userSchoolRepository) {
        this.userSchoolRepository = userSchoolRepository;
    }

    public List<UserSchoolYear> getAllSchoolYearsByUser(User user) {
        return userSchoolRepository.findAllByUserId(user);
    }

    public void addUserSchoolYear(UserSchoolYear userSchoolYear) {
        userSchoolRepository.save(userSchoolYear);
    }

    public UserSchoolYear getUserSchoolYearByUserAndSchoolYear(User currentUser, SchoolYear selectedSchoolYear) {
        return userSchoolRepository.findByUserIdAndSchoolYearId(currentUser, selectedSchoolYear);
    }

    public UserSchoolYear findById(Long id) {
        Optional<UserSchoolYear> userSchoolYear = userSchoolRepository.findById(id);
        return userSchoolYear.get();
    }
}
