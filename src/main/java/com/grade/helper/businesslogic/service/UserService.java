package com.grade.helper.businesslogic.service;

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

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    //generating test data
    @PostConstruct
    public void populateTestData() {
        if (userRepository.count() == 0) {
            userRepository.saveAll(
                    Stream.of(
                                    "1 FirstName1 LastName1 user1 password1",
                                    "2 FirstName2 LastName2 user2 password2",
                                    "3 FirstName3 LastName3 user3 password3",
                                    "4 Admin Admin admin admin")
                            .map(name -> {
                                String[] splitUser = name.split(" ");
                                User user = new User();
                                user.setId(Long.valueOf(splitUser[0]));
                                user.setFirstName(splitUser[1]);
                                user.setLastName(splitUser[2]);
                                user.setPassword(splitUser[3]);
                                return user;
                            }).collect(Collectors.toList())
            );
        }
    }


}
