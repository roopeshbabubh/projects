package com.assignment.blogplatform.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestBodyAllJson {
    private Long blogId;
    private String userName;
    private String title;
    private String content;
    private String category;
}
