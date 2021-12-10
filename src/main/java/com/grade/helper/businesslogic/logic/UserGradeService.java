package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.enums.SUBJECT;
import com.grade.helper.businesslogic.entities.joined.UserGrade;
import com.grade.helper.businesslogic.entities.joined.UserSchoolYear;
import com.grade.helper.businesslogic.entities.simple.*;
import com.grade.helper.businesslogic.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Set<Grade> getAllGradesForSubjectAndSchoolYear(Subject subject, UserSchoolYear userSchoolYear) {
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

}

