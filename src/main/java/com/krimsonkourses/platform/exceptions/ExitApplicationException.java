package com.krimsonkourses.platform.exceptions;

public class ExitApplicationException extends Exception{
    private final String exitMessage;
    public ExitApplicationException(String message) {
        super(message);
        this.exitMessage = message;
    }

    public String getExitMessage() {
        return exitMessage;
    }
}
