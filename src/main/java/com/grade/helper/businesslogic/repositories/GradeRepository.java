package com.grade.helper.businesslogic.repositories;

import com.grade.helper.businesslogic.entities.GradeDAO;
import com.grade.helper.businesslogic.enums.SUBJECT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<GradeDAO, Long> {

    List<GradeDAO> findAllBySubject(SUBJECT subject);
}
