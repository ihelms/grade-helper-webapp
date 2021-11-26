package com.grade.helper.ui;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * created by ihelms on 18.11.2021
 */

@Route(HomeView.HOME_VIEW)
public class HomeView extends HeaderView {

    final static String HOME_VIEW = "home";

    public HomeView() {
        VerticalLayout mainVerticalLayout = new VerticalLayout(new Label("Willkommen"));
        mainVerticalLayout.setWidthFull();
        mainVerticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        setContent(mainVerticalLayout);
    }
}
