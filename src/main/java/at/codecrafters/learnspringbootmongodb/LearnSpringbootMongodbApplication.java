package at.codecrafters.learnspringbootmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories("at.codecrafters.learnspringbootmongodb.repository")
public class LearnSpringbootMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnSpringbootMongodbApplication.class, args);
    }

}
