package com.grade.helper.ui.component;

import com.grade.helper.businesslogic.entities.enums.SUBJECT;
import com.grade.helper.businesslogic.entities.joined.UserSchoolYear;
import com.grade.helper.businesslogic.entities.simple.*;
import com.grade.helper.businesslogic.logic.*;
import com.grade.helper.ui.HeaderView;
import com.grade.helper.ui.validator.PlainStringToLongIdConverter;
import com.grade.helper.ui.validator.StringToGradeTypeConverter;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.*;


@Route(SubjectView.SUBJECT_VIEW)
public abstract class SubjectView extends HeaderView {

    final static String SUBJECT_VIEW = "subject";

    private final Binder<Grade> binder;

    private Grid<Grade> grid;
    private DataProvider<Grade, ?> dataProvider;

    private final Set<Grade> gradeResourceSet;
    private final Set<Grade> lastYearGradeResourceSet;
    private Grade selectedGrade;
    private final Subject selectedSubject;
    private final UserSchoolYear currentUserSchoolYear;

    HorizontalLayout improvementLayout;
    Button improvementButton;
    private VerticalLayout editContainer;
    private Button newGradeButton;
    private Button addButton;
    private TextField idTextField;

    private final GradeService gradeService;
    private final GradeTypeService gradeTypeService;
    private final UserGradeService userGradeService;

    public SubjectView(SchoolYearService schoolYearService,
                       SubjectService subjectService,
                       UserGradeService userGradeService,
                       GradeService gradeService,
                       UserService userService,
                       UserSchoolYearService userSchoolYearService,
                       SUBJECT subject,
                       GradeTypeService gradeTypeService) {
        super(userGradeService, schoolYearService, subjectService, userService, userSchoolYearService);

        this.gradeService = gradeService;
        this.gradeTypeService = gradeTypeService;
        this.userGradeService = userGradeService;
        this.binder = new Binder<>();

        VaadinSession.getCurrent().setAttribute("subject", subject.getValue());

        String schoolYear = String.valueOf(VaadinSession.getCurrent().getAttribute("school_year"));
        User currentUser = userService.getCurrentUser();
        SchoolYear selectedSchoolYear = schoolYearService.getSchoolYearByValue(schoolYear);
        UserSchoolYear lastUserSchoolYear = userSchoolYearService.getUserSchoolYearByUserAndSchoolYear(currentUser,
                schoolYearService.getSchoolYearById(selectedSchoolYear.getId() - 1));
        currentUserSchoolYear = userSchoolYearService.getUserSchoolYearByUserAndSchoolYear(currentUser, selectedSchoolYear);
        selectedSubject = subjectService.getSubjectOfSubjectEnum(subject);

        this.gradeResourceSet = userGradeService.getAllGradesForSubjectAndSchoolYear(selectedSubject, currentUserSchoolYear);
        if (lastUserSchoolYear != null) {
            this.lastYearGradeResourceSet = userGradeService.getAllGradesForSubjectAndSchoolYear(selectedSubject,
                    lastUserSchoolYear);
        } else {
            this.lastYearGradeResourceSet = new HashSet<>();
        }

        setContent(setView());
    }

    private VerticalLayout setView() {
        setImprovementLayout();
        improvementLayout = new HorizontalLayout(improvementButton);
        improvementLayout.setAlignSelf(FlexComponent.Alignment.END, improvementButton);
        improvementLayout.setWidthFull();

        grid = new Grid<>();
        dataProvider = DataProvider.ofCollection(gradeResourceSet);

        editContainer = new VerticalLayout();
        editContainer.setSizeFull();
        editContainer.setSpacing(false);
        editContainer.setMargin(false);
        editContainer.setVisible(false);

        createEditContainer();

        grid.addColumn(grade -> grade.getGrade_type().getGradeType())
                .setHeader("Notenart")
                .setSortable(true)
                .setAutoWidth(true)
                .setKey("gradeType");
        grid.addColumn(Grade::getGrade)
                .setHeader("Note")
                .setSortable(true)
                .setAutoWidth(true)
                .setKey("grade");
        grid.addColumn(Grade::getPrioritisation)
                .setHeader("Gewichtung")
                .setSortable(true)
                .setAutoWidth(true)
                .setKey("gradePriorisation");
        grid.addComponentColumn(grade -> new Button(VaadinIcon.TRASH.create(),
                        click -> {
                            userGradeService.removeGrade(grade);
                            gradeService.removeGrade(grade);
                            gradeResourceSet.remove(grade);
                            grid.getDataProvider().refreshAll();
                            setImprovementLayout();
                        }))
                .setWidth("10%");

        grid.setDataProvider(DataProvider.ofCollection(gradeResourceSet));
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addSelectionListener(this::conditionSelected);
        grid.setHeightByRows(true);

        openErrorMessageIfPrioIsToHigh();

        newGradeButton = new Button("Neu", buttonClickEvent -> createNewGrade());

        HorizontalLayout buttonLayout = new HorizontalLayout(newGradeButton);
        buttonLayout.setWidthFull();
        buttonLayout.setAlignItems(FlexComponent.Alignment.END);

        return new VerticalLayout(improvementLayout, new H4("Notenübersicht"), grid, editContainer, buttonLayout);
    }

    private void createEditContainer() {
        idTextField = new TextField("Id");
        idTextField.setReadOnly(true);
        idTextField.setWidth("500px");

        ComboBox<String> gradeTypeComboBox = new ComboBox<>("Notentyp");
        gradeTypeComboBox.setItems(gradeService.getAllGradeTypesAsString());
        gradeTypeComboBox.setWidth("500px");

        ComboBox<Integer> gradeComboBox = new ComboBox<>("Note");
        gradeComboBox.setItems(List.of(1, 2, 3, 4, 5, 6));
        gradeComboBox.setWidth("500px");

        ComboBox<Double> priorisationTextField = new ComboBox<>("Gewichtung");
        priorisationTextField.setItems(List.of(1.0, 0.8, 0.75, 0.5, 0.25, 0.125, 0.1));
        priorisationTextField.setWidth("500px");

        //Binder
        binder.forField(idTextField)
                .withConverter(new PlainStringToLongIdConverter())
                .bind(Grade::getId, Grade::setId);
        binder.forField(gradeTypeComboBox)
                .withConverter(new StringToGradeTypeConverter(gradeTypeService))
                .bind(Grade::getGrade_type, Grade::setGrade_type);
        binder.forField(gradeComboBox)
                .bind(Grade::getGrade, Grade::setGrade);
        binder.forField(priorisationTextField)
                .bind(Grade::getPrioritisation, Grade::setPrioritisation);

        //ContentLayout
        HorizontalLayout lineOneLayout = new HorizontalLayout(idTextField, gradeTypeComboBox);
        lineOneLayout.setWidthFull();

        HorizontalLayout lineTwoLayout = new HorizontalLayout(gradeComboBox, priorisationTextField);
        lineTwoLayout.setWidthFull();

        VerticalLayout contentLayout = new VerticalLayout();
        contentLayout.add(lineOneLayout, lineTwoLayout);

        //Button
        addButton = new Button("Hinzufügen", buttonClickEvent -> addGrade());
        Button cancelButton = new Button("Abbrechen", buttonClickEvent -> cancelEditGrade());

        HorizontalLayout buttonLayout = new HorizontalLayout(addButton, cancelButton);
        buttonLayout.setWidthFull();
        buttonLayout.setAlignItems(FlexComponent.Alignment.END);

        VerticalLayout verticalLayout = new VerticalLayout(new H4("Editor"), contentLayout, buttonLayout);
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
                grade.setSubject(selectedSubject);
                binder.writeBeanIfValid(grade);
                gradeService.saveGrade(grade);
                userGradeService.saveUserGrade(grade, currentUserSchoolYear);
                gradeResourceSet.add(grade);
            } else {
                selectedGrade.setSubject(selectedSubject);
                gradeResourceSet.remove(selectedGrade);
                binder.writeBeanIfValid(selectedGrade);
                gradeService.saveGrade(selectedGrade);
                userGradeService.saveUserGrade(selectedGrade, currentUserSchoolYear);
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
        setImprovementLayout();
        newGradeButton.setVisible(true);
        openErrorMessageIfPrioIsToHigh();
    }

    private void cancelEditGrade() {
        selectedGrade = null;
        editContainer.setVisible(false);
        newGradeButton.setVisible(true);
        openErrorMessageIfPrioIsToHigh();
    }

    private void createNewGrade() {
        editContainer.setVisible(true);
        selectedGrade = null;
        grid.deselectAll();
        binder.readBean(null);

        addButton.setText("Hinzufügen");
        idTextField.setValue("auto-generated");

        editContainer.setVisible(true);
        newGradeButton.setVisible(false);
    }

    private void setImprovementLayout() {
        double improvement = getImprovement();
        improvementButton = new Button();
        improvementButton.setEnabled(false);

        if (lastYearGradeResourceSet.size() == 0) {
            improvementButton.setText("Kein Vergleich zum Vorjahr möglich, da keine Daten für das Vorjahr existieren.");
            improvementButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        } else {
            if (improvement < 0) {
                improvementButton.setText("Keine Verbesserung zum Vorjahr");
                improvementButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            } else if (improvement > 0) {
                improvementButton.setText("Zum Vorjahr verbessert");
                improvementButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
            } else {
                improvementButton.setText("Keine Veränderung zum Vorjahr");
                improvementButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
            }
        }
    }

    private double getImprovement() {
        double average = 0;
        double lastAverage = 0;

        double priorisation = 0;

        for (Grade grade : gradeResourceSet) {
            average = average + (grade.getGrade() * grade.getPrioritisation());
            priorisation = priorisation + grade.getPrioritisation();
        }

        for (Grade grade : lastYearGradeResourceSet) {
            lastAverage = lastAverage + (grade.getGrade() * grade.getPrioritisation());
        }

        openErrorMessageIfPrioIsToHigh();

        return lastAverage - average;
    }

    private void openErrorMessageIfPrioIsToHigh() {
        double priorisation = 0;

        for (Grade grade : gradeResourceSet) {
            priorisation = priorisation + grade.getPrioritisation();
        }

        if (priorisation > 1) {
            Notification notification = new Notification();
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setText("Gewichtung größer als 100%");
            notification.setDuration(700);
            notification.open();
        }
    }
}
