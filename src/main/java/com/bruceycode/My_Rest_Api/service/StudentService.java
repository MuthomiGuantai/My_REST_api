package com.bruceycode.My_Rest_Api.service;

import com.bruceycode.My_Rest_Api.Repository.StudentRepository;
import com.bruceycode.My_Rest_Api.exceptions.EmailTakenException;
import com.bruceycode.My_Rest_Api.exceptions.StudentNotFoundException;
import com.bruceycode.My_Rest_Api.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
       return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
       Optional<Student> studentOptional = studentRepository
               .findStudentByEmail(student.getEmail());
       if(studentOptional.isPresent()){
           throw new EmailTakenException("Email taken");
       }
       studentRepository.save(student);
    }

    @Transactional
    public  void updateStudent(Long studentId,
                               String name,
                               String email){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student with id " + studentId + " does not exist"));

        if (name != null &&
        name.length() > 0 &&
        !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new EmailTakenException("Email Taken");
            }
            student.setEmail(email);
        }
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists){
            throw new StudentNotFoundException(
                    "Student with id " + studentId + " does not exist");

        }
        studentRepository.deleteById(studentId);
    }


    public Optional<Student> getStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(
                "Student with id " + studentId + " doesn't exist"));
        return studentRepository.findById(studentId);
    }
}

