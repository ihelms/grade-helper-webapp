package com.grade.helper.ui;

import com.grade.helper.businesslogic.entities.enums.SUBJECT;
import com.grade.helper.businesslogic.entities.joined.UserSchoolYear;
import com.grade.helper.businesslogic.entities.simple.User;
import com.grade.helper.businesslogic.logic.*;
import com.grade.helper.ui.component.OverviewView;
import com.grade.helper.ui.component.subjects.*;
import com.grade.helper.ui.windows.ConfigurationWindow;
import com.grade.helper.ui.windows.SchoolYearWindow;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;


public abstract class HeaderView extends AppLayout {

    public static String TITLE = "Grade Helper";

    SubjectService subjectService;
    UserGradeService userGradeService;
    UserSchoolYearService userSchoolYearService;

    List<String> schoolYearStringList;
    List<UserSchoolYear> schoolYearList;

    ComboBox<String> comboBox;
    String currentUserName;
    User currentUser;

    public HeaderView(UserGradeService userGradeService,
                      SchoolYearService schoolYearService,
                      SubjectService subjectService,
                      UserService userService,
                      UserSchoolYearService userSchoolYearService) {
        this.subjectService = subjectService;
        this.userGradeService = userGradeService;
        this.userSchoolYearService = userSchoolYearService;

        currentUserName = userService.getCurrentUser().getUsername();
        currentUser = userService.getCurrentUser();
        setLists();

        Anchor logo = new Anchor("/home", TITLE);

        Button schoolYearButton = new Button();
        schoolYearButton.setIcon(VaadinIcon.PLUS.create());
        schoolYearButton.addClickListener(buttonClickEvent -> {
            SchoolYearWindow schoolYearWindow = new SchoolYearWindow(schoolYearService,
                    userService,
                    subjectService,
                    userSchoolYearService);
            schoolYearWindow.open();
            setLists();
            comboBox.setItems(schoolYearStringList);
            comboBox.setValue(String.valueOf(VaadinSession.getCurrent().getAttribute("school_year")));
        });

        comboBox = new ComboBox<>();
        comboBox.setWidth("25%");
        comboBox.setClearButtonVisible(true);
        comboBox.setItems(schoolYearStringList);
        comboBox.setValue(schoolYearStringList.get(schoolYearStringList.size() - 1));
        VaadinSession.getCurrent().setAttribute("school_year", comboBox.getValue());

        comboBox.addValueChangeListener(valueChanged -> {
            VaadinSession.getCurrent().setAttribute("school_year", valueChanged.getValue());
            comboBox.setValue(valueChanged.getValue());
            setLists();
        });

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

        HorizontalLayout buttonLayout = new HorizontalLayout(userProfileButton, logoutButton);
        buttonLayout.setWidth("250px");

        HorizontalLayout header = new HorizontalLayout(leftSideHorizontalLayout,
                schoolYearHorizontalLayout,
                buttonLayout);
        header.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        header.getElement().setAttribute("background-color", "gray");
        header.setWidthFull();
        header.setHeight("50px");
        header.addClassName("header");

        addToNavbar(header);
        addToDrawer(new VerticalLayout(getDrawerRouterLinks()));
    }

    private VerticalLayout getDrawerRouterLinks() {
        VerticalLayout drawerLayout = new VerticalLayout();
        drawerLayout.setWidthFull();
        drawerLayout.setHeight("85%");

        RouterLink overviewLink = new RouterLink("Übersicht", OverviewView.class);
        RouterLink link_1 = new RouterLink(SUBJECT.MATHE.getValue(), MatheView.class);
        RouterLink link_2 = new RouterLink(SUBJECT.DEUTSCH.getValue(), DeutschView.class);
        RouterLink link_3 = new RouterLink(SUBJECT.ENGLISCH.getValue(), EnglischView.class);
        RouterLink link_4 = new RouterLink(SUBJECT.SPANISCH.getValue(), SpanischView.class);
        RouterLink link_5 = new RouterLink(SUBJECT.FRANZOESISCH.getValue(), FranzoesischView.class);
        RouterLink link_6 = new RouterLink(SUBJECT.CHEMIE.getValue(), ChemieView.class);
        RouterLink link_7 = new RouterLink(SUBJECT.PHYSIK.getValue(), PhysikView.class);
        RouterLink link_8 = new RouterLink(SUBJECT.BIO.getValue(), BiologieView.class);
        RouterLink link_9 = new RouterLink(SUBJECT.RELI.getValue(), ReliView.class);
        RouterLink link_10 = new RouterLink(SUBJECT.ETHIK.getValue(), EthikView.class);
        RouterLink link_11 = new RouterLink(SUBJECT.IV.getValue(), IV_View.class);
        RouterLink link_12 = new RouterLink(SUBJECT.KUNST.getValue(), KunstView.class);
        RouterLink link_13 = new RouterLink(SUBJECT.SPORT.getValue(), SportView.class);
        RouterLink link_14 = new RouterLink(SUBJECT.BWL.getValue(), BWL_View.class);
        RouterLink link_15 = new RouterLink(SUBJECT.VWL.getValue(), VWL_View.class);

        drawerLayout.addAndExpand(overviewLink, link_1, link_2, link_3, link_4, link_5,
                link_6, link_7, link_8, link_9, link_10,
                link_11, link_12, link_13, link_14, link_15);

        return drawerLayout;
    }

    public void setLists() {
        List<String> helperList = new ArrayList<>();
        schoolYearList = userSchoolYearService.getAllSchoolYearsByUser(currentUser);
        schoolYearList.forEach(schoolYear -> helperList.add(schoolYear.getSchoolYearId().getValue()));
        schoolYearStringList = helperList.stream().sorted().collect(Collectors.toList());
    }
}
