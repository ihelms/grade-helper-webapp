package com.grade.helper.ui.login;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

@Route("logout")
@PageTitle("Logout")
public class LogoutView extends VerticalLayout implements BeforeEnterObserver {

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        beforeEnterEvent.forwardTo(LoginView.class);
    }
}
