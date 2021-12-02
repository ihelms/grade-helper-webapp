package com.grade.helper.ui.windows;

import com.grade.helper.businesslogic.entities.SchoolYearDAO;
import com.grade.helper.businesslogic.logic.SchoolYearLogic;
import com.vaadin.flow.component.combobox.ComboBox;


/**
 * created by ihelms on 02.12.2021
 */

public class AddSchoolYearWindow extends CustomWindow {

    public AddSchoolYearWindow() {
        super("Schuljahr hinzufügen");

        SchoolYearLogic schoolYearLogic = new SchoolYearLogic();

        ComboBox<SchoolYearDAO> comboBox = new ComboBox<>("Schuljahr");
        comboBox.setItems();

        addClickListenerToAddButton(buttonClickEvent -> schoolYearLogic.addSchoolYear(comboBox.getValue()));
        setContent(comboBox);
    }

}
