package com.example.crudmn.models.psn;

public class CommentPostRequestEntity {
    private CommentEntity commentEntity;
    private IdObjectEntity postId;

    public CommentEntity getCommentEntity() {
        return commentEntity;
    }

    public CommentPostRequestEntity() {
    }

    public CommentPostRequestEntity(CommentEntity commentEntity, IdObjectEntity postId) {
        this.commentEntity = commentEntity;
        this.postId = postId;
    }

    public void setCommentEntity(CommentEntity commentEntity) {
        this.commentEntity = commentEntity;
    }

    public IdObjectEntity getPostId() {
        return postId;
    }

    public void setPostId(IdObjectEntity postId) {
        this.postId = postId;
    }
}
