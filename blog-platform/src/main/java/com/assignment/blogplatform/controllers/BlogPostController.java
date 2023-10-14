package com.assignment.blogplatform.controllers;

import com.assignment.blogplatform.entities.Tag;
import com.assignment.blogplatform.exceptions.CustomException;
import com.assignment.blogplatform.models.BlogPostModel;
import com.assignment.blogplatform.models.CommentRequestModel;
import com.assignment.blogplatform.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogpost")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private Environment env;

    @PostMapping("/publish-blog")
    public ResponseEntity<?> createBlog(@RequestBody BlogPostModel blogPostModel) {
        if (blogPostModel.getTitle() != null && blogPostModel.getContent() != null) {
            blogPostModel.setBlogPostId(blogPostService.publishBlog(blogPostModel).getBlogId());
            if (!blogPostModel.getTags().isEmpty()) {
                blogPostModel = blogPostService.applyTags(blogPostModel.getBlogPostId(), blogPostModel.getTags());
            }
            return new ResponseEntity<>(blogPostModel, HttpStatus.OK);
        } else {
            throw new CustomException(env.getProperty("invalided.data"));
        }
    }

    @PostMapping("/add-tags")
    public ResponseEntity<?> assignTagsToBlog(@RequestBody BlogPostModel blogPostModel ) {
        long blogId = blogPostModel.getBlogPostId();
        List<Tag> tags = blogPostModel.getTags();
        if (!tags.isEmpty()) {
            var updatedBlog = blogPostService.applyTags(blogId, tags);
            return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
        } else {
            throw new CustomException("Invalid input data");
        }
    }

    @PostMapping("/comment-post")
    public ResponseEntity<?> commentOnPost(@RequestBody CommentRequestModel commentRequestModel) {
        if (commentRequestModel != null) {
            var commentDetail = blogPostService.addComment(commentRequestModel);
            return new ResponseEntity<>(commentDetail, HttpStatus.OK);
        } else {
            throw new CustomException(env.getProperty("invalided.data"));
        }
    }

    @DeleteMapping("/remove-blog/{blogId}")
    public ResponseEntity<?> deleteBlogPost(@PathVariable("blogId") long blogId) {
        var removedBlog = blogPostService.removeBlog(blogId);
        return new ResponseEntity<>(removedBlog, HttpStatus.OK);
    }

    @DeleteMapping("/remove-comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") long commentId) {
        var removedComment = blogPostService.removeComment(commentId);
        return new ResponseEntity<>(removedComment, HttpStatus.OK);
    }
}
