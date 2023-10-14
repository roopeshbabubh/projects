package com.assignment.blogplatform.entities;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "comment_content")
    private String comment;

    @Column(name = "comment_date")
    private OffsetDateTime commentDate;

    @Column(name = "commenting_user_id")
    private Long commentingUserId;

    @Column(name = "blog_id")
    private Long blogId;

    public Comment() {
    }

    public Comment(Long commentId, String comment, OffsetDateTime commentDate, Long commentingUserId, Long blogId) {
        this.commentId = commentId;
        this.comment = comment;
        this.commentDate = commentDate;
        this.commentingUserId = commentingUserId;
        this.blogId = blogId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public OffsetDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(OffsetDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public Long getCommentingUserId() {
        return commentingUserId;
    }

    public void setCommentingUserId(Long commentingUserId) {
        this.commentingUserId = commentingUserId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}
