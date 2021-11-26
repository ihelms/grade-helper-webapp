package com.grade.helper.ui.component;

import com.grade.helper.businesslogic.entities.GradeDAO;
import com.grade.helper.ui.HeaderView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;

import java.util.Set;

/**
 * created by ihelms on 25.11.2021
 */

public class SchoolYearView extends HeaderView {

    private Set<GradeDAO> gradeDAOResourceSet;

    public SchoolYearView(Set<GradeDAO> gradeDAOResourceSet) {
        this.gradeDAOResourceSet = gradeDAOResourceSet;

        setContent(setView());
    }

    private VerticalLayout setView() {
        Grid<GradeDAO> grid = new Grid<>();

        Grid.Column<GradeDAO> gradeTypeColumn = grid.addColumn(GradeDAO::getGrade_type)
                .setHeader("Notenart")
                .setSortable(true)
                .setAutoWidth(true)
                .setKey("gradeType");
        Grid.Column<GradeDAO> gradeColumn = grid.addColumn(GradeDAO::getGrade)
                .setHeader("Note")
                .setSortable(true)
                .setAutoWidth(true)
                .setKey("grade");
        Grid.Column<GradeDAO> gradePriorisationColumn = grid.addColumn(GradeDAO::getPrioritisation)
                .setHeader("Gewichtung")
                .setSortable(true)
                .setAutoWidth(true)
                .setKey("gradePriorisation");
        Grid.Column<GradeDAO> dateColumn = grid.addColumn(GradeDAO::getDate)
                .setHeader("Datum")
                .setSortable(true)
                .setAutoWidth(true)
                .setKey("date");
        grid.addComponentColumn(item -> new Button(VaadinIcon.TRASH.create(), click -> {
                    gradeDAOResourceSet.remove(item);
                    grid.getDataProvider().refreshAll();
                }))
                .setWidth("10%");

        grid.setDataProvider(DataProvider.ofCollection(gradeDAOResourceSet));
        return new VerticalLayout(grid);
    }
}
