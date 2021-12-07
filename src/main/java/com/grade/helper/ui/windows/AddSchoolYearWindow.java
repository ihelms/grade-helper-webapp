package com.grade.helper.ui.windows;

import com.grade.helper.businesslogic.logic.SchoolYearService;
import com.vaadin.flow.component.combobox.ComboBox;
import org.springframework.stereotype.Component;


/**
 * created by ihelms on 02.12.2021
 */

@Component
public class AddSchoolYearWindow extends CustomWindow {

    public AddSchoolYearWindow(SchoolYearService schoolYearService) {
        super("Schuljahr hinzufügen");

        ComboBox<String> comboBox = new ComboBox<>("Schuljahr");
        comboBox.setItems(schoolYearService.getAllSchoolYears());

        //addClickListenerToAddButton(buttonClickEvent -> schoolYearLogic.addSchoolYear(comboBox.getValue()));
        setContent(comboBox);
    }

}
