package com.example.crudmn.service;


import com.example.crudmn.dto.BlogDto;
import com.example.crudmn.entity.Blog;
import com.example.crudmn.entity.Userpoll;
import com.example.crudmn.exception.BadRequestException;
import com.example.crudmn.exception.ResourceNotFoundException;
import com.example.crudmn.repository.BlogRepository;
import com.example.crudmn.repository.UserPollRepository;
import com.example.crudmn.security.SecurityUtil;
import com.example.crudmn.security.services.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

//@Service
//@Transactional
public class BlogService {

    @Autowired
    private BlogRepository  postRepository;

    @Autowired
    private UserPollRepository  userPollRepository;


    public Optional<Blog> findForId(Long id) {
        return postRepository.findById(id);
    }

    public Blog registerPost(BlogDto postDto, UserDetailsImpl customUserDetails) {

        Userpoll user = userPollRepository.findByUsername(customUserDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", customUserDetails.getUsername()));
        Blog newPost = new Blog();
        newPost.setTitle(postDto.getTitle());
        newPost.setBody(postDto.getBody());
        //newPost.ser(customUserDetails.getUsername());
        //newPost.setCreatedBy(LocalDateTime.now().toString());

        newPost.setUser(user); // temporary code
        return postRepository.saveAndFlush(newPost);
    }

    public Optional<BlogDto> editPost(BlogDto editPostDto) {
        return this.findForId(editPostDto.getId())
                .map(p -> {
                    if (p.getUser().getEmail() != SecurityUtil.getCurrentUserLogin().get().getEmail()) {
                        throw new BadRequestException("It's not a writer.");
                    }
                    p.setTitle(editPostDto.getTitle());
                    p.setBody(editPostDto.getBody());
                    return p;
                })
                .map(BlogDto::new);
    }

    /*public Page<Blog> findByUserOrderedByCreatedDatePageable(Userpoll user, Pageable pageable) {
        return postRepository.findByUserOrderByCreatedDateDesc(user, pageable);
    }*/

   /* public Page<Blog> findAllByOrderByCreatedDateDescPageable(Pageable pageable) {
        return postRepository.findAllByOrderByCreatedDateDesc(pageable);
    }*/

    public void deletePost(Long id) {
        postRepository.findById(id).ifPresent(p -> {
            if (p.getUser().getEmail() != SecurityUtil.getCurrentUserLogin().get().getEmail()) {
                throw new BadRequestException("It's not a writer.");
            }
            postRepository.delete(p);
        });
    }
}
