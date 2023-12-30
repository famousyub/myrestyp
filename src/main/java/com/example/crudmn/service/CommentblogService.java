package com.example.crudmn.service;


import com.example.crudmn.dto.CommentblogDto;
import com.example.crudmn.entity.Blog;
import com.example.crudmn.entity.Commentblog;
import com.example.crudmn.entity.Userpoll;
import com.example.crudmn.exception.BadRequestException;
import com.example.crudmn.exception.ResourceNotFoundException;
import com.example.crudmn.repository.BlogRepository;
import com.example.crudmn.repository.CommentblogRepository;
import com.example.crudmn.repository.UserPollRepository;
import com.example.crudmn.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

//@Service
//@Transactional
public class CommentblogService {

    @Autowired
    private CommentblogRepository  commentRepository;
    @Autowired
    private BlogRepository  postRepository;

    @Autowired
    private UserPollRepository userRepository;

    public Optional<Commentblog> findForId(Long id) {
        return commentRepository.findById(id);
    }

    public Optional<Blog> findPostForId(Long id) {
        return postRepository.findById(id);
    }

    public Optional<Commentblog> findCommentsByPostId(Long id) {
        return commentRepository.findById(id);
    }

    public Commentblog registerComment(CommentblogDto commentDto, UserDetailsImpl userDetails) {

        Userpoll user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", userDetails.getUsername()));
        Optional<Blog> postForId = this.findPostForId(commentDto.getPostId());
        if (postForId.isPresent()) {
            Commentblog newComment = new Commentblog();
            newComment.setBody(commentDto.getBody());
            newComment.setBlog(postForId.get());
            newComment.setUser(user);
            return commentRepository.saveAndFlush(newComment);
        } else {
            throw new BadRequestException("Not exist post.");
        }
    }

    public Optional<CommentblogDto> editPost(CommentblogDto editCommentDto) {
        return this.findForId(editCommentDto.getId())
                .map(comment -> {
                    comment.setBody(editCommentDto.getBody());
                    return comment;
                })
                .map(CommentblogDto::new);
    }

    public void deletePost(Long id) {
        commentRepository.findById(id).ifPresent(comment -> {
            commentRepository.delete(comment);
        });
    }

}
