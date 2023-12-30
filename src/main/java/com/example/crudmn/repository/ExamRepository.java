package com.example.crudmn.repository;

import com.example.crudmn.models.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExamRepository extends MongoRepository<Exam, String>, ExamRepositoryExtended {
}