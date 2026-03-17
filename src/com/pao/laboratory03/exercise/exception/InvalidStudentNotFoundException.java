package com.pao.laboratory03.exercise.exception;

public class InvalidStudentNotFoundException extends RuntimeException {
    public InvalidStudentNotFoundException(String message) {
        super(message);
    }
}