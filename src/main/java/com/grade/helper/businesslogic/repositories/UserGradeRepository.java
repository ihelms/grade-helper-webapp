package com.grade.helper.businesslogic.repositories;

import com.grade.helper.businesslogic.entities.joined.UserGrade;
import com.grade.helper.businesslogic.entities.joined.UserSchoolYear;
import com.grade.helper.businesslogic.entities.simple.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGradeRepository extends JpaRepository<UserGrade, Long> {

    List<UserGrade> findAllByUserSchoolYearId(UserSchoolYear userSchoolYear);
}
