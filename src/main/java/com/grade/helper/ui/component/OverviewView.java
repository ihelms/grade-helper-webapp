package com.grade.helper.ui.component;

import com.grade.helper.businesslogic.logic.*;
import com.grade.helper.businesslogic.resource.SubjectAverage;
import com.grade.helper.ui.HeaderView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;


@Route(OverviewView.OVERVIEW)
@SpringComponent
@UIScope
public class OverviewView extends HeaderView {

    final static String OVERVIEW = "overview";

    @Autowired
    public OverviewView(SchoolYearService schoolYearService,
                        SubjectService subjectService,
                        UserGradeService userGradeService,
                        UserService userService,
                        UserSchoolYearService userSchoolYearService,
                        SubjectAverageService subjectAverageService) {
        super(userGradeService, schoolYearService, subjectService, userService, userSchoolYearService);

        Set<SubjectAverage> subjectAverageSet = subjectAverageService.getSubjectAverageForSchoolYear();

        Grid<SubjectAverage> grid = new Grid<>();
        grid.addColumn(SubjectAverage::getSubject)
                .setAutoWidth(true)
                .setHeader("Fach");
        grid.addColumn(SubjectAverage::getAverage)
                .setAutoWidth(true)
                .setHeader("Durchschnitt");
        grid.setDataProvider(DataProvider.ofCollection(subjectAverageSet));
        grid.setHeightByRows(true);

        setContent(new VerticalLayout(grid));
    }
}
