package com.grade.helper;

import com.grade.helper.businesslogic.entities.enums.SUBJECT;
import com.grade.helper.businesslogic.entities.enums.TYPE;
import com.grade.helper.businesslogic.entities.joined.UserGrade;
import com.grade.helper.businesslogic.entities.joined.UserSchoolYear;
import com.grade.helper.businesslogic.entities.simple.*;
import com.grade.helper.businesslogic.repositories.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * created by ihelms on 09.12.2021
 */

public class Pseudoclass {

    public void setRepositories(UserGradeRepository userGradeRepository,
                                SubjectRepository subjectRepository,
                                UserRepository userRepository,
                                SchoolYearRepository schoolYearRepository,
                                GradeTypeRepository gradeTypeRepository,
                                GradeRepository gradeRepository,
                                UserSchoolRepository userSchoolRepository) {

        Timestamp timestamp = new Timestamp(2021, 10, 10, 0, 0, 0, 0);

        User user = new User("user", "userpass", "user", "user");
        User admin = new User("admin", "admin", "admin", "admin");

        if (userRepository.count() == 0) {
            userRepository.saveAndFlush(user);
            userRepository.saveAndFlush(admin);
        }

        SchoolYear schoolYear_1 = new SchoolYear(1L, "1. Klasse");
        SchoolYear schoolYear_2 = new SchoolYear(2L, "2. Klasse");
        SchoolYear schoolYear_3 = new SchoolYear(3L, "3. Klasse");
        SchoolYear schoolYear_4 = new SchoolYear(4L, "4. Klasse");
        SchoolYear schoolYear_5 = new SchoolYear(5L, "5. Klasse");
        SchoolYear schoolYear_6 = new SchoolYear(6L, "6. Klasse");
        SchoolYear schoolYear_7 = new SchoolYear(7L, "7. Klasse");
        SchoolYear schoolYear_8 = new SchoolYear(8L, "8. Klasse");
        SchoolYear schoolYear_9 = new SchoolYear(9L, "9. Klasse");
        SchoolYear schoolYear_10 = new SchoolYear(10L, "10. Klasse");

        if (schoolYearRepository.count() == 0) {
            schoolYearRepository.saveAll(List.of(schoolYear_1, schoolYear_2, schoolYear_3, schoolYear_4, schoolYear_5,
                    schoolYear_6, schoolYear_7, schoolYear_8, schoolYear_9, schoolYear_10)
            );
        }

        Subject bwl = new Subject(1L, "VWL");
        Subject vwl = new Subject(2L, "BWL");
        Subject iv = new Subject(3L, "IV");
        Subject mathe = new Subject(4L, "Mathe");
        Subject bio = new Subject(5L, "Biologie");
        Subject chemie = new Subject(6L, "Chemie");
        Subject physik = new Subject(7L, "Physik");
        Subject englisch = new Subject(8L, "Englisch");
        Subject ethik = new Subject(9L, "Ethik");
        Subject reli = new Subject(10L, "Religion");
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

        GradeType test = new GradeType(1L, "Test");
        GradeType projekt = new GradeType(2L, "Projekt");
        GradeType klausur = new GradeType(3L, "Klausur");
        GradeType epo = new GradeType(4L, "Epo");

        if (gradeTypeRepository.count() == 0) {
            gradeTypeRepository.saveAll(List.of(test, projekt, klausur, epo));
        }

        Grade user_grade_bio_1 = new Grade(test, 1, 0.25, timestamp, bio);
        Grade user_grade_bio_2 = new Grade(epo, 2, 0.25, timestamp, bio);
        Grade user_grade_bio_3 = new Grade(klausur, 1, 0.5, timestamp, bio);
        Grade user_grade_mathe_1 = new Grade(epo, 1, 0.25, timestamp, mathe);

        if (gradeRepository.count() == 0) {
            gradeRepository.saveAll(
                    List.of(user_grade_bio_1, user_grade_bio_2, user_grade_bio_3, user_grade_mathe_1)
            );
        }

        UserSchoolYear userSchoolYear_user_8 = new UserSchoolYear(user, schoolYear_8);
        UserSchoolYear userSchoolYear_user_9 = new UserSchoolYear(user, schoolYear_9);
        UserSchoolYear userSchoolYear_admin_8 = new UserSchoolYear(user, schoolYear_8);

        if (userSchoolRepository.count() == 0) {
            userSchoolRepository.saveAll(List.of(
                            userSchoolYear_user_8, userSchoolYear_user_9, userSchoolYear_admin_8
                    )
            );
        }

        UserGrade userGrade_user_bio_8_1 = new UserGrade(userSchoolYear_user_8, user_grade_bio_1);
        UserGrade userGrade_user_bio_8_2 = new UserGrade(userSchoolYear_user_8, user_grade_bio_2);
        UserGrade userGrade_user_bio_8_3 = new UserGrade(userSchoolYear_user_8, user_grade_bio_3);
        UserGrade userGrade_user_bio_9_1 = new UserGrade(userSchoolYear_user_9, user_grade_bio_1);
        UserGrade userGrade_user_bio_9_2 = new UserGrade(userSchoolYear_user_9, user_grade_bio_2);
        UserGrade userGrade_user_bio_9_3 = new UserGrade(userSchoolYear_user_9, user_grade_bio_3);

        if (userGradeRepository.count() == 0) {
            userGradeRepository.saveAll(
                    List.of(userGrade_user_bio_8_1, userGrade_user_bio_8_2, userGrade_user_bio_8_3,
                            userGrade_user_bio_9_1, userGrade_user_bio_9_2, userGrade_user_bio_9_3
                    )
            );
        }
    }
}
