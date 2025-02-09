package com.bruceycode.My_Rest_Api.Service;
import com.bruceycode.My_Rest_Api.Repository.StudentRepository;
import com.bruceycode.My_Rest_Api.model.Student;
import com.bruceycode.My_Rest_Api.service.StudentService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(1L);
        student.setName("John Doe");
        student.setEmail("john.doe@example.com");
    }

    @Test
    void canGetStudents() {
        // when
        studentService.getStudents();

        // then
        verify(studentRepository).findAll();
    }


}