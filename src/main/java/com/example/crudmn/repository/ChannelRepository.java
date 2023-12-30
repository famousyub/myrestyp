package com.example.crudmn.repository;

import com.example.crudmn.models.Channel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends MongoRepository<Channel,String> {
}
