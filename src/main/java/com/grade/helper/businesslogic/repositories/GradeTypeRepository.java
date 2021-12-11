package com.grade.helper.businesslogic.repositories;

import com.grade.helper.businesslogic.entities.simple.GradeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeTypeRepository extends JpaRepository<GradeType, Long> {

    @Override
    List<GradeType> findAll();

    GradeType findByGradeType(String gradeType);
}
