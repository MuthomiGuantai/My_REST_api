package com.bruceycode.My_Rest_Api.config;

import com.bruceycode.My_Rest_Api.Repository.StudentRepository;
import com.bruceycode.My_Rest_Api.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

import java.util.List;

@Configuration
public class StudentConfig implements CommandLineRunner  {
    @Autowired
    StudentRepository repository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("about to insert data");

        Student victor = new Student(


                "Victor",
                "victor@gmail.com",
                LocalDate.of(2002, Month.MARCH,5)
        );
        Student bruce = new Student(


                "Bruce",
                "bruce@gmail.com",
                LocalDate.of(1997, Month.DECEMBER,5)
        );
        repository.saveAll(List.of(bruce,victor));
    }
  /*  CommandLineRunner commandLineRunner(

    ){
        System.out.println("about to insert data");
        return args -> {
          Student bruce = new Student(

                            1L,
                            "Bruce",
                            "bruce@gmail.com",
                            LocalDate.of(1997, Month.DECEMBER,5),
                            30
            );
            Student victor = new Student(

                    2L,
                    "Victor",
                    "victor@gmail.com",
                    LocalDate.of(2002, Month.MARCH,5),
                    22
            );

            repository.saveAll(
                    List.of(bruce, victor)
            );

        };
    }*/

}
