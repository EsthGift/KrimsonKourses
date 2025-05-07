package com.krimsonkourses.platform.services;

import com.krimsonkourses.platform.exceptions.InvalidRequestException;
import com.krimsonkourses.platform.models.Student;
import com.krimsonkourses.platform.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentService {

    private final List<Student> students;

    protected StudentService() {
        this.students = new ArrayList<>();
    }

    protected int noOfStudents() {
        return this.students.size();
    }

    protected boolean isIdValid(int id) {
        return id>=0;
    }

    protected boolean isIdNew(int id) {
        for(Student student : students) {
            if(student.getId()==id) return false;
        }
        return true;
    }


    private Student extractAddStudentRequest(String request) throws InvalidRequestException {
        String name;
        int id;
        Pattern pattern = Pattern.compile(Constants.ADD_STUDENT_PATTERN);
        Matcher matcher = pattern.matcher(request);
        if(matcher.matches()) {
            name = matcher.group(1);
            try {
                id = Integer.parseInt(matcher.group(2));
            } catch (NumberFormatException e) {
                id = -1;
            }
        } else {
            throw new InvalidRequestException(Constants.INVALID_REQUEST_PATTERN_MESSAGE);
        }

        return new Student(id, name);
    }

    public String addStudent(String request) throws InvalidRequestException {
        Student student = extractAddStudentRequest(request);
        if(!isIdValid(student.getId())) return Constants.STUDENT_ID_NOT_VALID;
        if(!isIdNew(student.getId())) return Constants.STUDENT_ID_ALREADY_EXIST;
        students.add(student);
        return Constants.SUCCESS;
    }

    public String assignCourseToStudent(int studentId, int courseSequence) {
        for(Student student : students) {
            if(student.getId()==studentId) {
                if(student.getCurrentCourse()==courseSequence-1) {
                    student.setCurrentCourse(courseSequence);
                    return Constants.SUCCESS;
                } else if(student.getCurrentCourse()<courseSequence-1) {
                    return Constants.PRE_REQUISITES_NOT_COMPLETED;
                } else {
                    return Constants.COURSE_ALREADY_ASSIGNED;
                }
            }
        }
        return Constants.STUDENT_NOT_EXIST;
    }

    protected int extractStudentDetailRequest(String request) throws InvalidRequestException {
        Pattern pattern = Pattern.compile(Constants.STUDENT_DETAIL_PATTERN);
        Matcher matcher = pattern.matcher(request);
        if(matcher.matches()) {
            try {
                return Integer.parseInt(matcher.group(1));
            } catch (NumberFormatException e) {
                throw new InvalidRequestException(Constants.INVALID_REQUEST_PATTERN_MESSAGE);
            }
        } else {
            throw new InvalidRequestException(Constants.INVALID_REQUEST_PATTERN_MESSAGE);
        }
    }

    protected String studentDetail(String request) throws InvalidRequestException {
        int id = extractStudentDetailRequest(request);
        if(!isIdValid(id)) return Constants.STUDENT_ID_NOT_VALID;
        for(Student student : students) {
            if(student.getId()==id) {
                return String.format("Name: %s\nID: %d\nCourse count: %d",
                        student.getName(), student.getId(), student.getCurrentCourse());
            }
        }
        return Constants.STUDENT_NOT_EXIST;
    }

}
