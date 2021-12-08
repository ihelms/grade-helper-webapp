package com.grade.helper.ui.component;

import com.grade.helper.businesslogic.logic.SchoolYearService;
import com.grade.helper.businesslogic.logic.SubjectAverageService;
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

/**
 * created by ihelms on 25.11.2021
 */

@SuppressWarnings("unused")
@Route(OverviewView.OVERVIEW)
@SpringComponent
@UIScope
public class OverviewView extends HeaderView {

    final static String OVERVIEW = "overview";

    @Autowired
    public OverviewView(SchoolYearService schoolYearService) {
        super(schoolYearService);
        SubjectAverageService subjectAverageService = new SubjectAverageService();
        Set<SubjectAverage> subjectAverageSet = subjectAverageService.getSubjectAverageForSchoolYear();

        Grid<SubjectAverage> grid = new Grid<>();
        Grid.Column<SubjectAverage> subjectColumn = grid.addColumn(SubjectAverage::getSubject)
                .setAutoWidth(true)
                .setHeader("Fach");
        Grid.Column<SubjectAverage> averageColumn = grid.addColumn(SubjectAverage::getAverage)
                .setAutoWidth(true)
                .setHeader("Durchschnitt");
        grid.setDataProvider(DataProvider.ofCollection(subjectAverageSet));

        setContent(new VerticalLayout(grid));
    }
}
