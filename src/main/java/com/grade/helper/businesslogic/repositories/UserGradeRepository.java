package com.grade.helper.businesslogic.repositories;

import com.grade.helper.businesslogic.entities.UserGradeDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGradeRepository extends JpaRepository<UserGradeDAO, Long> {

    //Object getSchoolYearsByUserId();
}
