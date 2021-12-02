package com.grade.helper.businesslogic.resource;

import com.grade.helper.businesslogic.enums.SUBJECT;

/**
 * created by ihelms on 02.12.2021
 */

public class SubjectAverage {

    private SUBJECT subject;
    private Double average;

    public SUBJECT getSubject() {
        return subject;
    }

    public void setSubject(SUBJECT subject) {
        this.subject = subject;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return "SubjectAverage{" +
                "subject=" + subject +
                ", average=" + average +
                '}';
    }
}
