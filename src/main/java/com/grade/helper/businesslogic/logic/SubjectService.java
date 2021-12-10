package com.grade.helper.businesslogic.logic;

import com.grade.helper.businesslogic.entities.simple.Subject;
import com.grade.helper.businesslogic.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class SubjectService {

    SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }


}
