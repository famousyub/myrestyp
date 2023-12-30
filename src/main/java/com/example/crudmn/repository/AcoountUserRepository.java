package com.example.crudmn.repository;

import com.example.crudmn.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcoountUserRepository extends MongoRepository<Account,String> {

    Optional<Account> findByAccName(String accName);
    
}
