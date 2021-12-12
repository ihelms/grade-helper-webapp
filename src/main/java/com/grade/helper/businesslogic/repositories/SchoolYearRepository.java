package com.grade.helper.businesslogic.repositories;

import com.grade.helper.businesslogic.entities.simple.SchoolYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolYearRepository extends JpaRepository<SchoolYear, Long> {

    List<SchoolYear> findAllById(Long id);

    SchoolYear findSchoolYearDAOById(Long id);

    SchoolYear findSchoolYearDAOByValue(String value);

    @Override
    List<SchoolYear> findAll();
}
