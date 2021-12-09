package com.grade.helper.businesslogic.service;

import com.grade.helper.businesslogic.entities.simple.Subject;
import com.grade.helper.businesslogic.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

/**
 * created by ihelms on 26.11.2021
 */

@Service
public class SubjectService {

    SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getSubjectForSchoolYear(String schoolYearDAO) {
        return List.of(); //subjectRepository.findAllBySchoolYearDAO(schoolYearDAO);
    }

    public void addSubject(Subject subject) {
        //TODO
    }

    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }

    public List<String> findAllSubjectValues() {
        List<String> subjectValues = new LinkedList<>();
        List<Subject> subjectList = findAllSubjects();
        subjectList.forEach(subject -> subjectValues.add(subject.getValue()));
        return subjectValues;
    }

    public Subject findSubjectByName(String subjectName) {
        return subjectRepository.findByValue(subjectName);
    }

    //generating test data
    @PostConstruct
    public void populateTestData() {
        if (subjectRepository.count() == 0) {
            subjectRepository.saveAll(
                    List.of(
                            new Subject(1L, "Chemie"),
                            new Subject(2L, "Biologie"),
                            new Subject(3L, "Sport"),
                            new Subject(4L, "BWL")
                    )
            );
        }
    }
}
