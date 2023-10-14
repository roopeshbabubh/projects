package com.assignment.blogplatform.services;

import com.assignment.blogplatform.entities.BlogPost;
import com.assignment.blogplatform.entities.Comment;
import com.assignment.blogplatform.entities.Tag;
import com.assignment.blogplatform.entities.User;
import com.assignment.blogplatform.exceptions.CustomException;
import com.assignment.blogplatform.models.BlogPostModel;
import com.assignment.blogplatform.models.CommentRequestModel;
import com.assignment.blogplatform.models.CommentsModel;
import com.assignment.blogplatform.repositories.BlogPostRepository;
import com.assignment.blogplatform.repositories.CommentRepository;
import com.assignment.blogplatform.repositories.TagRepository;
import com.assignment.blogplatform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private Environment env;

    @Transactional
    public BlogPost publishBlog(BlogPostModel blogPost) {
        OffsetDateTime currentDate = OffsetDateTime.now();
        if (userRepository.existsByUserName(blogPost.getUserName())) {
            BlogPost newBlog = new BlogPost();
            newBlog.setBlogTitle(blogPost.getTitle());
            newBlog.setBlogContent(blogPost.getContent());
            newBlog.setPublishedDate(currentDate);
            newBlog.setUserName(blogPost.getUserName());
            return blogPostRepository.save(newBlog);
        } else {
            throw new CustomException(env.getProperty("user.not.found"));
        }
    }

    @Transactional
    public BlogPostModel applyTags(long blogId, List<Tag> tags) {
        if (blogPostRepository.existsByBlogId(blogId)) {
            BlogPost existingBlog = blogPostRepository.findByBlogId(blogId);
            BlogPostModel blogModel = new BlogPostModel();
            blogModel.setBlogPostId(existingBlog.getBlogId());
            blogModel.setUserName(existingBlog.getUserName());
            blogModel.setTitle(existingBlog.getBlogTitle());
            blogModel.setContent(existingBlog.getBlogContent());
            blogModel.setPublicationDate(existingBlog.getPublishedDate());
            tags.forEach(tag -> {
                if (tagRepository.findByBlogIdAndCategory(blogId, tag.getCategory()) == null) {
                    tag.setBlogId(blogId);
                    tagRepository.save(tag);
                }
            });
            blogModel.setTags(tagRepository.findAllByBlogId(blogId));
            return blogModel;
        } else {
            throw new CustomException(env.getProperty("no.blog"));
        }
    }

    public CommentsModel addComment(CommentRequestModel commentRequestModel) {
        long blogId = commentRequestModel.getBlogId();
        String commenterUserName = commentRequestModel.getCommenterName();
        String comment = commentRequestModel.getComment();
        if (userRepository.existsByUserName(commenterUserName)) {

            if (blogPostRepository.existsByBlogId(blogId)) {

                List<User> commentingUserList = new ArrayList<>();
                BlogPost existingBlog = blogPostRepository.findByBlogId(blogId);
                User user = userRepository.findByUserName(commenterUserName);
                User commentingUser = new User();
                commentingUser.setUserId(user.getUserId());
                commentingUser.setUserName(user.getUserName());
                commentingUser.setPassword(env.getProperty("show.no.password"));
                commentingUser.setEmail(user.getEmail());
                commentingUser.setActive(user.isActive());
                OffsetDateTime currentDate = OffsetDateTime.now();

                Comment newComment = new Comment();
                newComment.setComment(comment);
                newComment.setCommentDate(currentDate);
                newComment.setCommentingUserId(commentingUser.getUserId());
                newComment.setBlogId(blogId);

                List<Comment> comments = new ArrayList<>();
                comments.add(commentRepository.save(newComment));
                commentingUserList.add(commentingUser);

                return new CommentsModel(commentingUserList, existingBlog, comments);
            } else {
                throw new CustomException(env.getProperty("no.blog"));
            }
        } else {
            throw new CustomException(env.getProperty("user.not.found"));
        }
    }

    @Transactional
    public CommentsModel removeBlog(long blogId) {
        if (blogPostRepository.existsByBlogId(blogId)) {
            BlogPost blog = blogPostRepository.findByBlogId(blogId);
            List<Tag> tags = tagRepository.findAllByBlogId(blogId);
            List<Comment> comments = commentRepository.findAllByBlogId(blogId);

            if (blog != null) {
                blogPostRepository.deleteByBlogId(blogId);
            }
            if (!comments.isEmpty()) {
                commentRepository.deleteAll(comments);
            }
            if (!tags.isEmpty()) {
                tagRepository.deleteAll(tags);
            }

            List<User> commenters = new ArrayList<>();
            comments.forEach(comment -> {
                var user = userRepository.findByUserId(comment.getCommentingUserId());

                User commentingUser = new User();
                commentingUser.setUserId(user.getUserId());
                commentingUser.setUserName(user.getUserName());
                commentingUser.setPassword(env.getProperty("show.no.password"));
                commentingUser.setEmail(user.getEmail());
                commentingUser.setActive(user.isActive());
                commenters.add(commentingUser);
            });

            return new CommentsModel(commenters, blog, comments);
        } else {
            throw new CustomException(env.getProperty("no.blog"));
        }
    }

    @Transactional
    public Comment removeComment(long commentId) {
        if (commentRepository.existsById(commentId)) {
            var comment = commentRepository.findByCommentId(commentId);
            if (comment != null) {
                commentRepository.delete(comment);
            }
            return comment;
        } else {
            throw new CustomException(env.getProperty("no.comment"));
        }
    }
}
