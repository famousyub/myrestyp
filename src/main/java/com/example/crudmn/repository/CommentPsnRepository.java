package com.example.crudmn.repository;


import com.example.crudmn.models.psn.CommentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentPsnRepository extends MongoRepository<CommentEntity, String> {
}
