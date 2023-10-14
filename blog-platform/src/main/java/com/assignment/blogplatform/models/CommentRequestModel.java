package com.assignment.blogplatform.models;

public class CommentRequestModel {
    private Long blogId;
    private String commenterName;
    private String comment;

    public CommentRequestModel() {
    }

    public CommentRequestModel(Long blogId, String commenterName, String comment) {
        this.blogId = blogId;
        this.commenterName = commenterName;
        this.comment = comment;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
