package com.grade.helper.ui;

import com.grade.helper.businesslogic.entities.enums.SUBJECT;
import com.grade.helper.businesslogic.entities.simple.SchoolYear;
import com.grade.helper.businesslogic.entities.simple.Subject;
import com.grade.helper.businesslogic.service.*;
import com.grade.helper.ui.component.OverviewView;
import com.grade.helper.ui.component.SubjectView;
import com.grade.helper.ui.windows.*;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;

import java.util.LinkedList;
import java.util.List;

import static com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;

/**
 * created by ihelms on 18.11.2021
 */
public abstract class HeaderView extends AppLayout {

    public static String TITLE = "Grade Helper";

    SubjectService subjectService;
    UserGradeService userGradeService;

    ComboBox<String> comboBox;
    SchoolYear selectedSchoolYear;
    String currentUserName;

    public HeaderView(SchoolYearService schoolYearService,
                      SubjectService subjectService,
                      UserGradeService userGradeService,
                      UserService userService) {
        this.subjectService = subjectService;
        this.userGradeService = userGradeService;

        currentUserName = userService.getAuthenticatedUser().getUsername();

        Anchor logo = new Anchor("/home", TITLE);

        Button schoolYearButton = new Button();
        schoolYearButton.setIcon(VaadinIcon.PLUS.create());
        schoolYearButton.addClickListener(buttonClickEvent -> {
            SchoolYearWindow schoolYearWindow = new SchoolYearWindow(schoolYearService,
                    userGradeService, userService, subjectService);
            schoolYearWindow.open();
        });

        comboBox = new ComboBox<>("");
        comboBox.setWidth("25%");
        comboBox.setClearButtonVisible(true);
        comboBox.setItems(userGradeService.getAllSchoolYearStringsByUser(currentUserName));
        comboBox.addValueChangeListener(valueChanged ->
                VaadinSession.getCurrent().setAttribute("school_year", valueChanged.getValue()));
        if (VaadinSession.getCurrent().getAttribute("school_year") != null) {
            comboBox.setValue(VaadinSession.getCurrent().getAttribute("school_year").toString());
            schoolYearButton.setIcon(VaadinIcon.EDIT.create());
        }

        HorizontalLayout leftSideHorizontalLayout = new HorizontalLayout(new DrawerToggle(), logo);
        leftSideHorizontalLayout.setWidthFull();
        leftSideHorizontalLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        HorizontalLayout schoolYearHorizontalLayout = new HorizontalLayout(comboBox, schoolYearButton);
        schoolYearHorizontalLayout.setWidthFull();

        Button userProfileButton = new Button(VaadinIcon.USER.create());
        userProfileButton.addClickListener(buttonClickEvent -> {
            ConfigurationWindow configurationWindow = new ConfigurationWindow(userService);
            configurationWindow.open();
        });

        Button logoutButton = new Button(VaadinIcon.EXIT.create());
        logoutButton.addClickListener(buttonClickEvent -> userService.logout());

        HorizontalLayout header = new HorizontalLayout(leftSideHorizontalLayout,
                schoolYearHorizontalLayout,
                userProfileButton,
                logoutButton);
        header.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        header.setWidthFull();
        header.setHeight("25%");
        header.addClassName("header");

        addToNavbar(header);
        addToDrawer(new VerticalLayout(getDrawerRouterLinks()));
    }

    private VerticalLayout getDrawerRouterLinks() {
        VerticalLayout drawerLayout = new VerticalLayout();
        drawerLayout.setWidthFull();
        drawerLayout.setHeight("85%");

        RouterLink overviewLink = new RouterLink("Übersicht", OverviewView.class);
        overviewLink.setHighlightCondition(HighlightConditions.sameLocation());

        drawerLayout.addAndExpand(overviewLink);

        for (SUBJECT item : SUBJECT.values()) {
            VaadinSession.getCurrent().setAttribute("subject", item.getValue());

            RouterLink subjectRouterLink = new RouterLink(item.getValue(), SubjectView.class);
            subjectRouterLink.setHighlightCondition(HighlightConditions.sameLocation());
            drawerLayout.addAndExpand(subjectRouterLink);
        }

        return drawerLayout;
    }
}
