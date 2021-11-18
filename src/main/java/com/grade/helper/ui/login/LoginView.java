package com.grade.helper.ui.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

/**
 * created by ihelms on 18.11.2021
 */

@Route("login")
@PageTitle("Login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    public static final String LOGIN_FROM_ID = "loginFormId";
    public static final String LOGIN_USERNAME_ID = "loginUserNameId";
    public static final String LOGIN_PASSWORD_ID = "loginPasswordId";

    private final LoginForm loginForm;
    private TextField userName;
    private PasswordField password;
    private Button submitButton;

    public LoginView() {
        loginForm = new LoginForm();
        loginForm.setId(LOGIN_FROM_ID);

        addClassName("login-view");
        setSizeFull();
        setAlignItems(FlexComponent.Alignment.CENTER);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        loginForm.setAction("login");
        add(new H1("Login"), loginForm);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

        if (beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")
        ) {
            loginForm.setError(true);
        }
    }

}
