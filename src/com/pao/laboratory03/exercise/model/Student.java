package com.pao.laboratory03.exercise.model;

import java.util.HashMap;
import java.util.Map;

// Importăm excepțiile noastre custom
import com.pao.laboratory03.exercise.exception.InvalidStudentException;
import com.pao.laboratory03.exercise.exception.InvalidGradeException;

public class Student {
    
    private String name;
    private int age;
    private Map<Subject, Double> grades = new HashMap<>();

    public Student(String name, int age) {
        if (age < 18 || age > 60) {
            throw new InvalidStudentException("Vârsta " + age + " nu este validă (trebuie să fie între 18 și 60).");
        }
        
        this.name = name;
        this.age = age;
    }

    // --- Getteri ---
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Map<Subject, Double> getGrades() {
        return grades;
    }

    public void addGrade(Subject subject, double grade) {
        if (grade < 1 || grade > 10) {
            throw new InvalidGradeException("Nota " + grade + " este invalidă (trebuie să fie între 1 și 10).");
        }
        grades.put(subject, grade); 
    }

    public double getAverage() {
        if (grades.isEmpty()) {
            return 0.0; 
        }
        
        double sum = 0;
        for (double grade : grades.values()) {
            sum += grade;
        }
        
        return sum / grades.size();
    }

    @Override
    public String toString() {
        return String.format("Student{name='%s', age=%d, avg=%.2f}", name, age, getAverage());
    }
}