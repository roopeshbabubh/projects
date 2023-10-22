package com.assignment.blogplatform.services;

import com.assignment.blogplatform.entities.Blog;
import com.assignment.blogplatform.entities.Comment;
import com.assignment.blogplatform.entities.Tag;
import com.assignment.blogplatform.entities.User;
import com.assignment.blogplatform.exceptions.CustomException;
import com.assignment.blogplatform.models.BlogDetailsAndCommentSCount;
import com.assignment.blogplatform.models.BlogModel;
import com.assignment.blogplatform.models.CommentModel;
import com.assignment.blogplatform.repositories.BlogRepository;
import com.assignment.blogplatform.repositories.CommentRepository;
import com.assignment.blogplatform.repositories.TagRepository;
import com.assignment.blogplatform.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private Environment env;

    ZoneId zone = ZoneId.of("Asia/Kolkata");
    ZonedDateTime currentDate = ZonedDateTime.now(zone);

    public Blog publishBlog(BlogModel blogModel) {
        if (blogRepository.findByBlogTitle(blogModel.getTitle()) != null) {
            throw new CustomException(env.getProperty("blog.exists"));
        }
        Blog newBlog = new Blog();
        newBlog.setBlogTitle(blogModel.getTitle());
        newBlog.setBlogContent(blogModel.getContent());
        newBlog.setPublishedDate(currentDate);

        User existsUser = userRepository.findByUserName(blogModel.getUserName());
        if (existsUser != null) {
            newBlog.setUser(existsUser);
        } else {
            throw new CustomException(env.getProperty("no.user"));
        }
        if (!blogModel.getTags().isEmpty()) {
            newBlog.setTags(getTagList(blogModel.getTags()));
        }
        return blogRepository.save(newBlog);
    }

    public BlogModel applyTags(long blogId, List<Tag> tags){
        Blog existsBlog = blogRepository.findByBlogId(blogId);
        if (existsBlog != null) {
            List<Tag> existsTags = getTagList(tags);
            existsTags.forEach(existsTag -> {
                if (!existsBlog.getTags().contains(existsTag)) {
                    existsBlog.getTags().add(existsTag);
                }
            });
            return buildBlogModel(blogRepository.save(existsBlog));
        } else {
            throw new CustomException(env.getProperty("no.blog"));
        }
    }

    public List<Tag> getTagList(List<Tag> tags) {
        List<Tag> existsTags = new ArrayList<>();
        tags.forEach(tag -> {
            Tag exixtTag = tagRepository.findByCategory(tag.getCategory());
            if (exixtTag !=  null) {
                existsTags.add(exixtTag);
            } else {
                throw new CustomException(env.getProperty("no.tag"));
            }
        });
        return existsTags;
    }

    public CommentModel addComment(CommentModel commentModel) {
        Comment newComment = new Comment();
        newComment.setComment(commentModel.getComment());
        newComment.setCommentDate(currentDate);
        User existsUser = userRepository.findByUserName(commentModel.getCommenterName());
        if (existsUser != null) {
            newComment.setUser(existsUser);
        } else {
            throw new CustomException(env.getProperty("no.user"));
        }
        Blog existsBlog = blogRepository.findByBlogId(commentModel.getBlogId());
        if (existsBlog != null) {
            newComment.setBlog(existsBlog);
        } else {
            throw new CustomException(env.getProperty("no.blog"));
        }
        newComment = commentRepository.save(newComment);

        commentModel.setCommentId(newComment.getCommentId());
        commentModel.setCommentDate(newComment.getCommentDate());
        return commentModel;
    }

    public List<BlogModel> getAllBlogs(String userName) {
        User existsUser = userRepository.findByUserName(userName);
        if (existsUser != null) {
            List<BlogModel> blogModelList = new ArrayList<>();
            List<Blog> blogList = blogRepository.findAllByUser(existsUser);
            if (!blogList.isEmpty()) {
                blogList.forEach(blog -> {
                    blogModelList.add(buildBlogModel(blog));
                });
            }
            return blogModelList;
        } else {
            throw new CustomException(env.getProperty("no.user"));
        }
    }

    public List<BlogDetailsAndCommentSCount> getCommentsCountOfBlogs() {
        List<BlogDetailsAndCommentSCount> blogsWithCommentCountList = new ArrayList<>();
        List<Blog> blogList = blogRepository.findAll();
        if (blogList != null) {
            blogList.forEach(blog -> {
                BlogDetailsAndCommentSCount blogsWithCommentCount = new BlogDetailsAndCommentSCount();
                blogsWithCommentCount.setBlogId(blog.getBlogId());
                blogsWithCommentCount.setTitle(blog.getBlogTitle());
                blogsWithCommentCount.setContent(blog.getBlogContent());
                blogsWithCommentCount.setCommentsCount(blog.getComments().size());
                blogsWithCommentCountList.add(blogsWithCommentCount);
            });
            return blogsWithCommentCountList;
        } else {
            throw new CustomException(env.getProperty("no.blog"));
        }
    }

    public BlogModel retrieveBlog(String title, String content, String category) {
        Tag exixtTag = tagRepository.findByCategory(category);
        if (exixtTag == null) {
            throw new CustomException(env.getProperty("no.tag"));
        }
        Blog existsBlog = blogRepository.findByBlogTitleAndBlogContentAndTags(title, content, exixtTag);
        if (existsBlog != null) {
            return buildBlogModel(existsBlog);
        } else {
            throw new CustomException(env.getProperty("no.blog"));
        }
    }

    public Map<String, Long> getPopularTags() {
        return blogRepository.findAll().stream()
                .flatMap(blog -> blog.getTags().stream())
                .collect(Collectors.groupingBy(Tag::getCategory, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    @Transactional
    public BlogModel removeBlog(long blogId) {
        Blog existsBlog = blogRepository.findByBlogId(blogId);
        if (existsBlog != null) {
            blogRepository.delete(existsBlog);
            return buildBlogModel(existsBlog);
        } else {
            throw new CustomException(env.getProperty("no.blog"));
        }
    }

    public BlogModel buildBlogModel(Blog existsBlog) {
        BlogModel blogModel = new BlogModel();
        blogModel.setBlogId(existsBlog.getBlogId());
        blogModel.setUserName(existsBlog.getUser().getUserName());
        blogModel.setTitle(existsBlog.getBlogTitle());
        blogModel.setContent(existsBlog.getBlogContent());
        blogModel.setPublishedDate(existsBlog.getPublishedDate());
        blogModel.setTags(existsBlog.getTags());
        List<CommentModel> commentDetails = new ArrayList<>();
        if (existsBlog.getComments() != null) {
            existsBlog.getComments().forEach(comment -> {
                commentDetails.add(buildCommentModel(comment));
            });
        }
        blogModel.setCommentDetails(commentDetails);
        return blogModel;
    }

    @Transactional
    public CommentModel removeComment(long commentId) {
        Comment existsComment = commentRepository.findByCommentId(commentId);
        if (existsComment != null) {
            commentRepository.delete(existsComment);
            return buildCommentModel(existsComment);
        } else {
            throw new CustomException(env.getProperty("no.comment"));
        }
    }

    public CommentModel buildCommentModel(Comment existsComment) {
        CommentModel commentModel = new CommentModel();
        commentModel.setCommentId(existsComment.getCommentId());
        commentModel.setComment(existsComment.getComment());
        commentModel.setCommentDate(existsComment.getCommentDate());
        commentModel.setCommenterName(existsComment.getUser().getUserName());
        commentModel.setBlogId(existsComment.getBlog().getBlogId());
        return commentModel;
    }
}
