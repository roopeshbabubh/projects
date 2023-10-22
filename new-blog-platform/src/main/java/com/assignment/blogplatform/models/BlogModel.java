package com.assignment.blogplatform.models;

import com.assignment.blogplatform.entities.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BlogModel {

    private Long blogId;
    private String userName;
    private String title;
    private String content;
    private ZonedDateTime publishedDate;
    private List<Tag> tags;
    private List<CommentModel> commentDetails;
}
