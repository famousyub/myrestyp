package com.example.crudmn.repository;

import com.example.crudmn.entity.Blog;
import com.example.crudmn.entity.Userpoll;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


//@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

    //Page<Blog> findByUserOrderByCreatedDateDesc(Userpoll user, Pageable pageable);

   // Page<Blog> findAllByOrderByCreatedDateDesc(Pageable pageable);

    Optional<Blog> findById(Long id);

    void delete(Blog post);
}
