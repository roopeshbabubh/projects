package com.assignment.blogplatform.controllers;

import com.assignment.blogplatform.entities.Tag;
import com.assignment.blogplatform.exceptions.CustomException;
import com.assignment.blogplatform.models.BlogModel;
import com.assignment.blogplatform.models.CommentModel;
import com.assignment.blogplatform.models.RequestBodyAllJson;
import com.assignment.blogplatform.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private Environment env;

    @PostMapping("/publish-blog")
    public ResponseEntity<?> createBlog(@RequestBody BlogModel blogModel) {
        if (!blogModel.getTitle().isEmpty() && !blogModel.getContent().isEmpty()
                && !blogModel.getUserName().isEmpty()) {
            return new ResponseEntity<>(blogService.publishBlog(blogModel), HttpStatus.OK);
        } else {
            throw new CustomException(env.getProperty("invalid.data"));
        }
    }

    @PostMapping("/add-tags")
    public ResponseEntity<?> assignTagsToBlog(@RequestBody BlogModel blogModel) {
        long blogId = blogModel.getBlogId();
        List<Tag> tags = blogModel.getTags();
        if (blogId > 0 && !tags.isEmpty()) {
            return new ResponseEntity<>(blogService.applyTags(blogId, tags), HttpStatus.OK);
        } else {
            throw new CustomException(env.getProperty("invalid.data"));
        }
    }

    @PostMapping("/post-comment")
    public ResponseEntity<?> commentOnPost(@RequestBody CommentModel commentModel) {
        if (commentModel.getBlogId() > 0 && !commentModel.getCommenterName().isEmpty()
                && !commentModel.getComment().isEmpty()) {
            return new ResponseEntity<>(blogService.addComment(commentModel), HttpStatus.OK);
        } else {
            throw new CustomException(env.getProperty("invalid.data"));
        }
    }

    @GetMapping("/retrieve-all-blogs/{userName}")
    public ResponseEntity<?> retrieveAllBlogs(@PathVariable("userName") String userName) {
        return new ResponseEntity<>(blogService.getAllBlogs(userName), HttpStatus.OK);
    }

    @GetMapping("/get-comments-counts-on-each-blogs")
    public ResponseEntity<?> countOfCommentsOnEachBlogs() {
        return new ResponseEntity<>(blogService.getCommentsCountOfBlogs(), HttpStatus.OK);
    }

    @GetMapping("/get-blog")
    public ResponseEntity<?> getBlog(@RequestBody RequestBodyAllJson requestBodyAllJson) {
        String title = requestBodyAllJson.getTitle();
        String content = requestBodyAllJson.getContent();
        String category = requestBodyAllJson.getCategory();
        if (!title.isEmpty() && !content.isEmpty() && !category.isEmpty()) {
            return new ResponseEntity<>(blogService.retrieveBlog(title, content, category), HttpStatus.OK);
        }
        throw new CustomException(env.getProperty("invalid.data"));
    }

    @GetMapping("/tags-and-count")
    public ResponseEntity<?> getTagsCount() {
        return new ResponseEntity<>(blogService.getPopularTags(), HttpStatus.OK);
    }

    @DeleteMapping("/remove-blog/{blogId}")
    public ResponseEntity<?> deleteBlog(@PathVariable("blogId") long blogId) {
        return new ResponseEntity<>(blogService.removeBlog(blogId), HttpStatus.OK);
    }

    @DeleteMapping("/remove-comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") long commentId) {
        return new ResponseEntity<>(blogService.removeComment(commentId), HttpStatus.OK);
    }
}

