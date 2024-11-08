/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package TemaTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import static TemaTest.Database.*;

public class App {
  private static int postId = 1;
  private static int commentId = 1;

  public static void main(String[] args) {
    if (args == null) {
      System.out.println("Hello world!");
      return;
    }

    String name = args[0];

    if (name.equals("-create-user")) {
      if (args.length < 2) {
        resolve("error", "'Please provide username'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");

      if (args.length < 3) {
        resolve("error", "'Please provide password'");
        return;
      }

      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) != null) {
        resolve("error", "'User already exists'");
        return;
      }

      User.createUser(u, p);
      resolve("ok", "'User created successfully'");
    }

    if (name.equals("-create-post")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      if (args.length < 4) {
        resolve("error", "'No text provided'");
        return;
      }

      String text = args[3].split(" ", 2)[1].replace("'", "");

      if (text.length() > 300) {
        resolve("error", "'Post text length exceeded'");
        return;
      }

      Post.createPost(postId++ + "", u, text);
      resolve("ok", "'Post added successfully'");
    }

    if (name.equals("-comment-post")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      if (args.length < 5) {
        resolve("error", "'No text provided'");
        return;
      }

      String postId = args[3].split(" ")[1].replace("'", "");
      String text = args[4].split(" ", 2)[1].replace("'", "");

      if (text.length() > 300) {
        resolve("error", "'Comment text length exceeded'");
        return;
      }

      Comment.createComment(commentId++ + "", postId, u, text);
      resolve("ok", "'Comment added successfully'");
    }

    if (name.equals("-delete-post-by-id")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      if (args.length < 4) {
        resolve("error", "'No identifier was provided'");
        return;
      }

      String postId = args[3].split(" ")[1].replace("'", "");

      if (Post.getPost(postId) == null) {
        resolve("error", "'The identifier was not valid'");
        return;
      }

      Post.deletePost(postId);
      resolve("ok", "'Post deleted successfully'");
    }

    if (name.equals("-delete-comment-by-id")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      if (args.length < 4) {
        resolve("error", "'No identifier was provided'");
        return;
      }

      String commentId = args[3].split(" ")[1].replace("'", "");

      if (Comment.getComment(commentId) == null || !Objects.requireNonNull(Comment.getComment(commentId)).getUsername().equals(u)) {
        resolve("error", "'The identifier was not valid'");
        return;
      }

      Comment.deleteComment(commentId);
      resolve("ok", "'Operation executed successfully'");
    }

    if (name.equals("-follow-user-by-username")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      if (args.length < 4) {
        resolve("error", "'No username to follow was provided'");
        return;
      }

      String username = args[3].split(" ")[1].replace("'", "");

      if (User.getUser(username) == null) {
        resolve("error", "'The username to follow was not valid'");
        return;
      }

      ArrayList<String> lines = readFromDatabase("followers.txt");

      if (lines != null) {
        for (String line : lines) {
          String[] parts = line.split(",");

          if (parts.length < 2) {
            continue;
          }

          String u1 = parts[0];
          String u2 = parts[1];

          if (u1.equals(u) && u2.equals(username)) {
            resolve("error", "'The username to follow was not valid'");
            return;
          }
        }
      }

      User.followUser(u, username);
      resolve("ok", "'Operation executed successfully'");
    }

    if (name.equals("-unfollow-user-by-username")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      if (args.length < 4) {
        resolve("error", "'No username to unfollow was provided'");
        return;
      }

      String username = args[3].split(" ")[1].replace("'", "");

      if (User.getUser(username) == null) {
        resolve("error", "'The username to unfollow was not valid'");
        return;
      }

      ArrayList<String> lines = readFromDatabase("followers.txt");

      if (lines != null) {
        for (String line : lines) {
          String[] parts = line.split(",");

          if (parts.length < 2) {
            continue;
          }

          String u1 = parts[0];
          String u2 = parts[1];

          if (u1.equals(u) && u2.equals(username)) {
            String search = u + "," + username;
            User.unfollowUser(search);
            resolve("ok", "'Operation executed successfully'");
            return;
          }
        }
      }

      resolve("error", "'The username to unfollow was not valid'");
    }

    if (name.equals("-like-post")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      if (args.length < 4) {
        resolve("error", "'No post identifier to like was provided'");
        return;
      }

      String postId = args[3].split(" ")[1].replace("'", "");

      if (Post.getPost(postId) == null || !Objects.requireNonNull(Post.getPost(postId)).canBeLiked(u)) {
        resolve("error", "'The post identifier to like was not valid'");
        return;
      }

      String line = u + "," + postId;
      insertToDatabase("post_likes.txt", line);
      resolve("ok", "'Operation executed successfully'");
    }

    if (name.equals("-like-comment")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      if (args.length < 4) {
        resolve("error", "'No comment identifier to like was provided'");
        return;
      }

      String commentId = args[3].split(" ")[1].replace("'", "");

      if (Comment.getComment(commentId) == null || !Objects.requireNonNull(Comment.getComment(commentId)).canBeLiked(u)) {
        resolve("error", "'The comment identifier to like was not valid'");
        return;
      }

      String line = u + "," + commentId;
      insertToDatabase("comment_likes.txt", line);
      resolve("ok", "'Operation executed successfully'");
    }

    if (name.equals("-unlike-post")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      if (args.length < 4) {
        resolve("error", "'No post identifier to unlike was provided'");
        return;
      }

      String postId = args[3].split(" ")[1].replace("'", "");

      if (Post.getPost(postId) == null || Objects.requireNonNull(Post.getPost(postId)).canBeLiked(u)) {
        resolve("error", "'The post identifier to unlike was not valid'");
        return;
      }

      String line = u + "," + postId;
      deleteFromDatabase("post_likes.txt", line);
      resolve("ok", "'Operation executed successfully'");
    }

    if (name.equals("-unlike-comment")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      if (args.length < 4) {
        resolve("error", "'No comment identifier to unlike was provided'");
        return;
      }

      String commentId = args[3].split(" ")[1].replace("'", "");

      if (Comment.getComment(commentId) == null || Objects.requireNonNull(Comment.getComment(commentId)).canBeLiked(u)) {
        resolve("error", "'The comment identifier to unlike was not valid'");
        return;
      }

      String line = u + "," + commentId;
      deleteFromDatabase("comment_likes.txt", line);
      resolve("ok", "'Operation executed successfully'");
    }

    if (name.equals("-get-followings-posts")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      ArrayList<String> lines = readFromDatabase("followers.txt");

      if (lines == null) {
        resolve("ok", "[]");
        return;
      }

      ArrayList<String> posts = new ArrayList<>();

      for (String line : lines) {
        String[] parts = line.split(",");

        if (parts.length < 2) {
          continue;
        }

        String u1 = parts[0];
        String u2 = parts[1];

        if (u1.equals(u)) {
          ArrayList<String> lines2 = readFromDatabase("posts.txt");

          if (lines2 == null) {
            continue;
          }

          for (String line2 : lines2) {
            String[] parts2 = line2.split(",");

            if (parts2.length < 4) {
              continue;
            }

            String id = parts2[0];
            String u3 = parts2[1];
            String text = parts2[2];
            String date = parts2[3];

            if (u3.equals(u2)) {
              String post = "{'post_id':'" + id + "','post_text':'" + text + "','post_date':'" + date + "','username':'" + u3 + "'}";
              posts.add(post);
            }
          }
        }
      }

      Collections.reverse(posts);
      resolve("ok", " " + posts.toString().replace(" {", "{"));
    }

    if (name.equals("-get-user-posts")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      if (args.length < 4) {
        resolve("error", "'No username to list posts was provided'");
        return;
      }

      String username = args[3].split(" ")[1].replace("'", "");

      if (User.getUser(username) == null) {
        resolve("error", "'The username to list posts was not valid'");
        return;
      }

      ArrayList<String> lines = readFromDatabase("followers.txt");

      if (lines == null) {
        resolve("ok", "[]");
        return;
      }

      boolean found = false;

      for (String line : lines) {
        String[] parts = line.split(",");

        if (parts.length < 2) {
          continue;
        }

        String u1 = parts[0];
        String u2 = parts[1];

        if (u1.equals(u) && u2.equals(username)) {
          found = true;
          break;
        }
      }

      if (!found) {
        resolve("error", "'The username to list posts was not valid'");
        return;
      }

      lines = readFromDatabase("posts.txt");

      if (lines == null) {
        resolve("ok", "[]");
        return;
      }

      ArrayList<String> posts = new ArrayList<>();

      for (String line : lines) {
        String[] parts = line.split(",");

        if (parts.length < 4) {
          continue;
        }

        String id = parts[0];
        String u2 = parts[1];
        String text = parts[2];
        String date = parts[3];

        if (u2.equals(username)) {
          String post = "{'post_id':'" + id + "','post_text':'" + text + "','post_date':'" + date + "'}";
          posts.add(post);
        }
      }

      Collections.reverse(posts);
      resolve("ok", " " + posts.toString().replace(" {", "{"));
    }

    if (name.equals("-get-following")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      ArrayList<String> lines = readFromDatabase("followers.txt");

      if (lines == null) {
        resolve("ok", "[]");
        return;
      }

      ArrayList<String> users = new ArrayList<>();

      for (String line : lines) {
        String[] parts = line.split(",");

        if (parts.length < 2) {
          continue;
        }

        String u1 = parts[0];
        String u2 = parts[1];

        if (u1.equals(u)) {
          users.add(u2);
        }
      }

      StringBuilder message = new StringBuilder(" [");

      for (String user : users) {
        message.append("'").append(user).append("',");
      }

      if (!users.isEmpty()) {
        message.deleteCharAt(message.length() - 1);
      }

      message.append("]");
      resolve("ok", message.toString());
    }

    if (name.equals("-get-followers")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      if (args.length < 4) {
        resolve("error", "'No username to list followers was provided'");
        return;
      }

      String username = args[3].split(" ")[1].replace("'", "");

      if (User.getUser(username) == null) {
        resolve("error", "'The username to list followers was not valid'");
        return;
      }

      ArrayList<String> lines = readFromDatabase("followers.txt");

      if (lines == null) {
        resolve("ok", "[]");
        return;
      }

      ArrayList<String> users = new ArrayList<>();

      for (String line : lines) {
        String[] parts = line.split(",");

        if (parts.length < 2) {
          continue;
        }

        String u1 = parts[0];
        String u2 = parts[1];

        if (u2.equals(username)) {
          users.add(u1);
        }
      }

      StringBuilder message = new StringBuilder(" [");

      for (String user : users) {
        message.append("'").append(user).append("',");
      }

      if (!users.isEmpty()) {
        message.deleteCharAt(message.length() - 1);
      }

      message.append("]");
      resolve("ok", message.toString());
    }

    if (name.equals("-get-post-details")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      if (args.length < 4) {
        resolve("error", "'No post identifier was provided'");
        return;
      }

      String postId = args[3].split(" ")[1].replace("'", "");

      if (Post.getPost(postId) == null) {
        resolve("error", "'The post identifier was not valid'");
        return;
      }

      Post post = Post.getPost(postId);
      assert post != null;

      StringBuilder message = new StringBuilder(" [{");
      message.append("'post_text':'").append(post.getText()).append("',");
      message.append("'post_date':'").append(post.getDate()).append("',");
      message.append("'username':'").append(post.getUsername()).append("',");
      message.append("'number_of_likes':'").append(post.getNumberOfLikes()).append("',");
      message.append("'comments': [");

      ArrayList<String> lines = readFromDatabase("comments.txt");

      if (lines != null) {
        for (String line : lines) {
          String[] parts = line.split(",");

          if (parts.length < 4) {
            continue;
          }

          String id = parts[0];
          String postId2 = parts[1];
          String u2 = parts[2];
          String text = parts[3];

          if (postId2.equals(postId)) {
            message.append("{'comment_id':'").append(id).append("',");
            message.append("'comment_text':'").append(text).append("',");
            message.append("'comment_date':'").append(Objects.requireNonNull(Comment.getComment(id)).getDate()).append("',");
            message.append("'username':'").append(u2).append("',");
            message.append("'number_of_likes':'").append(Objects.requireNonNull(Comment.getComment(id)).getNumberOfLikes()).append("'},");
          }
        }
      }

      if (message.charAt(message.length() - 1) == ',') {
        message.deleteCharAt(message.length() - 1);
      }

      message.append("] }] ");
      resolve("ok", message.toString());
    }

    if (name.equals("-get-most-liked-posts")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      ArrayList<String> lines = readFromDatabase("posts.txt");

      if (lines == null) {
        resolve("ok", "[]");
        return;
      }

      ArrayList<Post> posts = new ArrayList<>();

      for (String line : lines) {
        String[] parts = line.split(",");

        if (parts.length < 4) {
          continue;
        }

        String id = parts[0];
        String u2 = parts[1];
        String text = parts[2];
        String date = parts[3];

        Post post = new Post();
        post.setPostId(id);
        post.setUsername(u2);
        post.setText(text);
        post.setDate(date);
        posts.add(post);
      }

      posts.sort((o1, o2) -> o2.getNumberOfLikes() - o1.getNumberOfLikes());

      ArrayList<String> posts2 = new ArrayList<>();

      for (Post post : posts) {
        StringBuilder post2 = new StringBuilder("{'post_id':'").append(post.getPostId()).append("',");
        post2.append("'post_text':'").append(post.getText()).append("',");
        post2.append("'post_date':'").append(post.getDate()).append("',");
        post2.append("'username':'").append(post.getUsername()).append("',");
        post2.append("'number_of_likes':'").append(post.getNumberOfLikes()).append("'}");
        posts2.add(post2.toString());
      }

      StringBuilder message = new StringBuilder(" [");

      for (String post : posts2) {
        message.append(post).append(",");
      }

      if (!posts2.isEmpty()) {
        message.deleteCharAt(message.length() - 1);
      }

      message.append(" ]");
      resolve("ok", message.toString());
    }

    if (name.equals("-get-most-commented-posts")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      ArrayList<String> lines = readFromDatabase("posts.txt");

      if (lines == null) {
        resolve("ok", "[]");
        return;
      }

      ArrayList<Post> posts = new ArrayList<>();

      for (String line : lines) {
        String[] parts = line.split(",");

        if (parts.length < 4) {
          continue;
        }

        String id = parts[0];
        String u2 = parts[1];
        String text = parts[2];
        String date = parts[3];

        Post post = new Post();
        post.setPostId(id);
        post.setUsername(u2);
        post.setText(text);
        post.setDate(date);
        posts.add(post);
      }

      posts.sort((o1, o2) -> o2.getNumberOfComments() - o1.getNumberOfComments());

      ArrayList<String> posts2 = new ArrayList<>();

      for (Post post : posts) {
        StringBuilder post2 = new StringBuilder("{'post_id':'").append(post.getPostId()).append("',");
        post2.append("'post_text':'").append(post.getText()).append("',");
        post2.append("'post_date':'").append(post.getDate()).append("',");
        post2.append("'username':'").append(post.getUsername()).append("',");
        post2.append("'number_of_comments':'").append(post.getNumberOfComments()).append("'}");
        posts2.add(post2.toString());
      }

      StringBuilder message = new StringBuilder(" [");

      for (String post : posts2) {
        message.append(post).append(",");
      }

      if (!posts2.isEmpty()) {
        message.deleteCharAt(message.length() - 1);
      }

      message.append("]");
      resolve("ok", message.toString());
    }

    if (name.equals("-get-most-followed-users")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      ArrayList<String> lines = readFromDatabase("users.txt");

      if (lines == null) {
        resolve("ok", "[]");
        return;
      }

      ArrayList<User> users = new ArrayList<>();

      for (String line : lines) {
        String[] parts = line.split(",");

        if (parts.length < 2) {
          continue;
        }

        String u2 = parts[1];

        User user = new User();
        user.setUsername(u2);
        users.add(user);
      }

      users.sort((o1, o2) -> o2.getNumberOfFollowers() - o1.getNumberOfFollowers());
      users = new ArrayList<>(users.subList(0, Math.min(users.size(), 5)));

      ArrayList<String> users2 = new ArrayList<>();

      for (User user : users) {
        StringBuilder user2 = new StringBuilder("{'username':'").append(user.getUsername()).append("',");
        user2.append("'number_of_followers':'").append(user.getNumberOfFollowers()).append("'}");
        users2.add(user2.toString());
      }

      StringBuilder message = new StringBuilder(" [");

      for (String user : users2) {
        message.append(user).append(",");
      }

      if (!users2.isEmpty()) {
        message.deleteCharAt(message.length() - 1);
      }

      message.append(" ]");
      resolve("ok", message.toString());
    }

    if (name.equals("-get-most-liked-users")) {
      if (args.length < 3) {
        resolve("error", "'You need to be authenticated'");
        return;
      }

      String u = args[1].split(" ")[1].replace("'", "");
      String p = args[2].split(" ")[1].replace("'", "");

      if (User.getUser(u) == null || !Objects.requireNonNull(User.getUser(u)).getPassword().equals(p)) {
        resolve("error", "'Login failed'");
        return;
      }

      ArrayList<String> lines = readFromDatabase("users.txt");

      if (lines == null) {
        resolve("ok", "[]");
        return;
      }

      ArrayList<User> users = new ArrayList<>();

      for (String line : lines) {
        String[] parts = line.split(",");

        if (parts.length < 2) {
          continue;
        }

        String u2 = parts[1];

        User user = new User();
        user.setUsername(u2);
        users.add(user);
      }

      users.sort((o1, o2) -> o2.getNumberOfLikes() - o1.getNumberOfLikes());
      users = new ArrayList<>(users.subList(0, Math.min(users.size(), 5)));

      ArrayList<String> users2 = new ArrayList<>();

      for (User user : users) {
        StringBuilder user2 = new StringBuilder("{'username':'").append(user.getUsername()).append("',");
        user2.append("'number_of_likes':'").append(user.getNumberOfLikes()).append("'}");
        users2.add(user2.toString());
      }

      StringBuilder message = new StringBuilder(" [");

      for (String user : users2) {
        message.append(user).append(",");
      }

      if (!users2.isEmpty()) {
        message.deleteCharAt(message.length() - 1);
      }

      message.append("]");
      resolve("ok", message.toString());
    }

    if (name.equals("-cleanup-all")) {
      postId = 1;
      commentId = 1;
      cleanupDatabase("users.txt");
      cleanupDatabase("posts.txt");
      cleanupDatabase("comments.txt");
      cleanupDatabase("followers.txt");
      cleanupDatabase("post_likes.txt");
      cleanupDatabase("comment_likes.txt");
      resolve("ok", "'Cleanup finished successfully'");
    }
  }

  public static void resolve(String status, String message) {
    System.out.println("{'status':'" + status + "','message':" + message + "}");
  }
}