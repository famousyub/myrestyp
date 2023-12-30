package com.example.crudmn.repository;

import com.example.crudmn.entity.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobRepository extends CrudRepository<Job,Long> {
}
