package com.grade.helper.ui.component;

import com.grade.helper.businesslogic.entities.Grade;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;

import java.util.HashSet;
import java.util.Set;

/**
 * created by ihelms on 25.11.2021
 */

public class SchoolYearView extends VerticalLayout {

    private Set<Grade> gradeResourceSet;

    public SchoolYearView(Set<Grade> gradeResourceSet) {
        this.gradeResourceSet = gradeResourceSet;

        add(setView());
    }

    private VerticalLayout setView() {
        Grid<Grade> grid = new Grid<>();

        Grid.Column<Grade> gradeTypeColumn = grid.addColumn(Grade::getGrade_type)
                .setHeader("Notenart")
                .setSortable(true)
                .setAutoWidth(true)
                .setKey("gradeType");
        Grid.Column<Grade> gradeColumn = grid.addColumn(Grade::getGrade)
                .setHeader("Note")
                .setSortable(true)
                .setAutoWidth(true)
                .setKey("grade");
        Grid.Column<Grade> gradePriorisationColumn = grid.addColumn(Grade::getPrioritisation)
                .setHeader("Gewichtung")
                .setSortable(true)
                .setAutoWidth(true)
                .setKey("gradePriorisation");
        Grid.Column<Grade> dateColumn = grid.addColumn(Grade::getDate)
                .setHeader("Datum")
                .setSortable(true)
                .setAutoWidth(true)
                .setKey("date");
        grid.addComponentColumn(item -> new Button(VaadinIcon.TRASH.create(), click -> {
                    gradeResourceSet.remove(item);
                    grid.getDataProvider().refreshAll();
                }))
                .setWidth("10%");

        grid.setDataProvider(DataProvider.ofCollection(gradeResourceSet));
        return new VerticalLayout(grid);
    }
}
