package com.example.crudmn.repository;


import com.example.crudmn.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail,Long> {
    UserDetail findByUsername(String username);
    String findByEmailID(String emailID);
}
