package com.example.crudmn.controller;

import com.example.crudmn.models.psn.CommentEntity;
import com.example.crudmn.models.psn.CommentPostRequestEntity;
import com.example.crudmn.models.psn.IdObjectEntity;
import com.example.crudmn.service.CommentPsnService;
import com.example.crudmn.service.ResponseObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class CommentPsnController {

    @Autowired
    private CommentPsnService commentService;

    @PostMapping("/insertcomment")
    public ResponseEntity<ResponseObjectService> insertComment(@RequestBody CommentPostRequestEntity postedComment) {
        CommentEntity inputComment = postedComment.getCommentEntity();
        IdObjectEntity inputPostId = postedComment.getPostId();
        return new ResponseEntity<>(commentService.insertComment(inputComment, inputPostId.getId()), HttpStatus.OK);
    }

    @PostMapping("/getcomments")
    public ResponseEntity<ResponseObjectService> getComments(@RequestBody IdObjectEntity inputPostId) {
        return new ResponseEntity<ResponseObjectService>(commentService.getComments(inputPostId.getId()), HttpStatus.OK);
    }
}
