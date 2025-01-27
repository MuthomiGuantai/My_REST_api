package com.bruceycode.My_Rest_Api.config;

import com.bruceycode.My_Rest_Api.Repository.StudentRepository;
import com.bruceycode.My_Rest_Api.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

import java.util.List;

@Configuration
public class StudentConfig {

    CommandLineRunner commandLineRunner(
            StudentRepository repository
    ){
        return args -> {
          Student bruce = new Student(

                            1L,
                            "Bruce",
                            "bruce@gmail.com",
                            LocalDate.of(1994, Month.JANUARY,5),
                            3
            );
            Student victor = new Student(

                    1L,
                    "Victor",
                    "victor@gmail.com",
                    LocalDate.of(2002, Month.MARCH,5),
                    3
            );

            repository.saveAll(
                    List.of(bruce, victor)
            );

        };
    }

}
