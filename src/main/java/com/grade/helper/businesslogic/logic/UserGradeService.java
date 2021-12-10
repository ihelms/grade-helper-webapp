package com.grade.helper.businesslogic.logic;

import com.grade.helper.Pseudoclass;
import com.grade.helper.businesslogic.entities.enums.SUBJECT;
import com.grade.helper.businesslogic.entities.joined.UserGrade;
import com.grade.helper.businesslogic.entities.joined.UserSchoolYear;
import com.grade.helper.businesslogic.entities.simple.*;
import com.grade.helper.businesslogic.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;


@Service
public class UserGradeService {

    UserGradeRepository userGradeRepository;
    SubjectRepository subjectRepository;
    UserRepository userRepository;
    SchoolYearRepository schoolYearRepository;
    GradeTypeRepository gradeTypeRepository;
    GradeRepository gradeRepository;
    UserSchoolRepository userSchoolRepository;

    @Autowired
    public UserGradeService(UserGradeRepository userGradeRepository,
                            SubjectRepository subjectRepository,
                            UserRepository userRepository,
                            SchoolYearRepository schoolYearRepository,
                            GradeTypeRepository gradeTypeRepository,
                            GradeRepository gradeRepository,
                            UserSchoolRepository userSchoolRepository) {
        this.userGradeRepository = userGradeRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.schoolYearRepository = schoolYearRepository;
        this.gradeTypeRepository = gradeTypeRepository;
        this.gradeRepository = gradeRepository;
        this.userSchoolRepository = userSchoolRepository;
    }

    public List<String> getAllSchoolYearStringsByUser(String username) {
        User user = userRepository.findUserDAOByUsername(username);

        List<String> schoolYears = new LinkedList<>();
        return schoolYears;
    }

    public List<Subject> getAllSubjectsBySchoolYearAndUser(Long schoolYearId, String username) {
        return new LinkedList<>();
    }

    public void addSchoolYear(SchoolYear schoolYear, User user) {
        //TODO: Adds all new or edited SchoolYears
    }


    public Set<Grade> getAllGradesForSubjectAndSchoolYear(SUBJECT subject, UserSchoolYear userSchoolYear) {
        Set<Grade> gradeList = new HashSet<>();
        List<UserGrade> userSchoolYearList = userGradeRepository.findAllByUserSchoolYearId(userSchoolYear);
        userSchoolYearList.forEach(userGrade -> {
            Grade grade = userGrade.getGradeId();
            if (Objects.equals(grade.getSubject().getValue(), subject.getValue())) {
                gradeList.add(grade);
            }
        });
        return gradeList;
    }

    //generating test data
    @PostConstruct
    public void populateTestData() {
        Pseudoclass pseudoclass = new Pseudoclass();
        pseudoclass.setRepositories(userGradeRepository, subjectRepository,
                userRepository, schoolYearRepository, gradeTypeRepository, gradeRepository, userSchoolRepository);
    }

}

