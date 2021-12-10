package com.grade.helper.ui;

import com.grade.helper.businesslogic.logic.*;
import com.grade.helper.security.SecurityUtils;
import com.grade.helper.ui.login.LoginView;
import com.vaadin.flow.router.*;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@Route
@SpringComponent
@UIScope
public class MainView extends HeaderView implements BeforeEnterObserver {

    @Autowired
    public MainView(SchoolYearService schoolYearService,
                    SubjectService subjectService,
                    UserGradeService userGradeService,
                    UserService userService,
                    UserSchoolYearService userSchoolYearService) {
        super(userGradeService, schoolYearService, subjectService, userService, userSchoolYearService);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        beforeEnterEvent.forwardTo(SecurityUtils.isUserLoggedIn() ? HomeView.class : LoginView.class);
    }
}
