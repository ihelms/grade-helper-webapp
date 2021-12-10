package com.grade.helper.ui.windows;

import com.grade.helper.businesslogic.entities.enums.YEAR;
import com.grade.helper.businesslogic.logic.*;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SchoolYearWindow extends CustomWindow {

    SubjectService subjectService;

    public SchoolYearWindow(SchoolYearService schoolYearService,
                            UserGradeService userGradeService,
                            UserService userService,
                            SubjectService subjectService,
                            UserSchoolYearService userSchoolYearService) {
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
            this.close();
        });

        setContent(new VerticalLayout(comboBox));
    }
}
