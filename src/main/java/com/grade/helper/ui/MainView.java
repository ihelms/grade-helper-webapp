package com.grade.helper.ui;

import com.grade.helper.businesslogic.service.*;
import com.grade.helper.security.SecurityUtils;
import com.grade.helper.ui.login.LoginView;
import com.vaadin.flow.router.*;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * created by ihelms on 18.11.2021
 */

@Route
@SpringComponent
@UIScope
public class MainView extends HeaderView implements BeforeEnterObserver {

    @Autowired
    public MainView(SchoolYearService schoolYearService,
                    SubjectService subjectService,
                    UserGradeService userGradeService,
                    UserService userService) {
        super(schoolYearService, subjectService, userGradeService, userService);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        beforeEnterEvent.forwardTo(SecurityUtils.isUserLoggedIn() ? HomeView.class : LoginView.class);
    }
}
