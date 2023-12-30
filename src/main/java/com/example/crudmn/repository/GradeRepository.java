package com.example.crudmn.repository;


import com.example.crudmn.models.Grade;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface GradeRepository extends MongoRepository<Grade, String>, GradeRepositoryExtended {
}
