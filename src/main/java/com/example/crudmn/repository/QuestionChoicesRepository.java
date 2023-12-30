package com.example.crudmn.repository;



import com.example.crudmn.models.QuestionChoices;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionChoicesRepository extends MongoRepository<QuestionChoices, String>, QuestionChoicesRepositoryExtended {
}
