package com.bruceycode.My_Rest_Api.config;

import com.bruceycode.My_Rest_Api.Repository.StudentRepository;
import com.bruceycode.My_Rest_Api.model.Student;
import com.bruceycode.My_Rest_Api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig implements CommandLineRunner {
    @Autowired
    StudentRepository repository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("about to insert data");

        Student victor = new Student(


                "VictorGatundu",
                "victor.gatundu@gmail.com",
                LocalDate.of(2002, Month.MARCH, 5)
        );
        Student bruce = new Student(


                "BruceMuthomi",
                "bruce.guantai@gmail.com",
                LocalDate.of(1997, Month.DECEMBER, 5)
        );
        repository.saveAll(List.of(bruce, victor));
    }
}


















