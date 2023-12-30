package com.example.crudmn.repository;

import com.example.crudmn.models.StudentsExams;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentsExamsRepository extends MongoRepository<StudentsExams, String>, StudentsExamsRepositoryExtended{
}