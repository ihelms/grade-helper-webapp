package com.grade.helper.ui.component;

import com.grade.helper.businesslogic.entities.simple.Grade;
import com.grade.helper.businesslogic.entities.simple.GradeType;
import com.grade.helper.businesslogic.service.*;
import com.grade.helper.ui.HeaderView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * created by ihelms on 25.11.2021
 */

@Route(SubjectView.SUBJECT_VIEW)
@SpringComponent
@UIScope
public class SubjectView extends HeaderView {

    final static String SUBJECT_VIEW = "subject";

    private final Binder<Grade> binder;

    private Grid<Grade> grid;
    private DataProvider<Grade, ?> dataProvider;

    private final Set<Grade> gradeResourceSet;
    private Grade selectedGrade;

    private VerticalLayout editContainer;
    private Button newGradeButton;
    private Button addButton;
    private TextField idTextField;
    private TextField dateTextField;

    private final GradeService gradeService;

    @Autowired
    public SubjectView(SchoolYearService schoolYearService,
                       SubjectService subjectService,
                       UserGradeService userGradeService,
                       GradeService gradeService,
                       UserService userService) {
        super(schoolYearService, subjectService, userGradeService, userService);

        this.gradeService = gradeService;
        this.binder = new Binder<>();

        String subjectName = String.valueOf(VaadinSession.getCurrent().getAttribute("subject"));

        //TODO
        this.gradeResourceSet = new HashSet<>();

        setContent(setView());
    }

    private VerticalLayout setView() {
        grid = new Grid<>();
        dataProvider = DataProvider.ofCollection(gradeResourceSet);

        editContainer = new VerticalLayout();
        editContainer.setSizeFull();
        editContainer.setSpacing(false);
        editContainer.setMargin(false);
        editContainer.setVisible(false);
        editContainer.setClassName("grid-edit-container");
        //TODO: Add style to editContainer

        createEditContainer();

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
        Grid.Column<Grade> gradePrioritisationColumn = grid.addColumn(Grade::getPrioritisation)
                .setHeader("Gewichtung")
                .setSortable(true)
                .setAutoWidth(true)
                .setKey("gradePriorisation");
        Grid.Column<Grade> dateColumn = grid.addColumn(Grade::getDate)
                .setHeader("Datum")
                .setSortable(true)
                .setAutoWidth(true)
                .setKey("date");
        grid.addComponentColumn(item -> new Button(VaadinIcon.TRASH.create(),
                        click -> {
                            //TODO
                            gradeResourceSet.remove(item);
                            grid.getDataProvider().refreshAll();
                        }))
                .setWidth("10%");

        grid.setDataProvider(DataProvider.ofCollection(gradeResourceSet));
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addSelectionListener(this::conditionSelected);

        newGradeButton = new Button("Neu", buttonClickEvent -> createNewGrade());

        HorizontalLayout buttonLayout = new HorizontalLayout(newGradeButton);
        buttonLayout.setWidthFull();
        buttonLayout.setAlignItems(FlexComponent.Alignment.END);

        return new VerticalLayout(grid, editContainer, buttonLayout);
    }

    private void createEditContainer() {
        idTextField = new TextField("Id");
        idTextField.setReadOnly(true);
        idTextField.setWidth("500px");

        ComboBox<GradeType> gradeTypeComboBox = new ComboBox<>("Notentyp");
        gradeTypeComboBox.setItems(gradeService.getAllGradeTypes());
        gradeTypeComboBox.setWidth("500px");

        ComboBox<Integer> gradeComboBox = new ComboBox<>("Note");
        gradeComboBox.setItems(List.of(1, 2, 3, 4, 5, 6));
        gradeComboBox.setWidth("500px");

        TextField priorisationTextField = new TextField("Gewichtung");
        priorisationTextField.setWidth("500px");

        dateTextField = new TextField("Datum");
        dateTextField.setReadOnly(true);
        dateTextField.setWidth("500px");

        //Binder
        binder.forField(idTextField)
                .withConverter(new StringToLongConverter(idTextField.getValue()))
                .bind(Grade::getId, Grade::setId);
        binder.forField(gradeTypeComboBox)
                .bind(Grade::getGrade_type, Grade::setGrade_type);
        binder.forField(gradeComboBox)
                .bind(Grade::getGrade, Grade::setGrade);
        binder.forField(priorisationTextField)
                .withConverter(new StringToDoubleConverter(priorisationTextField.getValue()))
                .bind(Grade::getPrioritisation, Grade::setPrioritisation);
        //binder.forField(dateTextField)
        //      .withConverter()
        //    .bind(GradeDAO::getDate, GradeDAO::setDate);

        //ContentLayout
        HorizontalLayout lineOneLayout = new HorizontalLayout(idTextField, gradeTypeComboBox);
        lineOneLayout.setWidthFull();

        HorizontalLayout twoOneLayout = new HorizontalLayout(gradeComboBox, priorisationTextField);
        twoOneLayout.setWidthFull();

        HorizontalLayout threeOneLayout = new HorizontalLayout(dateTextField);
        threeOneLayout.setWidthFull();

        VerticalLayout contentLayout = new VerticalLayout();
        contentLayout.add(lineOneLayout, twoOneLayout, threeOneLayout);

        //Button
        addButton = new Button("Hinzufügen", buttonClickEvent -> addGrade());
        Button cancelButton = new Button("Abbrechen", buttonClickEvent -> cancelEditGrade());

        HorizontalLayout buttonLayout = new HorizontalLayout(addButton, cancelButton);
        buttonLayout.setWidthFull();
        buttonLayout.setAlignItems(FlexComponent.Alignment.END);

        VerticalLayout verticalLayout = new VerticalLayout(contentLayout, buttonLayout);
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.END, buttonLayout);

        editContainer.add(verticalLayout);
    }

    private void conditionSelected(SelectionEvent<Grid<Grade>, Grade> selection) {
        if (selection.getFirstSelectedItem().isPresent()) {
            selectedGrade = selection.getFirstSelectedItem().get();
            binder.readBean(selectedGrade);
            editContainer.setVisible(true);
            newGradeButton.setVisible(false);
            addButton.setText("Update");
        } else {
            binder.removeBean();
            selectedGrade = null;
            editContainer.setVisible(false);
            newGradeButton.setVisible(true);
        }
    }

    private void addGrade() {
        if (binder.validate().isOk()) {
            if (selectedGrade == null) {
                Grade grade = new Grade();
                binder.writeBeanIfValid(grade);
                gradeResourceSet.add(grade);
            } else {
                gradeResourceSet.remove(selectedGrade);
                binder.writeBeanIfValid(selectedGrade);
                gradeResourceSet.add(selectedGrade);
            }
            editContainer.setVisible(false);
            dataProvider = DataProvider.ofCollection(gradeResourceSet);
            grid.setDataProvider(dataProvider);
            grid.setHeightByRows(true);
            dataProvider.refreshAll();
        } else {
            binder.validate();
        }
        newGradeButton.setVisible(true);
    }

    private void cancelEditGrade() {
        selectedGrade = null;
        editContainer.setVisible(false);
        newGradeButton.setVisible(true);
    }

    private void createNewGrade() {
        editContainer.setVisible(true);
        selectedGrade = null;
        grid.deselectAll();
        binder.readBean(null);

        addButton.setText("Hinzufügen");

        editContainer.setVisible(true);
        newGradeButton.setVisible(false);
    }
}
