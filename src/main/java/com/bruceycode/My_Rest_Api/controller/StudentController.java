package com.bruceycode.My_Rest_Api.controller;
import com.bruceycode.My_Rest_Api.model.Student;
import com.bruceycode.My_Rest_Api.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();

    }

    @GetMapping(path =  "{studentId}")
    public Optional<Student> getStudent(@PathVariable("studentId")Long studentId){
        return studentService.getStudent(studentId);
    }

    @PostMapping
    public void registerNewStudent(@Valid @RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable ("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }

    @GetMapping("/print-datasource")
    public String printDataSource() {
        studentService.printDataSourceDetails();
        return "DataSource details printed to console!";
    }

    @GetMapping("/print-setvaluedatasource")
    public String setValueDtSourceConfig() {
        studentService.setValueDtSourceConfig();
        return "DataSource details printed to console!";
    }
}