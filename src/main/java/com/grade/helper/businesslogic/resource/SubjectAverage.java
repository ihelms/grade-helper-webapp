package com.grade.helper.businesslogic.resource;

import com.grade.helper.businesslogic.entities.simple.Subject;

/**
 * created by ihelms on 02.12.2021
 */

public class SubjectAverage {

    private Subject subject;
    private Double average;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
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
