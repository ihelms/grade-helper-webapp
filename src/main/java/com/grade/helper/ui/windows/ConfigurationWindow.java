package com.grade.helper.ui.windows;

import com.grade.helper.ui.login.LogoutView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.server.VaadinService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * created by ihelms on 30.11.2021
 */

public class ConfigurationWindow extends CustomWindow {

    public ConfigurationWindow() {
        super("Benutzereinstellungen");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Label username;
        if (authentication != null) {
            username = new Label(authentication.getName());
        } else {
            username = new Label("<Username>");
        }

        Button buttonLogout = new Button("Ausloggen");
        buttonLogout.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getSession().close();
            VaadinService.getCurrentRequest().getWrappedSession().invalidate();
            UI.getCurrent().navigate(LogoutView.class);
        });

        addButton(buttonLogout);
        setAddButtonText("Passwort ändern");
        setContent(username);
    }
}
