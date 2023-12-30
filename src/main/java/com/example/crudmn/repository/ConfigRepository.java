package com.example.crudmn.repository;

import com.example.crudmn.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {
}
