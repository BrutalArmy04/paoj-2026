package com.pao.laboratory04.exercise.exception;

public class InvalidStudentNotFoundException extends RuntimeException {
    public InvalidStudentNotFoundException(String message) {
        super(message);
    }
}