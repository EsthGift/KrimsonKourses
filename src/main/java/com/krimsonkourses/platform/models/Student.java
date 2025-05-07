package com.krimsonkourses.platform.models;

public class Student {

    private int id;
    private String name;
    private int currentCourse;

    public Student(int id, String name) {
        this(id, name, 0);
    }

    public Student(int id, String name, int currentCourse) {
        this.id = id;
        this.name = name;
        this.currentCourse = currentCourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(int currentCourse) {
        this.currentCourse = currentCourse;
    }
}
