package com.assignment.blogplatform.models;

import com.assignment.blogplatform.entities.BlogPost;
import com.assignment.blogplatform.entities.Comment;
import com.assignment.blogplatform.entities.Tag;
import com.assignment.blogplatform.entities.User;

import java.util.List;

public class CommentsModel {

    private List<User> commenterDetails;
    private BlogPost blogDetails;
    private List<Comment> commentDetails;

    public CommentsModel() {
    }

    public CommentsModel(BlogPost blogDetails, List<Comment> commentDetails) {
        this.blogDetails = blogDetails;
        this.commentDetails = commentDetails;
    }

    public CommentsModel(List<User> commenterDetails, BlogPost blogDetails, List<Comment> commentDetails) {
        this.commenterDetails = commenterDetails;
        this.blogDetails = blogDetails;
        this.commentDetails = commentDetails;
    }

    public List<User> getCommenterDetails() {
        return commenterDetails;
    }

    public void setCommenterDetails(List<User> commenterDetails) {
        this.commenterDetails = commenterDetails;
    }

    public BlogPost getBlogDetails() {
        return blogDetails;
    }

    public void setBlogDetails(BlogPost blogDetails) {
        this.blogDetails = blogDetails;
    }

    public List<Comment> getCommentDetails() {
        return commentDetails;
    }

    public void setCommentDetails(List<Comment> commentDetails) {
        this.commentDetails = commentDetails;
    }
}