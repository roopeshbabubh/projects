package com.assignment.blogplatform.repositories;

import com.assignment.blogplatform.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllByBlogId(long blogPostId);

    Tag findByBlogIdAndCategory(long blogId, String category);
}
