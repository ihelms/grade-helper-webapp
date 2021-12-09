package com.grade.helper.businesslogic.repositories;

import com.grade.helper.businesslogic.entities.simple.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserDAOByUsername(String username);

    User findUserDAOById(Long id);

    @Override
    <S extends User> S save(S s);
}
