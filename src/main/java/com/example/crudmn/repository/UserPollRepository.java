package com.example.crudmn.repository;

import com.example.crudmn.entity.Userpoll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPollRepository extends JpaRepository<Userpoll,Long> {



    @Query(" select username from Userpoll")
    List<String> findAllUsername();

    Optional<Userpoll> findByEmail(String email);

    Optional<Userpoll> findByUsernameOrEmail(String username, String email);

    List<Userpoll> findByIdIn(List<Long> userIds);

    Optional<Userpoll> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
