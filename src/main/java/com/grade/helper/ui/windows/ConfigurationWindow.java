package com.grade.helper.ui.windows;

import com.grade.helper.businesslogic.logic.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;


public class ConfigurationWindow extends CustomWindow {

    public ConfigurationWindow(UserService userService) {
        super("Benutzereinstellungen");

        TextField username = new TextField();
        username.setValue(userService.getCurrentUser().getUsername());
        username.setEnabled(false);
        username.setWidth("250px");

        Button changePassword = new Button("Passwort ändern");
        changePassword.setWidth("250px");
        changePassword.addClickListener(buttonClickEvent -> {
            ChangePasswordWindow changePasswordWindow = new ChangePasswordWindow();
            changePasswordWindow.open();
        });

        setContent(new VerticalLayout(username, changePassword));
    }
}
