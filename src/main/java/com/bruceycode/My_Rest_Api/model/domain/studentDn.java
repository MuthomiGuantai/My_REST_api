package com.bruceycode.My_Rest_Api.model.domain;

import com.bruceycode.My_Rest_Api.util.Validation.Adult;
import com.bruceycode.My_Rest_Api.util.Validation.ValidEmailDomain;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.Period;

public class studentDn {

    @NotNull(message = "Name cannot be null")
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

    @NotNull(message = "Gender cannot be null")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
    private String gender;

    @NotNull(message = "Hometown cannot be null")
    @Size(min = 2, max = 50, message = "Hometown must be between 2 and 50 characters")
    private String hometown;

    private Integer age;

    public studentDn() {
    }

    public studentDn(String name, String email, LocalDate dob, String gender, String hometown) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.hometown = hometown;
        this.age = calculateAge(dob);
    }

    private Integer calculateAge(LocalDate dob) {
        if (dob != null) {
            return Period.between(dob, LocalDate.now()).getYears();
        } else {
            return null;
        }
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
        this.age = calculateAge(dob); // Recalculate age when dob is updated
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // toString method
    @Override
    public String toString() {
        return "StudentFrontend{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", hometown='" + hometown + '\'' +
                ", age=" + age +
                '}';
    }
}