package com.example.crudmn.repository;

import com.example.crudmn.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {

}
