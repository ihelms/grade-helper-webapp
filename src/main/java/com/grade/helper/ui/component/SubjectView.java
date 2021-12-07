package com.grade.helper.ui.component;

import com.grade.helper.businesslogic.entities.GradeDAO;
import com.grade.helper.businesslogic.enums.GRADE_TYPE;
import com.grade.helper.businesslogic.enums.SUBJECT;
import com.grade.helper.businesslogic.logic.GradeService;
import com.grade.helper.businesslogic.logic.SchoolYearService;
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

import java.util.List;
import java.util.Set;

/**
 * created by ihelms on 25.11.2021
 */

@Route(SubjectView.SUBJECT_VIEW)
@SpringComponent
@UIScope
public class SubjectView extends HeaderView {

    final static String SUBJECT_VIEW = "subject";

    private final Binder<GradeDAO> binder;

    private Grid<GradeDAO> grid;
    private DataProvider<GradeDAO, ?> dataProvider;

    private final Set<GradeDAO> gradeDAOResourceSet;
    private GradeDAO selectedGrade;

    private VerticalLayout editContainer;
    private Button newGradeButton;
    private Button addButton;
    private TextField idTextField;
    private TextField dateTextField;

    @Autowired
    public SubjectView(SchoolYearService schoolYearService, GradeService gradeService) {
        super(schoolYearService);

        this.binder = new Binder<>();

        String subjectName = String.valueOf(VaadinSession.getCurrent().getAttribute("subject"));
        this.gradeDAOResourceSet = gradeService.getGradesBySubjectAndYear(SUBJECT.valueOf(String.valueOf(subjectName)));

        setContent(setView());
    }

    private VerticalLayout setView() {
        grid = new Grid<>();
        dataProvider = DataProvider.ofCollection(gradeDAOResourceSet);

        editContainer = new VerticalLayout();
        editContainer.setSizeFull();
        editContainer.setSpacing(false);
        editContainer.setMargin(false);
        editContainer.setVisible(false);
        editContainer.setClassName("grid-edit-container");
        //TODO: Add style to editContainer

        createEditContainer();

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
        Grid.Column<GradeDAO> gradePrioritisationColumn = grid.addColumn(GradeDAO::getPrioritisation)
                .setHeader("Gewichtung")
                .setSortable(true)
                .setAutoWidth(true)
                .setKey("gradePriorisation");
        Grid.Column<GradeDAO> dateColumn = grid.addColumn(GradeDAO::getDate)
                .setHeader("Datum")
                .setSortable(true)
                .setAutoWidth(true)
                .setKey("date");
        grid.addComponentColumn(item -> new Button(VaadinIcon.TRASH.create(),
                        click -> {
                            //TODO
                            gradeDAOResourceSet.remove(item);
                            grid.getDataProvider().refreshAll();
                        }))
                .setWidth("10%");

        grid.setDataProvider(DataProvider.ofCollection(gradeDAOResourceSet));
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

        ComboBox<GRADE_TYPE> gradeTypeComboBox = new ComboBox<>("Notentyp");
        gradeTypeComboBox.setItems(GRADE_TYPE.values());
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
                .bind(GradeDAO::getId, GradeDAO::setId);
        binder.forField(gradeTypeComboBox)
                .bind(GradeDAO::getGrade_type, GradeDAO::setGrade_type);
        binder.forField(gradeComboBox)
                .bind(GradeDAO::getGrade, GradeDAO::setGrade);
        binder.forField(priorisationTextField)
                .withConverter(new StringToDoubleConverter(priorisationTextField.getValue()))
                .bind(GradeDAO::getPrioritisation, GradeDAO::setPrioritisation);
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

    private void conditionSelected(SelectionEvent<Grid<GradeDAO>, GradeDAO> selection) {
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
                GradeDAO gradeDAO = new GradeDAO();
                binder.writeBeanIfValid(gradeDAO);
                gradeDAOResourceSet.add(gradeDAO);
            } else {
                gradeDAOResourceSet.remove(selectedGrade);
                binder.writeBeanIfValid(selectedGrade);
                gradeDAOResourceSet.add(selectedGrade);
            }
            editContainer.setVisible(false);
            dataProvider = DataProvider.ofCollection(gradeDAOResourceSet);
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

        idTextField.setValue("Auto-Generated");
        dateTextField.setValue("Will be generated");
        addButton.setText("Hinzufügen");

        editContainer.setVisible(true);
        newGradeButton.setVisible(false);
    }
}
