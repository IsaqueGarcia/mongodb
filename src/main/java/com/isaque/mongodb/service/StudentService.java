package com.isaque.mongodb.service;

import com.isaque.mongodb.repository.StudentRepository;
import com.isaque.mongodb.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        student.setTimeCreated();
        return studentRepository.save(student);
    }
}
