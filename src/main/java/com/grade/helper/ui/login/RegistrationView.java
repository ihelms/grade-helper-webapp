package com.grade.helper.ui.login;

import com.grade.helper.businesslogic.entities.simple.User;
import com.grade.helper.businesslogic.logic.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@Route("registration")
@PageTitle("Registration")
@UIScope
@SpringComponent
public class RegistrationView extends VerticalLayout {

    @Autowired
    public RegistrationView(UserService userService) {
        User user = new User();
        Binder<User> binder = new Binder<>();

        H2 title = new H2("Registrieren");

        TextField username = createTextField("Username");
        TextField firstName = createTextField("Vorname");
        TextField lastName = createTextField("Nachname");

        PasswordField password = new PasswordField("Passwort");
        PasswordField passwordConfirmation = new PasswordField("Passwort wiederholen");
        passwordConfirmation.addValueChangeListener(valueChangeEvent -> {
            //TODO
        });

        VerticalLayout fieldLayout = new VerticalLayout(username, firstName,
                lastName, password, passwordConfirmation);

        //Binder
        binder.forField(username)
                .bind(User::getUsername, User::setUsername);
        binder.forField(firstName)
                .bind(User::getFirstName, User::setFirstName);
        binder.forField(lastName)
                .bind(User::getLastName, User::setLastName);
        binder.forField(password)
                .bind(User::getPassword, User::setPassword);
        binder.readBean(user);

        Notification notification = new Notification();
        notification.setText("Error");

        Button registerButton = new Button("Registrieren");
        registerButton.addClickListener(buttonClickEvent -> {
            binder.writeBeanIfValid(user);
            userService.saveUser(user);
        });

        VerticalLayout content = new VerticalLayout(title, fieldLayout, registerButton);
        content.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        addAndExpand(content);
    }

    private TextField createTextField(String title) {
        TextField textField = new TextField(title);
        textField.setWidth("250px");
        textField.setRequired(true);

        return textField;
    }
}
