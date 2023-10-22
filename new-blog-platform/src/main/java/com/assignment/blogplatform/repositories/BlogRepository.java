package com.assignment.blogplatform.repositories;

import com.assignment.blogplatform.entities.Blog;
import com.assignment.blogplatform.entities.Tag;
import com.assignment.blogplatform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    Blog findByBlogId(long blogId);

    Blog findByBlogTitle(String blogTitle);

    Blog findByBlogTitleAndBlogContentAndTags(String blogTitle, String blogContent, Tag tags);

    List<Blog> findAllByUser(User user);
}
