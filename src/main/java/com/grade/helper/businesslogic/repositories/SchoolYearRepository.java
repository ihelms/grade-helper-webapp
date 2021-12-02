package com.grade.helper.businesslogic.repositories;

import com.grade.helper.businesslogic.entities.SchoolYearDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolYearRepository extends JpaRepository<SchoolYearDAO, Long> {

    List<SchoolYearDAO> findAllById(Long id);

    //void addSchoolYear(SchoolYearDAO schoolYearDAO);

}
