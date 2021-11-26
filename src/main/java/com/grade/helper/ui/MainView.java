package com.grade.helper.ui;

import com.vaadin.flow.router.*;

/**
 * created by ihelms on 18.11.2021
 */

@Route
public class MainView extends HeaderView implements BeforeEnterObserver {

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        beforeEnterEvent.forwardTo(HomeView.class);
    }
}
