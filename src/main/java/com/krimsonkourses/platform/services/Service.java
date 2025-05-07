package com.krimsonkourses.platform.services;

import com.krimsonkourses.platform.exceptions.ExitApplicationException;
import com.krimsonkourses.platform.exceptions.InvalidRequestException;
import com.krimsonkourses.platform.util.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Service {




    private CourseService courseService;
    private StudentService studentService;

    public Service() {
        this.courseService = new CourseService();
        this.studentService = new StudentService();
    }

    public String request(String request) throws InvalidRequestException, ExitApplicationException {
        if(request.startsWith("ADD_COURSE")) {
            return courseService.addCourse(request);
        } else if(request.startsWith("ADD_STUDENT")) {
            return studentService.addStudent(request);
        } else if(request.startsWith("COURSE_DETAIL")) {
            return courseService.courseDetail(request);
        } else if(request.startsWith("COURSE_ASSIGN")) {
            return assignCourse(request);
        } else if(request.startsWith("STUDENT_DETAIL")) {
            return studentService.studentDetail(request);
        } else if (request.startsWith("EXIT")){
            String exitMessage = exitApplication(request);
            throw new ExitApplicationException(exitMessage);
        } else {
            return Constants.REQUEST_NOT_SUPPORTED;
        }
    }

    private String exitApplication(String request) throws InvalidRequestException {
        if(!request.equals("EXIT")) throw new InvalidRequestException(Constants.INVALID_REQUEST_PATTERN_MESSAGE);
        return String.format("Course count: %d\nStudent count: %d\nAdios!",
                courseService.noOfCourses(), studentService.noOfStudents());
    }


    private Map<String, Integer> extractAssignCourseRequest(String request,
                                                            String courseSequenceKey,
                                                            String studentIdKey) throws InvalidRequestException {
        Map<String, Integer> map = new HashMap<>();
        Pattern pattern = Pattern.compile(Constants.COURSE_ASSIGN_PATTERN);
        Matcher matcher = pattern.matcher(request);
        if(matcher.matches()) {
            try {
                int studentId = Integer.parseInt(matcher.group(1));
                int courseSequence = Integer.parseInt(matcher.group(2));
                map.put(courseSequenceKey, courseSequence);
                map.put(studentIdKey, studentId);
            } catch (NumberFormatException e) {
                throw new InvalidRequestException(Constants.INVALID_REQUEST_PATTERN_MESSAGE);
            }
        } else {
            throw new InvalidRequestException(Constants.INVALID_REQUEST_PATTERN_MESSAGE);
        }
        return map;
    }


    private String assignCourse(String request) throws InvalidRequestException {
        String courseSequenceKey = "courseSequence", studentIdKey = "studentId";
        Map<String, Integer> map = extractAssignCourseRequest(request, courseSequenceKey, studentIdKey);
        int courseSequence = map.getOrDefault(courseSequenceKey, -1);
        int studentId = map.getOrDefault(studentIdKey, -1);
        if(!studentService.isIdValid(studentId)) {
            return Constants.STUDENT_ID_NOT_VALID;
        }
        if(!courseService.isSequenceValid(courseSequence)) {
            return Constants.COURSE_SEQUENCE_NOT_VALID;
        }
        if(studentService.isIdNew(studentId)) {
            return Constants.STUDENT_NOT_EXIST;
        }
        if(courseService.isSequenceNew(courseSequence)) {
            return Constants.COURSE_NOT_EXIST;
        }
        return studentService.assignCourseToStudent(studentId, courseSequence);
    }
}
