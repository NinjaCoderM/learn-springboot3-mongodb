package at.codecrafters.learnspringbootmongodb.service;

import at.codecrafters.learnspringbootmongodb.entity.Student;
import at.codecrafters.learnspringbootmongodb.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    public List<Student> getStudentByName(String name) {
        return studentRepository.findAllByName(name);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getAllStudents(int pageNr, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNr-1, pageSize);
        return studentRepository.findAll(pageRequest).getContent();
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }

    public List<Student> getStudentByNameAndEmail(String name, String email) {
        return studentRepository.findAllByNameAndEmail(name, email);
    }

    public List<Student> getStudentByNameOrEmail(String name, String email) {
        return studentRepository.findAllByNameOrEmailOrderByName(name, email);
    }


}
