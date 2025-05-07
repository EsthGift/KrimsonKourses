package com.krimsonkourses.platform.models;

public class Course {

    private int sequence;
    private String name;
    public Course(int sequence, String name) {
        this.sequence = sequence;
        this.name = name;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
