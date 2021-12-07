package com.grade.helper.ui;

import com.grade.helper.businesslogic.logic.SchoolYearService;
import com.vaadin.flow.router.*;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * created by ihelms on 18.11.2021
 */

@Route
@SpringComponent
@UIScope
public class MainView extends HeaderView implements BeforeEnterObserver {

    @Autowired
    public MainView(SchoolYearService schoolYearService) {
        super(schoolYearService);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        beforeEnterEvent.forwardTo(HomeView.class);

        //TODO: Uncomment
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //beforeEnterEvent.forwardTo(authentication != null ? HomeView.class : LoginView.class);
    }
}
