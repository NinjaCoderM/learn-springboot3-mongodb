package at.codecrafters.learnspringbootmongodb.service;

import at.codecrafters.learnspringbootmongodb.entity.Student;
import at.codecrafters.learnspringbootmongodb.repository.DepartmentRepository;
import at.codecrafters.learnspringbootmongodb.repository.StudentRepository;
import at.codecrafters.learnspringbootmongodb.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public Student createStudent(Student student) {
        if(student.getDepartment() != null) {
            departmentRepository.save(student.getDepartment());
        }
        if(student.getSubjects() != null && !student.getSubjects().isEmpty()) {
            subjectRepository.saveAll(student.getSubjects());
        }
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
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        PageRequest pageRequest = PageRequest.of(pageNr-1, pageSize, sort);
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
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return studentRepository.findAllByNameOrEmailOrderByName(name, email, sort);
    }


    public List<Student> getStudentByDeptName(String deptName) {
        return studentRepository.findByDepartmentDepartmentName(deptName);
    }

    public List<Student> getStudentBySubjectName(String subjectName) {
        return studentRepository.findBySubjectsSubjectName(subjectName);
    }
}
