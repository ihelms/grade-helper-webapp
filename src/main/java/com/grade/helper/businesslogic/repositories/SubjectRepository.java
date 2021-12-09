package com.grade.helper.businesslogic.repositories;

import com.grade.helper.businesslogic.entities.simple.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Override
    List<Subject> findAll();

    Subject findByValue(@Param("value") String value);

  //  @Query(value = "select * from sc", nativeQuery = true)
   // List<Subject> findAllBySchoolYearDAO(String schoolYearDAO);
}
