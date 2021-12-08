package com.grade.helper.ui;

import com.grade.helper.businesslogic.enums.SUBJECT;
import com.grade.helper.businesslogic.logic.SchoolYearService;
import com.grade.helper.businesslogic.logic.SubjectService;
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

import java.util.List;

import static com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;

/**
 * created by ihelms on 18.11.2021
 */
public abstract class HeaderView extends AppLayout {

    public static String TITLE = "Grade Helper";

    public HeaderView(SchoolYearService schoolYearService) {
        Anchor logo = new Anchor("/home", TITLE);

        ComboBox<String> comboBox = new ComboBox<>("");
        comboBox.setWidth("25%");
        comboBox.setClearButtonVisible(true);
        comboBox.setItems(schoolYearService.getSchoolYearValuesByUser());
        comboBox.addValueChangeListener(valueChanged ->
                VaadinSession.getCurrent().setAttribute("school_year", valueChanged.getValue()));
        if (VaadinSession.getCurrent().getAttribute("school_year") != null) {
            comboBox.setValue(VaadinSession.getCurrent().getAttribute("school_year").toString());
        }

        Button addSchoolYearButton = new Button(VaadinIcon.PLUS.create());
        addSchoolYearButton.addClickListener(buttonClickEvent -> {
            AddSchoolYearWindow addSchoolYearWindow = new AddSchoolYearWindow(schoolYearService);
            addSchoolYearWindow.open();
        });

        HorizontalLayout leftSideHorizontalLayout = new HorizontalLayout(new DrawerToggle(), logo);
        leftSideHorizontalLayout.setWidthFull();
        leftSideHorizontalLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        HorizontalLayout schoolYearHorizontalLayout = new HorizontalLayout(comboBox, addSchoolYearButton);
        schoolYearHorizontalLayout.setWidthFull();

        Button userProfileButton = new Button(VaadinIcon.USER.create());
        userProfileButton.addClickListener(buttonClickEvent -> {
            ConfigurationWindow configurationWindow = new ConfigurationWindow();
            configurationWindow.open();
        });

        HorizontalLayout header = new HorizontalLayout(leftSideHorizontalLayout, schoolYearHorizontalLayout, userProfileButton);
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

        SubjectService subjectService = new SubjectService();
        List<SUBJECT> subjectList = subjectService.getSubjectForSchoolYear();
        for (SUBJECT item : subjectList) {
            VaadinSession.getCurrent().setAttribute("subject", item.toString());

            RouterLink subjectRouterLink = new RouterLink(item.toString(), SubjectView.class);
            subjectRouterLink.setHighlightCondition(HighlightConditions.sameLocation());
            drawerLayout.addAndExpand(subjectRouterLink);
        }

        Button addSubjectButton = new Button("Schulfach hinzufügen");
        addSubjectButton.addClickListener(buttonClickEvent -> {
            AddSubjectWindow addSubjectWindow = new AddSubjectWindow();
            addSubjectWindow.open();
        });

        HorizontalLayout buttonLayout = new HorizontalLayout(addSubjectButton);
        buttonLayout.setHeight("15%");

        return new VerticalLayout(drawerLayout, buttonLayout);
    }
}
