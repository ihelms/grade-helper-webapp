package com.grade.helper.ui.windows;

import com.grade.helper.businesslogic.entities.simple.User;
import com.grade.helper.businesslogic.logic.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class ConfigurationWindow extends CustomWindow {

    public ConfigurationWindow(UserService userService) {
        super("Benutzereinstellungen");

        User currentUser = userService.getCurrentUser();

        Label username = new Label(currentUser.getUsername());
        username.setEnabled(false);
        username.setWidth("250px");

        Button changePassword = new Button("Passwort ändern");
        changePassword.setWidth("250px");
        changePassword.addClickListener(buttonClickEvent -> {
            ChangePasswordWindow changePasswordWindow = new ChangePasswordWindow(userService);
            changePasswordWindow.open();
        });

        setButtonsInvisible();
        setContent(new VerticalLayout(username, changePassword));
    }
}
