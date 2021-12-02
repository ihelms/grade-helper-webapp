package com.grade.helper.ui.component;

import com.grade.helper.businesslogic.logic.SubjectAverageLogic;
import com.grade.helper.businesslogic.resource.SubjectAverage;
import com.grade.helper.ui.HeaderView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;

import java.util.Set;

/**
 * created by ihelms on 25.11.2021
 */

@SuppressWarnings("unused")
@Route(OverviewView.OVERVIEW)
public class OverviewView extends HeaderView {

    final static String OVERVIEW = "overview";

    public OverviewView() {
        SubjectAverageLogic subjectAverageLogic = new SubjectAverageLogic();
        Set<SubjectAverage> subjectAverageSet = subjectAverageLogic.getSubjectAverageForSchoolYear();

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
