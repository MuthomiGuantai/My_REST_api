package com.bruceycode.My_Rest_Api.model;
import com.bruceycode.My_Rest_Api.util.Validation.Adult;
import com.bruceycode.My_Rest_Api.util.Validation.ValidEmailDomain;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
@Data
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    @NotNull(message = "Name cannot be null")
    @Size(min = 8, max = 20, message = "Name must be between 8 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_\\s]+$", message = "Name can only contain letters, numbers, underscores and space")
    private String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    @ValidEmailDomain(message = "Email domain must be from example.com or test.com")
    private String email;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    @Adult(message = "Student must be at least 18 years old")
    private LocalDate dob;

    @Transient
    private Integer age;
    public Student() {

    }

    public Student(String name,
                   String email,
                   LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }


}
