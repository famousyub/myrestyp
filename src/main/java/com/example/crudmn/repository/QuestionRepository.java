package com.example.crudmn.repository;


import com.example.crudmn.models.Question;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface QuestionRepository extends MongoRepository<Question, String>, QuestionRepositoryExtended {
}
