package com.grade.helper;

import com.grade.helper.businesslogic.entities.joined.UserGrade;
import com.grade.helper.businesslogic.entities.simple.*;
import com.grade.helper.businesslogic.repositories.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * created by ihelms on 09.12.2021
 */

public class Pseudoclass {

    public void setRepositories(UserGradeRepository userGradeRepository,
                                SubjectRepository subjectRepository,
                                UserRepository userRepository,
                                SchoolYearRepository schoolYearRepository,
                                GradeTypeRepository gradeTypeRepository,
                                GradeRepository gradeRepository) {
        if (userRepository.count() == 0) {
            userRepository.saveAll(
                    Stream.of(
                            "1 FirstName1 LastName1 user userpass",
                            "2 FirstName2 LastName2 user2 password2",
                            "3 FirstName3 LastName3 user3 password3",
                            "4 Admin Admin admin admin"
                    ).map(name -> {
                        String[] splitUser = name.split(" ");
                        return new User(splitUser[1], splitUser[2], splitUser[3], splitUser[4]);
                    }).collect(Collectors.toList())
            );
        }
        if (schoolYearRepository.count() == 0) {
            schoolYearRepository.saveAll(
                    Stream.of(
                            "1. Klasse",
                            "2. Klasse",
                            "3. Klasse",
                            "4. Klasse",
                            "5. Klasse",
                            "6. Klasse",
                            "7. Klasse",
                            "8. Klasse",
                            "9. Klasse",
                            "10. Klasse"
                    ).map(name -> {
                        SchoolYear schoolYear = new SchoolYear();
                        schoolYear.setValue(name);
                        return schoolYear;
                    }).collect(Collectors.toList())
            );
        }
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
        if (gradeTypeRepository.count() == 0) {
            gradeTypeRepository.saveAll(
                    List.of(
                            new GradeType(1L, "Epo"),
                            new GradeType(2L, "Klausur"),
                            new GradeType(3L, "Test")
                    )
            );
        }
        if (gradeRepository.count() == 0) {
            gradeRepository.saveAll(
                    List.of(
                            new Grade(new GradeType(1L, "Epo"), 1, 0.25, new Timestamp(2021, 10, 10, 0, 0, 0, 0), new Subject(1L, "Chemie")),
                            new Grade(new GradeType(2L, "Klausur"), 1, 0.5, new Timestamp(2021, 10, 10, 0, 0, 0, 0), new Subject(1L, "Chemie")),
                            new Grade(new GradeType(3L, "Test"), 1, 0.25, new Timestamp(2021, 10, 10, 0, 0, 0, 0), new Subject(2L, "Biologie"))
                    )
            );
        }

        if (userGradeRepository.count() == 0) {
            User user = userRepository.findUserDAOById(1L);
            SchoolYear schoolYear = schoolYearRepository.findSchoolYearDAOById(10L);
            Grade grade = gradeRepository.findGradeDAOById(1L);

            userGradeRepository.saveAll(List.of(new UserGrade(user, schoolYear, grade)));
        }
    }

}
