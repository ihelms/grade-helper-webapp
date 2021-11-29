package com.grade.helper.ui.windows;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;

/**
 * created by ihelms on 29.11.2021
 */

public class CustomDialog extends Dialog {

    private final H2 title;
    private final Div content;

    public CustomDialog(String caption, Component component) {
        this.setDraggable(false);
        this.setResizable(false);
        this.setWidth("600px");
        this.getElement().setAttribute("aria-labelledby", "dialog-title");
        this.title = caption != null ? new H2(caption) : new H2();
        this.title.addClassName("dialog-title");

        Button close = new Button(VaadinIcon.CLOSE_SMALL.create());
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.addClickListener((event) -> this.close());

        Header header = new Header(this.title, close);
        header.getElement().getThemeList().add("dark");
        this.add(header);
        this.content = component != null ? new Div(component) : new Div();

        this.add(this.content);
    }

    public void setCaption(String caption) {
        this.title.setText(caption);
    }

    public void setContent(Component... contentComponents) {
        this.content.removeAll();
        this.content.add(contentComponents);
    }

    public void clearContent() {
        this.content.removeAll();
    }

}
