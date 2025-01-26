package at.codecrafters.learnspringbootmongodb.controller;

import at.codecrafters.learnspringbootmongodb.entity.Student;
import at.codecrafters.learnspringbootmongodb.error.CustomBadRequestException;
import at.codecrafters.learnspringbootmongodb.error.CustomNotFoundException;
import at.codecrafters.learnspringbootmongodb.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable String id) {
        Optional<Student> student = studentService.getStudentById(id);
        if(student.isPresent()) {
            return ResponseEntity.ok().body(student.get());
        } else {
            throw new CustomNotFoundException("Student with ID " + id + " not found");
        }
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id, @Valid @RequestBody Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomBadRequestException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        Optional<Student> studentInDB = studentService.getStudentById(id);

        if(studentInDB.isEmpty()) {
            throw new CustomNotFoundException("Student with ID " + id + " not found");
        } else  if (!id.equals(student.getId())) {
            throw new CustomBadRequestException("ID in path and body do not match");
        } else {
            try {
                studentService.save(student);
                return ResponseEntity.ok().body(studentService.getStudentById(id));
            } catch (DataIntegrityViolationException e) {
                throw new CustomBadRequestException("Invalid data provided: " + e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException("Unexpected error occurred: " + e.getMessage());
            }
        }
    }

}
