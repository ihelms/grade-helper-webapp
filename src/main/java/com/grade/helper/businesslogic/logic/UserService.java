package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.simple.User;
import com.grade.helper.businesslogic.repositories.UserRepository;
import com.grade.helper.ui.login.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.server.VaadinServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final String LOGOUT_SUCCESS_URL = "/";

    public UserDetails getAuthenticatedUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context.getAuthentication() != null) {
            Object principal = context.getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                return (UserDetails) context.getAuthentication().getPrincipal();
            }
        }
        return null;
    }

    public User getCurrentUser() {
        UserDetails userDetails = getAuthenticatedUser();
        return userRepository.findUserByUsername(userDetails != null ? userDetails.getUsername() : "");
    }

    public void logout() {
        UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(VaadinServletRequest.getCurrent().getHttpServletRequest(), null, null);
    }

    public void saveUser(User user) {
        Notification notification = new Notification();
        Div text = new Div();
        notification.add(text);
        notification.setDuration(650);

        try {
            if (userRepository.findUserByUsername(user.getUsername()) == null) {
                userRepository.save(user);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                text.setText("User wurde angelegt");
                notification.open();
                UI.getCurrent().navigate(LoginView.class);
            } else {
                text.setText("User existiert bereits");
                notification.open();
                throw new Exception();
            }
        } catch (Exception exc) {
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            text.setText("Registrierung fehlgeschlagen");
            notification.open();
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
