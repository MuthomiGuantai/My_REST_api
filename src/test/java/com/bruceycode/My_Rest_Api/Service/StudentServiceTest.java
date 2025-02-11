package com.bruceycode.My_Rest_Api.Service;
import com.bruceycode.My_Rest_Api.Repository.StudentRepository;
import com.bruceycode.My_Rest_Api.exceptions.EmailTakenException;
import com.bruceycode.My_Rest_Api.exceptions.GlobalExceptionHandler;
import com.bruceycode.My_Rest_Api.exceptions.StudentNotFoundException;
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
        studentService.getStudents();

        verify(studentRepository).findAll();
    }

    @Test
    void canAddNewStudentSuccessfully() {
        given(studentRepository.findStudentByEmail(student.getEmail()))
                .willReturn(Optional.empty());

        studentService.addNewStudent(student);

        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void willBeThrowWhenEmailIsTaken() {
        given(studentRepository.findStudentByEmail(student.getEmail()))
                .willReturn(Optional.of(student));

        assertThatThrownBy(() -> studentService.addNewStudent(student))
                .isInstanceOf(EmailTakenException.class)
                .hasMessageContaining("email taken");

        verify(studentRepository, never()).save(any());
    }

    @Test
    void canDeleteStudentSuccessfully() {
        given(studentRepository.existsById(student.getId()))
                .willReturn(true);

        studentService.deleteStudent(student.getId());

        verify(studentRepository).deleteById(student.getId());
    }

    @Test
    void willThrowWhenDeleteStudentNotFound() {
        given(studentRepository.existsById(student.getId()))
                .willReturn(false);

        assertThatThrownBy(() -> studentService.deleteStudent(student.getId()))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Student with id " + student.getId() + " does not exist");

        verify(studentRepository, never()).deleteById(any());
    }

    @Test
    void canUpdateStudentSuccessfully() {
        given(studentRepository.findById(student.getId()))
                .willReturn(Optional.of(student));

        String newName = "Victor Guantai";
        String newEmail = "victor.guantai@gmail.com";

        studentService.updateStudent(student.getId(), newName, newEmail);

        assertThat(student.getName()).isEqualTo(newName);
        assertThat(student.getEmail()).isEqualTo(newEmail);
    }

    @Test
    void willThrowWhenUpdateStudentNotFound() {
        given(studentRepository.findById(student.getId()))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> studentService.updateStudent(student.getId(), "Victor Guantai", "victor.guantai@gmal.com"))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Student with id " + student.getId() + " does not exist");
    }

    @Test
    void willThrowWhenUpdateStudentEmailIsTaken() {
        Student existingStudent = new Student();
        existingStudent.setId(2L);
        existingStudent.setName("Victor Guantai");
        existingStudent.setEmail("Victor.guantai@gmail.com");

        given(studentRepository.findById(student.getId()))
                .willReturn(Optional.of(student));
        given(studentRepository.findStudentByEmail(existingStudent.getEmail()))
                .willReturn(Optional.of(existingStudent));

        assertThatThrownBy(() -> studentService.updateStudent(student.getId(), "Victor Guantai", existingStudent.getEmail()))
                .isInstanceOf(EmailTakenException.class)
                .hasMessageContaining("Email Taken");
    }

    @Test
    void canGetStudentByIdSuccessfully() {
        // given
        given(studentRepository.findById(student.getId()))
                .willReturn(Optional.of(student));

        Optional<Student> result = studentService.getStudent(student.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(student);
    }

    @Test
    void willThrowWhenGetStudentNotFound() {
        given(studentRepository.findById(student.getId()))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> studentService.getStudent(student.getId()))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Student with id " + student.getId() + " doesn't exist");
    }

}