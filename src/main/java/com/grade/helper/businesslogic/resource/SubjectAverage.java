package com.grade.helper.businesslogic.resource;


@SuppressWarnings("unused")
public class SubjectAverage {

    private String subject;
    private Double average;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public SubjectAverage() {
    }

    public SubjectAverage(String subject, Double average) {
        this.subject = subject;
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
