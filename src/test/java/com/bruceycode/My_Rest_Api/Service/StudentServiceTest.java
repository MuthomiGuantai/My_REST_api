package com.bruceycode.My_Rest_Api.Service;
import com.bruceycode.My_Rest_Api.Repository.StudentRepository;
import com.bruceycode.My_Rest_Api.model.Student;
import com.bruceycode.My_Rest_Api.service.StudentService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
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
        student.setName("Bruce Muthomi");
        student.setEmail("bruce.muthomi@gmail.com");
    }

    @Test
    void canGetStudentsSuccessfully() {
        // when
        studentService.getStudents();

        // then
        verify(studentRepository).findAll();
    }

    @Test
    void canAddNewStudentSuccessfully() {
        // given
        given(studentRepository.findStudentByEmail(student.getEmail()))
                .willReturn(Optional.empty());

        // when
        studentService.addNewStudent(student);

        // then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void willBeThrowWhenEmailIsTaken() {
        // given
        given(studentRepository.findStudentByEmail(student.getEmail()))
                .willReturn(Optional.of(student));

        // when
        // then
        assertThatThrownBy(() -> studentService.addNewStudent(student))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("email taken");

        verify(studentRepository, never()).save(any());
    }

    @Test
    void canDeleteStudentSuccessfully() {
        // given
        given(studentRepository.existsById(student.getId()))
                .willReturn(true);

        // when
        studentService.deleteStudent(student.getId());

        // then
        verify(studentRepository).deleteById(student.getId());
    }

    @Test
    void willBeThrowWhenDeleteStudentNotFound() {
        // given
        given(studentRepository.existsById(student.getId()))
                .willReturn(false);

        // when
        // then
        assertThatThrownBy(() -> studentService.deleteStudent(student.getId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("student with id " + student.getId() + " does not exist");

        verify(studentRepository, never()).deleteById(any());
    }

    @Test
    void canUpdateStudentSuccessfully() {
        // given
        given(studentRepository.findById(student.getId()))
                .willReturn(Optional.of(student));

        String newName = "Victor Guantai";
        String newEmail = "victor.guantai@gmail.com";

        // when
        studentService.updateStudent(student.getId(), newName, newEmail);

        // then
        assertThat(student.getName()).isEqualTo(newName);
        assertThat(student.getEmail()).isEqualTo(newEmail);
    }
}