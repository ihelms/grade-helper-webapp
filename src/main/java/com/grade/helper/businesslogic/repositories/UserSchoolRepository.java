package com.grade.helper.businesslogic.repositories;

import com.grade.helper.businesslogic.entities.joined.UserSchoolYear;
import com.grade.helper.businesslogic.entities.simple.SchoolYear;
import com.grade.helper.businesslogic.entities.simple.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSchoolRepository extends JpaRepository<UserSchoolYear, Long> {

    List<UserSchoolYear> findAllByUserId(User user);

    UserSchoolYear findByUserIdAndSchoolYearId(User user, SchoolYear schoolYear);
}
