package com.grade.helper.businesslogic.repositories;

import com.grade.helper.businesslogic.entities.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, Long> {

   /*
   UserDAO getUserByNameAndPassword(@Param("username") String username,
                                     @Param("password") String password);
   */
}
