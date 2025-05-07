package com.krimsonkourses.platform.util;

public class Constants {

    public static final String INVALID_REQUEST_PATTERN_MESSAGE = "REQUEST_PATTERN_INVALID";
    public static final String SUCCESS = "SUCCESS";
    public static final String REQUEST_NOT_SUPPORTED = "REQUEST_NOT_SUPPORTED";
    public static final String COURSE_ASSIGN_PATTERN = "COURSE_ASSIGN STUDENT (.+) COURSE (.+)";

    // Course Service
    public static final String ADD_COURSE_PATTERN = "ADD_COURSE NAME (.+) SEQUENCE (.+)";
    public static final String COURSE_DETAIL_PATTERN = "COURSE_DETAIL (.+)";
    public static final String COURSE_SEQUENCE_NOT_VALID = "COURSE_SEQUENCE_NOT_VALID";
    public static final String COURSE_NAME_ALREADY_EXIST = "COURSE_NAME_ALREADY_EXIST";
    public static final String COURSE_SEQUENCE_ALREADY_EXIST = "COURSE_SEQUENCE_ALREADY_EXIST";
    public static final String ADD_COURSE_IN_SEQUENCE = "ADD_COURSE_IN_SEQUENCE";
    public static final String COURSE_NOT_EXIST = "COURSE_NOT_EXIST";

    // Student Service
    public static final String ADD_STUDENT_PATTERN = "ADD_STUDENT NAME (.+) ID (.+)";
    public static final String STUDENT_DETAIL_PATTERN = "STUDENT_DETAIL (.+)";
    public static final String STUDENT_ID_NOT_VALID = "STUDENT_ID_NOT_VALID";
    public static final String STUDENT_ID_ALREADY_EXIST = "STUDENT_ID_ALREADY_EXIST";
    public static final String STUDENT_NOT_EXIST = "STUDENT_NOT_EXIST";
    public static final String PRE_REQUISITES_NOT_COMPLETED = "PRE_REQUISITES_NOT_COMPLETED";
    public static final String COURSE_ALREADY_ASSIGNED = "COURSE_ALREADY_ASSIGNED";
}
