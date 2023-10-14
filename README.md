# Blog Platform

This README provides instructions on how to use the Blog Platform's API, including various endpoints for user registration, changing passwords, adding roles, publishing blogs, managing tags, commenting on posts, and removing content. Please note that this API includes security measures to ensure proper authorization.

## Registering a User

You can use the following endpoint to register a new user. No security measures are applied here, so use it with caution.

### Endpoint
- **POST** `http://localhost:8080/api/users/register`

### JSON Format Examples
1. Example 1:
```json
{
    "userName": "jhon",
    "password": "fun123",
    "email": "user@example.com",
    "roleName": "ADMIN"
}
```

2. Example 2:
```json
{
    "userName": "mary",
    "password": "fun123",
    "email": "user@example.com",
    "roleName": "ADMIN"
}
```

3. Example 3:
```json
{
    "userName": "matt",
    "password": "fun123",
    "email": "user@example.com",
    "roleName": "USER"
}
```

## Changing Password

To change a user's password, you need to be either a USER or ADMIN, and basic authentication is required.

### Endpoint
- **POST** `http://localhost:8080/api/logged-users/change-password`

### JSON Format Example
```json
{
    "userName": "jhon",
    "password": "fun123"
}
```

Here, the new password is provided in the JSON body, and the old password should be provided in basic authentication.

## Adding Roles to Users

To add roles to existing users, you should be an ADMIN. Basic authentication is required.

### Endpoint
- **POST** `http://localhost:8080/api/logged-users/add-roles`

### JSON Format Example
```json
{
    "userName": "mary",
    "roleName": "USER"
}
```

## Publishing a Blog

To post a blog, you should be either a USER or ADMIN. Security measures are in place, and basic authentication is required.

### Endpoint
- **POST** `http://localhost:8080/api/blogpost/publish-blog`

### JSON Format Example
```json
{
    "userName": "mary",
    "title": "Sample Blog Post",
    "content": "This is the content of the blog post.",
    "tags": [
        {
            "category": "Technology"
        },
        {
            "category": "Programming"
        }
    ]
}
```

## Adding Tags (Categories) to Blogs

To add tags (categories) to existing blogs, you should be either a USER or ADMIN. Basic authentication is required.

### Endpoint
- **POST** `http://localhost:8080/api/blogpost/add-tags`

### JSON Format Example
```json
{
    "blogPostId": 1,
    "tags": [
        {
            "category": "Si-Fi"
        },
        {
            "category": "Fantasy"
        }
    ]
}
```

## Commenting on a Blog Post

To comment on an existing blog post, you should be either a USER or ADMIN. Basic authentication is required.

### Endpoint
- **POST** `http://localhost:8080/api/blogpost/comment-post`

### JSON Format Example
```json
{
    "blogId": 1,
    "commenterName": "jhon",
    "comment": "This is a comment on the blog post."
}
```

## Removing Content

To remove existing blogs, comments, or users, you should be an ADMIN. Security measures are in place, and basic authentication is required.

### Removing a Blog
- **DELETE** `http://localhost:8080/api/blogpost/remove-blog/{blogId}`

Use it in Postman, where `{blogId}` is the ID of the blog you want to remove.

### Removing a Comment
- **DELETE** `http://localhost:8080/api/blogpost/remove-comment/{commentId}`

Use it in Postman, where `{commentId}` is the ID of the comment you want to remove.

### Removing a User
- **POST** `http://localhost:8080/api/logged-users/remove-user`

### JSON Format Example
```json
{
    "userName": "mary"
}
```

Remember to replace `<username>` and `<password>` with your actual authentication details when using the API.
