package com.grade.helper.ui.windows;

import com.grade.helper.businesslogic.logic.UserService;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;


public class ChangePasswordWindow extends CustomWindow {

    public ChangePasswordWindow(UserService userService) {
        super("Passwort ändern");

        Label usernameLabel = new Label(userService.getCurrentUser().getUsername());
        usernameLabel.setEnabled(false);

        TextField passwordTextField = new TextField("Password");
        TextField confirmationPasswordTextField = new TextField("Confirm Password");

        VerticalLayout contentLayout = new VerticalLayout(usernameLabel, passwordTextField, confirmationPasswordTextField);
        contentLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        setContent(contentLayout);
    }
}
