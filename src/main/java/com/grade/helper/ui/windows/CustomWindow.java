package com.grade.helper.ui.windows;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * created by ihelms on 30.11.2021
 */

public abstract class CustomWindow extends Dialog {

    private final VerticalLayout contentLayout;
    private final Button addButton;
    private final Footer footer;

    public CustomWindow(String title) {
        super();
        contentLayout = new VerticalLayout();

        setModal(true);
        setWidth("50%");

        Button close = new Button(VaadinIcon.CLOSE_SMALL.create());
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.addClickListener((event) -> this.close());

        Header header = new Header(new HorizontalLayout(new H3(title), close));
        header.setWidthFull();

        addButton = new Button("Hinzufügen");

        Button cancelButton = new Button("Abbrechen");
        cancelButton.addClickListener(buttonClickEvent -> this.close());

        footer = new Footer();
        footer.add(addButton, cancelButton);
        footer.setWidthFull();

        VerticalLayout windowLayout = new VerticalLayout();
        windowLayout.add(header, contentLayout, footer);
        add(windowLayout);
    }

    public void setContent(Component... components) {
        contentLayout.add(components);
    }

    public void addClickListenerToAddButton(ComponentEventListener<ClickEvent<Button>> listener) {
        this.addButton.addClickListener(listener);
    }

    public void setAddButtonText(String text) {
        this.addButton.setText(text);
    }

    public void addButton(Button button) {
        this.footer.add(button);
    }

}
