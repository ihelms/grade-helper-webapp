package com.grade.helper.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

/**
 * created by ihelms on 18.11.2021
 */

public abstract class ApplicationView extends AppLayout {
    public static String TITLE = "Grade Helper";

    public ApplicationView() {
        H1 logo = new H1("Service");
        logo.addClassName("logo");

        Anchor logout = new Anchor("logout", "Log out");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.addClassName("header");
        addToNavbar(header);

        /*
        RouterLink customerLink = new RouterLink("Customer", CustomerView.class);
        RouterLink adminLink = new RouterLink("Admin", AdminView.class);

        customerLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(customerLink, adminLink));
        */
    }
}
