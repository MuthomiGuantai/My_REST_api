package com.bruceycode.My_Rest_Api.model;
import com.bruceycode.My_Rest_Api.util.validation.Adult;
import com.bruceycode.My_Rest_Api.util.validation.ValidEmailDomain;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
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
    @NotNull(message = "name cannot be null")
    @Size(min = 8, max = 20, message = "Username must be between 8 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_\\s]+$", message = "Username can only contain letters, numbers, underscores and space")
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

    public Student(Long id,
                   String name,
                   String email,
                   LocalDate dob

                   ) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(String name,
                   String email,
                   LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }

}
