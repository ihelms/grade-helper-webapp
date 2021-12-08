package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.UserDAO;
import com.grade.helper.businesslogic.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    //generating test data
    @PostConstruct
    public void populateTestData() {
        if (userRepository.count() == 0) {
            userRepository.saveAll(
                    Stream.of(
                            "1 FirstName1 LastName1 user1 password1",
                            "2 FirstName1 LastName1 user1 password1",
                            "3 FirstName1 LastName1 user1 password1"
                    ).map(name -> {
                        String[] splitUser = name.split(" ");
                        UserDAO userDAO = new UserDAO();
                        userDAO.setId(Long.valueOf(splitUser[0]));
                        userDAO.setFirstName(splitUser[1]);
                        userDAO.setLastName(splitUser[2]);
                        userDAO.setPassword(splitUser[3]);
                        return userDAO;
                    }).collect(Collectors.toList())
            );
        }
    }
}
