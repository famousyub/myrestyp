package com.example.crudmn.repository;


import com.example.crudmn.entity.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessagecRepository extends CrudRepository<Message, Long> {

    @Query("select m from Message m where m.sender.id = ?1 and m.recipient.id = ?2 or m.sender.id = ?2 and m.recipient.id = ?1")
//    @Query("select m from Message m where m.sender.id = ?1 and m.recipient.id = 2")
    List<Message> getMessagesBetweenTwoUser(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id);
}
