package com.grade.helper.businesslogic.logic;

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
        Subject bwl = new Subject(1L, "VWL");
        Subject vwl = new Subject(2L, "BWL");
        Subject iv = new Subject(3L, "IV");
        Subject mathe = new Subject(4L, "Mathe");
        Subject bio = new Subject(5L, "BIO");
        Subject chemie = new Subject(6L, "Chemie");
        Subject physik = new Subject(7L, "Physik");
        Subject englisch = new Subject(8L, "Englisch");
        Subject ethik = new Subject(9L, "Ethik");
        Subject reli = new Subject(10L, "Reli");
        Subject deutsch = new Subject(11L, "Deutsch");
        Subject spanisch = new Subject(12L, "Spanisch");
        Subject franzoesisch = new Subject(13L, "Französisch");
        Subject kunst = new Subject(14L, "Kunst");
        Subject sport = new Subject(15L, "Sport");

        if (subjectRepository.count() == 0) {
            subjectRepository.saveAll(List.of(bwl, vwl, iv, mathe, bio, chemie, physik, englisch,
                    ethik, reli, deutsch, spanisch, franzoesisch, kunst, sport)
            );
        }
    }
}
