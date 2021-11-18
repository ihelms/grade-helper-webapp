package com.grade.helper.ui;

import com.grade.helper.ui.login.LoginView;
import com.vaadin.flow.router.*;

/**
 * created by ihelms on 18.11.2021
 */

@Route
public class MainView implements BeforeEnterObserver {

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        beforeEnterEvent.forwardTo(HomeView.class);
    }
}
