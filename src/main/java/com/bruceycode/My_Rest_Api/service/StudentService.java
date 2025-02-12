package com.bruceycode.My_Rest_Api.service;

import com.bruceycode.My_Rest_Api.Repository.StudentRepository;
import com.bruceycode.My_Rest_Api.config.DataSourceConfig;
import com.bruceycode.My_Rest_Api.exceptions.EmailTakenException;
import com.bruceycode.My_Rest_Api.exceptions.StudentNotFoundException;
import com.bruceycode.My_Rest_Api.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class StudentService {

    private final DataSourceConfig dataSourceConfig;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, DataSourceConfig dataSourceConfig) {
        this.studentRepository = studentRepository;
        this.dataSourceConfig = dataSourceConfig;
    }

    public List<Student> getStudents() {
        log.info("Getting all the students");
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            log.error("Email address {} is already registered", student.getEmail());
            throw new EmailTakenException("Email taken");
        }
        log.info("New student {} with email {} added", student.getName(),student.getEmail());
        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                        "Student with id " + studentId + " does not exist"));

        boolean isUpdated = false;

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            log.info("Updating name for student with id {} from {} to {}", studentId, student.getName(), name);
            student.setName(name);
            isUpdated = true;
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                log.error("Email address {} is already registered", email);
                throw new EmailTakenException("Email taken");
            }
            log.info("Updating email for student with id {} from {} to {}", studentId, student.getEmail(), email);
            student.setEmail(email);
            isUpdated = true;
        }

        if (isUpdated) {
            studentRepository.save(student);
            log.info("Student with id {} has been updated successfully.", studentId);
        } else {
            log.info("No changes detected for student with id {}.", studentId);
        }
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            log.error("Student with id {} does not exist", studentId);
            throw new StudentNotFoundException("Student with id " + studentId + " does not exist");
        }
        log.info("Deleting student with id {}", studentId);
        studentRepository.deleteById(studentId);
    }

    public Optional<Student> getStudent(Long studentId) {
        log.info("Getting student with id {}", studentId);
        return Optional.ofNullable(studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + studentId + " doesn't exist")));
    }

    public void printDataSourceDetails() {
        System.out.println("DataSource URL: " + dataSourceConfig.getUrl());
        System.out.println("DataSource Username: " + dataSourceConfig.getUsername());
        System.out.println("DataSource Password: " + dataSourceConfig.getPassword());
    }
}