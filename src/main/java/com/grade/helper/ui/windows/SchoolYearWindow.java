package com.grade.helper.ui.windows;

import com.grade.helper.businesslogic.entities.enums.SUBJECT;
import com.grade.helper.businesslogic.entities.enums.YEAR;
import com.grade.helper.businesslogic.entities.simple.Subject;
import com.grade.helper.businesslogic.service.*;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * created by ihelms on 02.12.2021
 */

@Component
public class SchoolYearWindow extends CustomWindow {

    SubjectService subjectService;

    public SchoolYearWindow(SchoolYearService schoolYearService,
                            UserGradeService userGradeService,
                            UserService userService,
                            SubjectService subjectService) {
        super("Schuljahr hinzufügen");

        this.subjectService = subjectService;

        String schoolYear = VaadinSession.getCurrent() != null
                ? String.valueOf(VaadinSession.getCurrent().getAttribute("school_year"))
                : "";

        ComboBox<String> comboBox = new ComboBox<>("Schuljahr");
        comboBox.setItems(Arrays.stream(YEAR.values()).map(YEAR::getValue));

        if (schoolYear != null) {
            comboBox.setValue(schoolYear);
        }

        addClickListenerToAddButton(buttonClickEvent -> {
            //TODO
        });

        setContent(new VerticalLayout(comboBox));
    }
}
