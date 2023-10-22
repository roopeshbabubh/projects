package com.assignment.blogplatform.models;

import com.assignment.blogplatform.entities.Blog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentModel {

    private Long commentId;
    private String comment;
    private ZonedDateTime commentDate;
    private String commenterName;
    private Long blogId;
}
