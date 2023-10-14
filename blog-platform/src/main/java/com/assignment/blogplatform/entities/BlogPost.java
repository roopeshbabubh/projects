package com.assignment.blogplatform.entities;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "blog_posts")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long blogId;

    @Column(name = "blog_title", nullable = false)
    private String blogTitle;

    @Column(name = "blog_content", nullable = false)
    private String blogContent;

    @Column(name = "published_date", nullable = false)
    private OffsetDateTime publishedDate;

    @Column(name = "user_name", nullable = false)
    private String userName;

    public BlogPost() {
    }

    public BlogPost(Long blogId, String blogTitle, String blogContent, OffsetDateTime publishedDate, String userName) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.publishedDate = publishedDate;
        this.userName = userName;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogPostId) {
        this.blogId = blogPostId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String postTitle) {
        this.blogTitle = postTitle;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String postContent) {
        this.blogContent = postContent;
    }

    public OffsetDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(OffsetDateTime publicationDate) {
        this.publishedDate = publicationDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
