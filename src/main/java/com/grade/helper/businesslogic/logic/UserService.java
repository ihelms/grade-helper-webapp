package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.simple.User;
import com.grade.helper.businesslogic.repositories.UserRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

/**
 * created by ihelms on 02.12.2021
 */

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
        Object principal = context.getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return (UserDetails) context.getAuthentication().getPrincipal();
        }
        return null;
    }

    public User getAuthenticatedUserDAO() {
        UserDetails userDetails = getAuthenticatedUser();
        return userRepository.findUserDAOByUsername(userDetails.getUsername());
    }

    public void logout() {
        UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(VaadinServletRequest.getCurrent().getHttpServletRequest(), null, null);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean isNewUserSaved(User user) {
        userRepository.findAll().forEach(userDAO1 -> System.out.println(userDAO1.toString()));
        User exists = userRepository.findUserDAOByUsername(user.getUsername());
        return exists != null;
    }

}
