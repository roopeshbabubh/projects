package com.assignment.blogplatform.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserModel {

    private Long userId;
    private String userName;
    private String password;
    private String email;
    private boolean active;
    private String roleName;
}
