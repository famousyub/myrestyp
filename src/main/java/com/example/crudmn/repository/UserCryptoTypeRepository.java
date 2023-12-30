package com.example.crudmn.repository;

import com.example.crudmn.models.UserCryptoType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



public interface UserCryptoTypeRepository extends MongoRepository<UserCryptoType, String> {
}
