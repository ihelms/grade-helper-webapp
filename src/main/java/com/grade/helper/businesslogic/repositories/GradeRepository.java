package com.grade.helper.businesslogic.repositories;

import com.grade.helper.businesslogic.entities.simple.Grade;
import com.grade.helper.businesslogic.entities.simple.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findAllBySubject(Subject subject);

    Grade findGradeDAOById(Long id);
}
