package com.assignment.blogplatform.models;


import com.assignment.blogplatform.entities.Tag;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class BlogPostModel {

    private Long blogPostId;
    private String userName;
    private String title;
    private String content;
    private OffsetDateTime publicationDate;
    private List<Tag> tags = new ArrayList<>();

    public BlogPostModel() {
    }

    public BlogPostModel(Long blogPostId, String userName, String title, String content, OffsetDateTime publicationDate, List<Tag> tags) {
        this.blogPostId = blogPostId;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.publicationDate = publicationDate;
        this.tags = tags;
    }

    public Long getBlogPostId() {
        return blogPostId;
    }

    public void setBlogPostId(Long blogPostId) {
        this.blogPostId = blogPostId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public OffsetDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(OffsetDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
