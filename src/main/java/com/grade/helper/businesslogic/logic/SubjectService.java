package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.enums.SUBJECT;
import com.grade.helper.businesslogic.entities.simple.Subject;
import com.grade.helper.businesslogic.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubjectService {

    SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectOfSubjectEnum(SUBJECT subject) {
        return subjectRepository.findByValue(subject.getValue());
    }
}
