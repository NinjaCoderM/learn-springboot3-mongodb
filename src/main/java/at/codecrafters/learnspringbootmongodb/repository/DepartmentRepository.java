package at.codecrafters.learnspringbootmongodb.repository;

import at.codecrafters.learnspringbootmongodb.entity.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {
}
