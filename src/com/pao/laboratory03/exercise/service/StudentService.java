package com.pao.laboratory03.exercise.service;

import com.pao.laboratory03.exercise.exception.InvalidStudentNotFoundException;
import com.pao.laboratory03.exercise.model.Student;
import com.pao.laboratory03.exercise.model.Subject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {

    private static StudentService instance;

    private List<Student> students;

    private StudentService() {
        students = new ArrayList<>();
    }

    public static StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    public void addStudent(String name, int age) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                throw new RuntimeException("Studentul cu numele '" + name + "' exista deja");
            }
        }
        students.add(new Student(name, age));
    }

    public Student findByName(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        throw new InvalidStudentNotFoundException("Studentul cu numele '" + name + "' nu a fost gasit.");
    }

    public void addGrade(String studentName, Subject subject, double grade) {
        Student student = findByName(studentName); 
        student.addGrade(subject, grade);          
    }

    public void printAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Nu exista studenti.");
            return;
        }
        System.out.println("--- Lista Studenti ---");
        for (Student s : students) {
            System.out.println(s.toString()); 
        }
    }

    public void printTopStudents() {
        if (students.isEmpty()) {
            System.out.println("Nu exista studenti pentru a face un top.");
            return;
        }

        List<Student> sortedStudents = new ArrayList<>(students);

        sortedStudents.sort((s1, s2) -> Double.compare(s2.getAverage(), s1.getAverage()));

        System.out.println("--- Top Studenți ---");
        for (int i = 0; i < sortedStudents.size(); i++) {
            Student s = sortedStudents.get(i);
            System.out.printf("%d. %s - Medie: %.2f%n", (i + 1), s.getName(), s.getAverage());
        }
    }

    public Map<Subject, Double> getAveragePerSubject() {
        Map<Subject, Double> sumMap = new HashMap<>();
        Map<Subject, Integer> countMap = new HashMap<>();

        for (Student s : students) {
            for (Map.Entry<Subject, Double> entry : s.getGrades().entrySet()) {
                Subject sub = entry.getKey();
                Double grade = entry.getValue();

                sumMap.put(sub, sumMap.getOrDefault(sub, 0.0) + grade);
                countMap.put(sub, countMap.getOrDefault(sub, 0) + 1);
            }
        }

        Map<Subject, Double> averageMap = new HashMap<>();
        for (Subject sub : sumMap.keySet()) {
            double sum = sumMap.get(sub);
            int count = countMap.get(sub);
            averageMap.put(sub, sum / count);
        }

        return averageMap;
    }
}