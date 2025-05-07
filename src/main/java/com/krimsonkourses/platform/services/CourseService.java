package com.krimsonkourses.platform.services;


import com.krimsonkourses.platform.exceptions.InvalidRequestException;
import com.krimsonkourses.platform.models.Course;
import com.krimsonkourses.platform.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseService {

    private final List<Course> courses;

    protected CourseService() {
        this.courses = new ArrayList<>();
    }

    protected int noOfCourses() {
        return this.courses.size();
    }

    protected boolean isCourseNew(String name) {
        for(Course c: courses) {
            if(c.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    protected boolean isSequenceValid(int sequence) {
        return sequence>=1;
    }

    protected boolean isCourseNextInSequenceToAdd(int sequence) {
        return sequence==courses.size()+1;
    }

    protected boolean isSequenceNew(int sequence) {
        return courses.size()<sequence;
    }

    private Course extractAddCourseRequest(String request) throws InvalidRequestException {
        String name;
        int sequence;
        Pattern pattern = Pattern.compile(Constants.ADD_COURSE_PATTERN);
        Matcher matcher = pattern.matcher(request);
        if(matcher.matches()) {
            name = matcher.group(1);
            try {
                sequence = Integer.parseInt(matcher.group(2));
            } catch (NumberFormatException e) {
                sequence = -1;
            }
        } else {
            throw new InvalidRequestException(Constants.INVALID_REQUEST_PATTERN_MESSAGE);
        }
        return new Course(sequence, name);
    }

    private int extractCourseDetailRequest(String request) throws InvalidRequestException {
        int sequence;
        Pattern pattern = Pattern.compile(Constants.COURSE_DETAIL_PATTERN);
        Matcher matcher = pattern.matcher(request);
        if(matcher.matches()) {
            try {
                sequence = Integer.parseInt(matcher.group(1));
            } catch (NumberFormatException e) {
                throw new InvalidRequestException(Constants.INVALID_REQUEST_PATTERN_MESSAGE);
            }
        } else {
            throw new InvalidRequestException(Constants.INVALID_REQUEST_PATTERN_MESSAGE);
        }
        return sequence;
    }

    protected String addCourse(String request) throws InvalidRequestException {
        Course course = extractAddCourseRequest(request);
        if(!isSequenceValid(course.getSequence())) return Constants.COURSE_SEQUENCE_NOT_VALID;
        if(!isCourseNew(course.getName())) return Constants.COURSE_NAME_ALREADY_EXIST;
        if(!isSequenceNew(course.getSequence())) return Constants.COURSE_SEQUENCE_ALREADY_EXIST;
        if(!isCourseNextInSequenceToAdd(course.getSequence())) return Constants.ADD_COURSE_IN_SEQUENCE;
        courses.add(course);
        return Constants.SUCCESS;
    }

    protected String courseDetail(String request) throws InvalidRequestException {
        int sequence = extractCourseDetailRequest(request);
        if(!isSequenceValid(sequence)) {
            return Constants.COURSE_SEQUENCE_NOT_VALID;
        }
        if(isCourseNextInSequenceToAdd(sequence) || isSequenceNew(sequence)) {
            return Constants.COURSE_NOT_EXIST;
        }
        Course course = courses.get(sequence-1);
        return String.format("Name: %s\nSequence: %d",course.getName(), course.getSequence());
    }
}
