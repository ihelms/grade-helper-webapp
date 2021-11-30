package com.grade.helper.ui.windows;

import com.grade.helper.ui.login.LogoutView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinService;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * created by ihelms on 30.11.2021
 */

public class ConfigurationWindow extends Dialog {
    private final String title = "Benutzereinstellungen";

    public ConfigurationWindow() {
        super();
        setModal(true);
        setWidth("50%");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Button close = new Button(VaadinIcon.CLOSE_SMALL.create());
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.addClickListener((event) -> this.close());

        Header header = new Header(new H3(this.title), close);
        header.setWidthFull();

        Label username;
        if (authentication != null) {
            username = new Label(authentication.getName());
        } else {
            username = new Label();
        }

        Button buttonChangePassword = new Button("Passwort ändern");
        Button buttonLogout = new Button("Ausloggen");
        buttonLogout.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getSession().close();
            VaadinService.getCurrentRequest().getWrappedSession().invalidate();
            UI.getCurrent().navigate(LogoutView.class);
          //  UI.getCurrent().getPage().setLocation("/logout");
        });

        VerticalLayout contentLayout = new VerticalLayout();
        contentLayout.add(header, username, buttonChangePassword, buttonLogout);
        add(contentLayout);
    }
}
