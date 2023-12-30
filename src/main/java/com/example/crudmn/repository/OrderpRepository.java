package com.example.crudmn.repository;

import com.example.crudmn.models.Ordersp;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderpRepository extends MongoRepository<Ordersp,String> {

    List<Ordersp> findAllByUser(String user_id);
}