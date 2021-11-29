package com.grade.helper.ui.windows;

import com.grade.helper.businesslogic.enums.SUBJECT;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * created by ihelms on 29.11.2021
 */

public class SchoolYearWindow extends Dialog {

    private final String title = "Schuljahr hinzufügen";

    public SchoolYearWindow() {
        super();
        setModal(true);
        setWidth("50%");

        Button close = new Button(VaadinIcon.CLOSE_SMALL.create());
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.addClickListener((event) -> this.close());

        Header header = new Header(new H3(this.title), close);
        header.setWidthFull();

        ComboBox<SUBJECT> comboBox = new ComboBox<>("Schulfach");
        comboBox.setItems(SUBJECT.values());

        Button buttonAdd = new Button("Hinzufügen");
        buttonAdd.addClickListener(buttonClickEvent -> {
            //TODO
        });

        Button buttonCancel = new Button("Abbrechen");
        buttonCancel.addClickListener(buttonClickEvent -> this.close());

        Footer footer = new Footer();
        footer.add(buttonAdd, buttonCancel);

        VerticalLayout contentLayout = new VerticalLayout();
        contentLayout.add(header, comboBox, footer);
        add(contentLayout);
    }
}
