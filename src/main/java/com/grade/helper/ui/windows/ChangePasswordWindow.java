package com.grade.helper.ui.windows;

import com.grade.helper.businesslogic.entities.simple.User;
import com.grade.helper.businesslogic.logic.UserService;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.data.binder.Binder;


public class ChangePasswordWindow extends CustomWindow {

    public ChangePasswordWindow(UserService userService) {
        super("Passwort ändern");

        User currentUser = userService.getCurrentUser();
        Binder<User> binder = new Binder<>();

        PasswordField password = new PasswordField("Password");
        PasswordField confirmationPasswordTextField = new PasswordField("Confirm Password");
        confirmationPasswordTextField.addValueChangeListener(valueChangeEvent -> {
            if(!password.getValue().equals(confirmationPasswordTextField.getValue())) {
                confirmationPasswordTextField.setInvalid(true);
                confirmationPasswordTextField.setErrorMessage("Stimmt mit Passwort nicht überein!");
            }
        });

        addClickListenerToAddButton(buttonClickEvent -> {
            binder.writeBeanIfValid(currentUser);
            userService.saveUserAfterPasswordChange(currentUser);
            this.close();
        });

        VerticalLayout contentLayout = new VerticalLayout(password, confirmationPasswordTextField);
        contentLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        setContent(contentLayout);
    }
}
