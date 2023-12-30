package com.example.crudmn.repository;

import com.example.crudmn.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MessageRepository extends MongoRepository<Message,String> {
   @Tailable
   public Flux<Message> findWithTailableCursorByChannelId(String channelId);

}
