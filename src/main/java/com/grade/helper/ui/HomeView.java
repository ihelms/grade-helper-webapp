package com.grade.helper.ui;

import com.grade.helper.businesslogic.logic.*;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;


@Route(HomeView.HOME_VIEW)
@SpringComponent
@UIScope
public class HomeView extends HeaderView {

    final static String HOME_VIEW = "home";

    @Autowired
    public HomeView(UserGradeService userGradeService,
                    SchoolYearService schoolYearService,
                    SubjectService subjectService,
                    UserService userService,
                    UserSchoolYearService userSchoolYearService) {
        super(userGradeService, schoolYearService, subjectService, userService, userSchoolYearService);

        VerticalLayout mainVerticalLayout = new VerticalLayout(new Label("Willkommen"));
        mainVerticalLayout.setWidthFull();
        mainVerticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        setContent(mainVerticalLayout);
    }
}
