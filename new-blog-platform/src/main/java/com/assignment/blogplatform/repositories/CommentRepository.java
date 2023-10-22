package com.assignment.blogplatform.repositories;

import com.assignment.blogplatform.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findByCommentId(long commentId);

}
