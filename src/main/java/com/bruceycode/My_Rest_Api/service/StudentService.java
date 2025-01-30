package com.bruceycode.My_Rest_Api.service;

import com.bruceycode.My_Rest_Api.Repository.StudentRepository;
import com.bruceycode.My_Rest_Api.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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
        System.out.println(student);
    }

    public  void editStudent(Student student){
        System.out.println(student);
    }
}

