package com.grade.helper.ui.windows;

import com.grade.helper.businesslogic.entities.enums.YEAR;
import com.grade.helper.businesslogic.entities.joined.UserSchoolYear;
import com.grade.helper.businesslogic.logic.*;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class SchoolYearWindow extends CustomWindow {

    SubjectService subjectService;
    UserSchoolYearService userSchoolYearService;
    UserService userService;

    ListBox<String> listBox;

    public SchoolYearWindow(SchoolYearService schoolYearService,
                            UserService userService,
                            SubjectService subjectService,
                            UserSchoolYearService userSchoolYearService) {
        super("Schuljahr hinzufügen");

        this.subjectService = subjectService;
        this.userSchoolYearService = userSchoolYearService;
        this.userService = userService;

        listBox = new ListBox<>();
        listBox.setReadOnly(true);
        setListBoxItems();

        String schoolYear = VaadinSession.getCurrent() != null
                ? String.valueOf(VaadinSession.getCurrent().getAttribute("school_year"))
                : "";

        ComboBox<String> comboBox = new ComboBox<>("Schuljahr");
        comboBox.setItems(Arrays.stream(YEAR.values()).map(YEAR::getValue));

        if (schoolYear != null) {
            comboBox.setValue(schoolYear);
        }

        addClickListenerToAddButton(buttonClickEvent -> {
            VaadinSession.getCurrent().setAttribute("school_year", comboBox.getValue());
            userSchoolYearService.addUserSchoolYear(new UserSchoolYear(
                    userService.getCurrentUser(),
                    schoolYearService.getSchoolYearByValue(comboBox.getValue()
                    ))
            );
            setListBoxItems();
            this.close();
        });

        VerticalLayout contentLayout = new VerticalLayout(listBox, comboBox);
        contentLayout.setWidthFull();
        setContent(contentLayout);
    }

    private void setListBoxItems() {
        List<UserSchoolYear> userSchoolYearList =
                userSchoolYearService.getAllSchoolYearsByUser(userService.getCurrentUser());
        listBox.setItems(userSchoolYearList.stream()
                .map(userSchoolYear -> userSchoolYear.getSchoolYearId().getValue()));
    }
}
