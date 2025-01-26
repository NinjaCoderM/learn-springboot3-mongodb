package at.codecrafters.learnspringbootmongodb.repository;

import at.codecrafters.learnspringbootmongodb.entity.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    List<Student> findAllByName(String name);
    List<Student> findAllByNameAndEmail(String name, String email);
    List<Student> findAllByNameOrEmailOrderByName(String name, String email, Sort sort);
    List<Student> findByDepartmentDepartmentName(String departmentName);

}

