package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.joined.UserSchoolYear;
import com.grade.helper.businesslogic.entities.simple.*;
import com.grade.helper.businesslogic.resource.SubjectAverage;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class SubjectAverageService {

    UserGradeService userGradeService;
    UserSchoolYearService userSchoolYearService;
    UserService userService;
    SchoolYearService schoolYearService;
    SubjectService subjectService;

    public SubjectAverageService(UserGradeService userGradeService,
                                 UserSchoolYearService userSchoolYearService,
                                 UserService userService,
                                 SchoolYearService schoolYearService,
                                 SubjectService subjectService
    ) {
        this.userGradeService = userGradeService;
        this.userSchoolYearService = userSchoolYearService;
        this.userService = userService;
        this.schoolYearService = schoolYearService;
        this.subjectService = subjectService;
    }

    public Set<SubjectAverage> getSubjectAverageForSchoolYear() {
        String schoolYear = VaadinSession.getCurrent().getAttribute("school_year").toString();
        SchoolYear currentSchoolYear = schoolYearService.getSchoolYearByValue(schoolYear);

        Set<SubjectAverage> subjectAverageSet = new HashSet<>();

        for (Subject subject : subjectService.getAll()) {
            UserSchoolYear userSchoolYear = userSchoolYearService.getUserSchoolYearByUserAndSchoolYear(
                    userService.getCurrentUser(),
                    currentSchoolYear
            );

            Set<Grade> gradeList = userGradeService.getAllGradesForSubjectAndSchoolYear(subject, userSchoolYear);

            double average = 0.0;
            for (Grade grade : gradeList) {
                average = average + (grade.getGrade() * grade.getPrioritisation());
            }
            SubjectAverage subjectAverage = new SubjectAverage(subject.getValue(), average);
            subjectAverageSet.add(subjectAverage);
        }

        return subjectAverageSet;
    }

}
