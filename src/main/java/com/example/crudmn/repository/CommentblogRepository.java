package com.example.crudmn.repository;

import com.example.crudmn.entity.Commentblog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


//@Repository
public interface CommentblogRepository extends JpaRepository<Commentblog, Long> {

    public Optional<List<Commentblog>> findByBlog(Long blogId);

}
