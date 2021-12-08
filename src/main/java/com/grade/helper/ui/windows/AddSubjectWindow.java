package com.grade.helper.ui.windows;

import com.grade.helper.businesslogic.enums.SUBJECT;
import com.grade.helper.businesslogic.logic.SubjectService;
import com.vaadin.flow.component.combobox.ComboBox;

/**
 * created by ihelms on 29.11.2021
 */

public class AddSubjectWindow extends CustomWindow {

    public AddSubjectWindow() {
        super("Schulfach hinzufügen");

        SubjectService subjectService = new SubjectService();

        ComboBox<SUBJECT> comboBox = new ComboBox<>("Schulfach");
        comboBox.setItems(SUBJECT.values());

        addClickListenerToAddButton(buttonClickEvent -> subjectService.addSubject(comboBox.getValue()));
        setContent(comboBox);
    }
}
