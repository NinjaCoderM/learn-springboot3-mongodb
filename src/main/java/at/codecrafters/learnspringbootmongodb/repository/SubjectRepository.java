package at.codecrafters.learnspringbootmongodb.repository;

import at.codecrafters.learnspringbootmongodb.entity.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends MongoRepository<Subject, String> {
}
