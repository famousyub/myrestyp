package com.example.crudmn.repository;


import com.example.crudmn.entity.Roles;
import com.example.crudmn.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolePollRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(RoleName roleName);
}
