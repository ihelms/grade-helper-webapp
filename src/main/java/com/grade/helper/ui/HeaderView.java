package com.grade.helper.ui;

import com.grade.helper.businesslogic.entities.SchoolYearDAO;
import com.grade.helper.ui.component.OverviewView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

import static com.vaadin.flow.component.orderedlayout.FlexComponent.*;

/**
 * created by ihelms on 18.11.2021
 */

public abstract class HeaderView extends AppLayout {
    public static String TITLE = "Grade Helper";

    public HeaderView() {
        Anchor logo = new Anchor("/home", TITLE);
        Anchor logout = new Anchor("logout", "Log out");

        ComboBox<SchoolYearDAO> comboBox = new ComboBox<>("");
        comboBox.setClearButtonVisible(true);

        //TODO: CHANGE
        comboBox.setItems();

        Button addSchoolYearButton = new Button(VaadinIcon.PLUS.create());
        addSchoolYearButton.addClickListener(buttonClickEvent -> {
            //TODO: ADD SCHOOL_YEAR
        });

        HorizontalLayout leftSideHorizontalLayout = new HorizontalLayout(new DrawerToggle(), logo);
        leftSideHorizontalLayout.setWidthFull();
        leftSideHorizontalLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        HorizontalLayout schoolYearHorizontalLayout = new HorizontalLayout(comboBox, addSchoolYearButton);
        schoolYearHorizontalLayout.setWidthFull();

        HorizontalLayout userLayout = new HorizontalLayout(logout);

        HorizontalLayout header = new HorizontalLayout(leftSideHorizontalLayout, schoolYearHorizontalLayout, userLayout);
        header.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        header.setWidthFull();
        header.addClassName("header");

        //RouterLinks
        RouterLink overviewLink = new RouterLink("Übersicht", OverviewView.class);
        overviewLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToNavbar(header);
        addToDrawer(new VerticalLayout(overviewLink));
    }
}
