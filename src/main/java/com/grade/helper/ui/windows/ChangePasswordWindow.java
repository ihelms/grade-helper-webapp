package com.grade.helper.ui.windows;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;


public class ChangePasswordWindow extends CustomWindow {

    public ChangePasswordWindow() {
        super("Passwort ändern");

        Label usernameLabel = new Label("TODO");
        usernameLabel.setEnabled(false);

        TextField passwordTextField = new TextField("Password");
        TextField confirmationPasswordTextField = new TextField("Confirm Password");

        VerticalLayout contentLayout = new VerticalLayout(usernameLabel, passwordTextField, confirmationPasswordTextField);
        setContent(contentLayout);

    }
}
