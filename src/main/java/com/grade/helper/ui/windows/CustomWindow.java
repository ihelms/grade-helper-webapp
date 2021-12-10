package com.grade.helper.ui.windows;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.*;


public abstract class CustomWindow extends Dialog {

    private final VerticalLayout contentLayout;
    private final Button addButton;

    public CustomWindow(String title) {
        super();
        contentLayout = new VerticalLayout();

        setModal(true);
        setWidth("50%");

        H3 headerTitle = new H3(title);
        headerTitle.setWidthFull();

        HorizontalLayout header = new HorizontalLayout(headerTitle);
        header.setWidthFull();
        header.setAlignSelf(FlexComponent.Alignment.START, headerTitle);

        addButton = new Button("Hinzufügen");

        Button cancelButton = new Button("Abbrechen");
        cancelButton.addClickListener(buttonClickEvent -> this.close());

        HorizontalLayout footer = new HorizontalLayout();
        footer.add(addButton, cancelButton);
        footer.setAlignSelf(FlexComponent.Alignment.END);
        footer.setWidthFull();

        VerticalLayout windowLayout = new VerticalLayout();
        windowLayout.add(header, contentLayout, footer);
        windowLayout.setWidthFull();
        add(windowLayout);
    }

    public void setContent(Component... components) {
        contentLayout.add(components);
        contentLayout.setWidthFull();
    }

    public void addClickListenerToAddButton(ComponentEventListener<ClickEvent<Button>> listener) {
        this.addButton.addClickListener(listener);
    }
}
