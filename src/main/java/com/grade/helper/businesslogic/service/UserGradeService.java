package com.grade.helper.businesslogic.service;

import com.grade.helper.Pseudoclass;
import com.grade.helper.businesslogic.entities.joined.UserGrade;
import com.grade.helper.businesslogic.entities.simple.*;
import com.grade.helper.businesslogic.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

/**
 * created by ihelms on 09.12.2021
 */

@Service
public class UserGradeService {

    UserGradeRepository userGradeRepository;
    SubjectRepository subjectRepository;
    UserRepository userRepository;
    SchoolYearRepository schoolYearRepository;
    GradeTypeRepository gradeTypeRepository;
    GradeRepository gradeRepository;

    @Autowired
    public UserGradeService(UserGradeRepository userGradeRepository,
                            SubjectRepository subjectRepository,
                            UserRepository userRepository,
                            SchoolYearRepository schoolYearRepository,
                            GradeTypeRepository gradeTypeRepository,
                            GradeRepository gradeRepository) {
        this.userGradeRepository = userGradeRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.schoolYearRepository = schoolYearRepository;
        this.gradeTypeRepository = gradeTypeRepository;
        this.gradeRepository = gradeRepository;
    }

    public List<String> getAllSchoolYearStringsByUser(String username) {
        User user = userRepository.findUserDAOByUsername(username);

        List<String> schoolYears = new LinkedList<>();
        List<UserGrade> userGrades = userGradeRepository.getUserGradeDAOSByUserId(user);
        userGrades.forEach(userGrade -> schoolYears.add(userGrade.getSchoolYearId().getValue()));

        return schoolYears;
    }

    public List<Subject> getAllSubjectsBySchoolYearAndUser(Long schoolYearId, String username) {
        User user = userRepository.findUserDAOByUsername(username);
        SchoolYear schoolYear = schoolYearRepository.findSchoolYearDAOById(schoolYearId);

        return new LinkedList<>();
    }

    public void addSchoolYear(SchoolYear schoolYear, User user) {
        //TODO: Adds all new or edited SchoolYears
    }

    //generating test data
    @PostConstruct
    public void populateTestData() {
        Pseudoclass pseudoclass = new Pseudoclass();
        pseudoclass.setRepositories(userGradeRepository, subjectRepository,
                userRepository, schoolYearRepository, gradeTypeRepository, gradeRepository);
    }

}

