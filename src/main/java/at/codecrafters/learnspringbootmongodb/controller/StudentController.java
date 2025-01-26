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
    public Student createStudent(@Valid @RequestBody Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomBadRequestException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
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

    @GetMapping("/getByName")
    public ResponseEntity<?> getStudentByName(@RequestParam String name) {
        List<Student> student = studentService.getStudentByName(name);
        if (student.isEmpty()) {
            throw new CustomNotFoundException("Student with name: " + name + " not found");
        } else {
            return ResponseEntity.ok().body(student);
        }
    }

    @GetMapping("/studentByNameAndEmail")
    public ResponseEntity<?> getStudentByNameAndEmail(@RequestParam String name, @RequestParam String email) {
        List<Student> student = studentService.getStudentByNameAndEmail(name, email);
        if (student.isEmpty()) {
            throw new CustomNotFoundException("Student with name: " + name + " and " + email + " not found");
        } else {
            return ResponseEntity.ok().body(student);
        }
    }

    @GetMapping("/studentByNameOrEmail")
    public ResponseEntity<?> getStudentByNameOrEmail(@RequestParam String name, @RequestParam String email) {
        List<Student> student = studentService.getStudentByNameOrEmail(name, email);
        if (student.isEmpty()) {
            throw new CustomNotFoundException("Student with name: " + name + " and " + email + " not found");
        } else {
            return ResponseEntity.ok().body(student);
        }
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/allPageable")
    public ResponseEntity<?> getAllStudents(@RequestParam int pageNr, @RequestParam int pageSize) {
        List<Student> student = studentService.getAllStudents(pageNr, pageSize);
        if (student.isEmpty()) {
            throw new CustomNotFoundException("all Students with pageNr: " + pageNr + " and " + pageSize + " is empty");
        } else {
            return ResponseEntity.ok().body(student);
        }
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable String id) {
        Optional<Student> student = studentService.getStudentById(id);
        if(student.isPresent()) {
            studentService.delete(student.get());
            return ResponseEntity.ok().body("Student with ID " + id + " deleted successfully");
        } else {
            throw new CustomNotFoundException("Student with ID " + id + " not found");
        }
    }

}
