# Blog-platform

## Introduction

The Blog Platform API provides a set of endpoints for creating, retrieving, updating, and deleting user accounts, blogs, comments, and tags. The API offers a flexible and secure way to manage blog content and user data.

## Authentication

Authentication is required for various operations on this API. Most endpoints use **Basic Authentication**, where you need to provide a `<username>` and `<password>`. Make sure to include these credentials in the request headers.

## Requests

## 1. Register User

- **Description**: This is a POST request designed for the purpose of registering a new user on the platform and, optionally, activating an inactive user. 
- **Note:** You can only provide roles present in the database, which are **"USER"** and **"ADMIN."**
- **Endpoint**: [http://localhost:8080/api/users/register](http://localhost:8080/api/users/register)
- **Method**: POST
- **Authorization**: No Authentication
- **Request Body**:

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

## 2. Publish Blog

- **Description**: This is a POST request for publishing a new blog post. 
- **Note:** You can only provide categories present in the database, which include **'Technology'**, **'Travel'**, **'Food'**, **'Fashion'**, **'Sports'**, **'Science'**, **'Music'**, **'Health'**, **'Books'**, **'Movies'**, **'Fitness'**, **'Gaming'**, **'Art'**, **'History'**, and **'Nature'**.
- **Endpoint**: [http://localhost:8080/api/blog/publish-blog](http://localhost:8080/api/blog/publish-blog)
- **Method**: POST
- **Authorization**: Basic Authentication
- **Request Body**:

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
            "category": "Travel"
        }
    ]
}
```

## 3. Add Tags (Categories) to Blog

- **Description**: This is a POST request for adding tags to a blog post.
- **Note:** You can only provide categories present in the database, which include **'Technology'**, **'Travel'**, **'Food'**, **'Fashion'**, **'Sports'**, **'Science'**, **'Music'**, **'Health'**, **'Books'**, **'Movies'**, **'Fitness'**, **'Gaming'**, **'Art'**, **'History'**, and **'Nature'**.
- **Endpoint**: [http://localhost:8080/api/blog/add-tags](http://localhost:8080/api/blog/add-tags)
- **Method**: POST
- **Authorization**: Basic Authentication
- **Request Body**:

```json
{
    "blogId": 1,
    "tags": [
        {
            "category": "Art"
        },
        {
            "category": "Technology"
        }
    ]
}
```

## 4. Change Password

- **Description**: This is a POST request for changing a user's password.
- **Endpoint**: [http://localhost:8080/api/logged-users/change-password](http://localhost:8080/api/logged-users/change-password)
- **Method**: POST
- **Authorization**: Basic Authentication
- **Request Body**:

```json
{
    "userName": "jhon",
    "password": "fun123"
}
```

## 5. Remove Blog

- **Description**: This is a DELETE request to remove a specific blog post.
- **Endpoint**: [http://localhost:8080/api/blog/remove-blog/{blogId}](http://localhost:8080/api/blog/remove-blog/{blogId})
- **Method**: DELETE
- **Authorization**: Basic Authentication

## 6. Remove Comment

- **Description**: This is a DELETE request to remove a specific comment on a blog post.
- **Endpoint**: [http://localhost:8080/api/blog/remove-comment/{commentId}](http://localhost:8080/api/blog/remove-comment/{commentId})
- **Method**: DELETE
- **Authorization**: Basic Authentication

## 7. Remove User

- **Description**: This is a DELETE request to remove a user.
- **Endpoint**: [http://localhost:8080/api/logged-users/remove-user](http://localhost:8080/api/logged-users/remove-user)
- **Method**: DELETE
- **Authorization**: Basic Authentication
- **Request Body**:

```json
{
    "userName": "mary"
}
```

## 8. Change User Roles

- **Description**: This is a POST request to change a user's roles.
- **Endpoint**: [http://localhost:8080/api/logged-users/change-roles](http://localhost:8080/api/logged-users/change-roles)
- **Method**: POST
- **Authorization**: Basic Authentication
- **Request Body**:

```json
{
    "userName": "mary",
    "roleName": "ADMIN"
}
```

## 9. Post Comment on Blog

- **Description**: This is a POST request to post a comment on a specific blog post.
- **Endpoint**: [http://localhost:8080/api/blog/post-comment](http://localhost:8080/api/blog/post-comment)
- **Method**: POST
- **Authorization**: Basic Authentication
- **Request Body**:

```json
{
    "blogId": 1,
    "commenterName": "jhon",
    "comment": "This is a comment on the blog post."
}
```

## 10. Retrieve All Blogs

- **Description**: This is a GET request to retrieve all blog posts for a user.
- **Endpoint**: [http://localhost:8080/api/blog/retrieve-all-blogs/mary](http://localhost:8080/api/blog/retrieve-all-blogs/mary)
- **Method**: GET
- **Authorization**: Basic Authentication

## 11. Get Comments Counts on Each Blog

- **Description**: This is a GET request to get the comment counts for each blog.
- **Endpoint**: [http://localhost:8080/api/blog/get-comments-counts-on-each-blogs](http://localhost:8080/api/blog/get-comments-counts-on-each-blogs)
- **Method**: GET
- **Authorization**: Basic Authentication

## 12. Get Blog

- **Description**: This is a GET request to retrieve blog posts that match certain criteria.
- **Endpoint**: [http://localhost:8080/api/blog/get-blogs](http://localhost:8080/api/blog/get-blogs)
- **Method**: GET
- **Authorization**: Basic Authentication
- **Request Body**:

```json
{
    "title": "Sample Blog Post",
    "content": "This is the content of the blog post.",
    "category": "Technology"
}
```

## 13. Get Tags and it's use Counts

- **Description**: This is a GET request to retrieve tags and their counts.
- **Endpoint**: [http://localhost:8080/api/blog/tags-and-count](http://localhost:8080/api/blog/tags-and-count)
- **Method**: GET
- **Authorization**: Basic Authentication

## Response Codes

The API returns standard HTTP response codes to indicate the success or failure of a request. Common response codes include:

- 200 OK: The request was successful.
- 201 Created: The resource was successfully created.
- 204 No Content: The request was successful, and there is no additional information to send back.
- 400 Bad Request: The request was invalid or missing required parameters.
- 401 Unauthorized: The request requires user authentication or the provided credentials are invalid.
- 403 Forbidden: The authenticated user does not have access to the requested resource.
- 404 Not Found: The requested resource does not exist.
- 500 Internal Server Error: An error occurred on the server.

## Conclusion

The Blog Platform API provides powerful features for managing user accounts, blogs, comments, and tags. With proper authentication and authorization, you can effectively use this API to create, retrieve, update, and delete data. Use the provided Postman Collection to test the API endpoints and explore its capabilities.