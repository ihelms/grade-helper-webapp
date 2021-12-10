package com.grade.helper.ui.component.subjects;

import com.grade.helper.businesslogic.entities.enums.SUBJECT;
import com.grade.helper.businesslogic.logic.*;
import com.grade.helper.ui.component.SubjectView;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * created by ihelms on 10.12.2021
 */

@Route(VWL_View.SUBJECT_VIEW)
@SpringComponent
@UIScope
public class VWL_View extends SubjectView {
    final static String SUBJECT_VIEW = "subject/vwl";

    @Autowired
    public VWL_View(SchoolYearService schoolYearService, SubjectService subjectService, UserGradeService userGradeService, GradeService gradeService, UserService userService, UserSchoolYearService userSchoolYearService) {
        super(schoolYearService, subjectService, userGradeService,
                gradeService, userService, userSchoolYearService, SUBJECT.BWL);
    }
}
