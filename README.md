# Java Social Media Application

## Overview

This Java application serves as a basic social media platform, featuring user authentication, post creation, comments, and various user interactions. The primary goal of this project is to apply the principles of Object-Oriented Programming (OOP) while developing a cohesive and functional application as part of my university coursework.

## Learning Objectives

In this project, I aimed to learn the fundamentals of OOP by implementing key concepts such as encapsulation, inheritance, and polymorphism. The entire code structure has been oriented towards these principles, resulting in an implementation that not only meets the requirements but also addresses them in an elegant and cohesive manner.

## Brief Application Description

### Classes Overview

- **App**: 
    - **Description**: The main class that contains the entry point of the application and user command logic.
    - **Methods**:
        - `main(String[] args)`: Entry point for the application. Parses user commands and executes the corresponding actions.
        - `resolve(String status, String message)`: Helper method to display response messages in a specified format.

- **User**: 
    - **Description**: Represents a user in the social media application.
    - **Methods**:
        - `createUser(String username, String password)`: Creates a new user.
        - `getUser(String username)`: Retrieves a user by username.
        - `followUser(String follower, String following)`: Adds a user to the follower list.
        - `unfollowUser(String line)`: Removes a user from the follower list.

- **Post**: 
    - **Description**: Represents a post created by a user.
    - **Methods**:
        - `createPost(String postId, String username, String text)`: Creates a new post.
        - `getPost(String postId)`: Retrieves a post by post ID.
        - `deletePost(String postId)`: Deletes a post.

- **Comment**: 
    - **Description**: Represents a comment on a post.
    - **Methods**:
        - `createComment(String commentId, String postId, String username, String text)`: Creates a new comment.
        - `getComment(String commentId)`: Retrieves a comment by comment ID.
        - `deleteComment(String commentId)`: Deletes a comment.

- **Database**: 
    - **Description**: Provides methods for reading and writing to the database files.
    - **Methods**:
        - `readFromDatabase(String filename)`: Reads lines from a specified database file.
        - `insertToDatabase(String filename, String line)`: Inserts a line into a specified database file.
        - `deleteFromDatabase(String filename, String line)`: Deletes a line from a specified database file.
        - `cleanupDatabase(String filename)`: Deletes all entries from a specified database file.

## Conclusion

This project has been a valuable learning experience, allowing me to apply theoretical OOP principles in a practical context. I look forward to expanding my skills in software development and enhancing the application further in the future.
