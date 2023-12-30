package com.example.crudmn.repository;

import com.example.crudmn.models.Photos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  PhotosRepository extends MongoRepository<Photos,String> {

}
