package com.grade.helper.ui.login;

import com.grade.helper.businesslogic.entities.UserDAO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * created by ihelms on 18.11.2021
 */

@Route("registration")
@PageTitle("Registration")
public class RegistrationView extends VerticalLayout {

    public RegistrationView() {
        UserDAO userDAO = new UserDAO();
        Binder<UserDAO> binder = new Binder<>();

        H2 title = new H2("Registrieren");

        TextField username = createTextField("Username");
        TextField firstName = createTextField("Vorname");
        TextField lastName = createTextField("Nachname");

        PasswordField password = new PasswordField("Passwort");
        PasswordField passwordConfirmation = new PasswordField("Passwort wiederholen");
        passwordConfirmation.addValueChangeListener(valueChangeEvent -> {
            if (!password.getValue().equals(passwordConfirmation.getValue())) {
                //TODO
                    passwordConfirmation.setErrorMessage("Passwörter nicht gleich.");
            }
        });

        VerticalLayout fieldLayout = new VerticalLayout(username, firstName, lastName, password, passwordConfirmation);
        fieldLayout.setHorizontalComponentAlignment(Alignment.CENTER);

        //Binder
        binder.forField(username)
                .bind(UserDAO::getUsername, UserDAO::setUsername);
        binder.forField(firstName)
                .bind(UserDAO::getFirstName, UserDAO::setFirstName);
        binder.forField(lastName)
                .bind(UserDAO::getLastName, UserDAO::setLastName);
        binder.forField(password)
                .bind(UserDAO::getPassword, UserDAO::setPassword);
        binder.readBean(userDAO);

        Button registerButton = new Button("Registrieren");
        registerButton.addClickListener(buttonClickEvent -> {
           //TODO
        });

        add(title, fieldLayout, registerButton);
    }

    private TextField createTextField(String title) {
        TextField textField = new TextField(title);
        textField.setWidth("250px");
        textField.setRequired(true);

        return textField;
    }
}
