package com.assignment.blogplatform.repositories;

import com.assignment.blogplatform.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    BlogPost findByBlogId(long blogId);

    boolean existsByBlogId(long blogId);

    void deleteByBlogId(long blogId);

}
