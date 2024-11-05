package TemaTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static TemaTest.Database.*;

public class Post implements Likeable {
  private String postId;
  private String username;
  private String text;
  private String date;

  public static Post getPost(String postId) {
    ArrayList<String> lines = readFromDatabase("posts.txt");

    if (lines == null) {
      return null;
    }

    for (String line : lines) {
      String[] parts = line.split(",");

      if (parts.length < 4) {
        continue;
      }

      String id = parts[0];
      String u = parts[1];
      String text = parts[2];
      String date = parts[3];

      if (id.equals(postId)) {
        Post post = new Post();
        post.setPostId(id);
        post.setUsername(u);
        post.setText(text);
        post.setDate(date);
        return post;
      }
    }

    return null;
  }

  public static void createPost(String postId, String u, String text) {
    LocalDate date = LocalDate.now();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String line = postId + "," + u + "," + text + "," + date.format(dateFormatter);
    insertToDatabase("posts.txt", line);
  }

  public static void deletePost(String postId) {
    deleteFromDatabase("posts.txt", postId);
  }

  public boolean canBeLiked(String username) {
    ArrayList<String> lines = readFromDatabase("post_likes.txt");

    if (lines == null) {
      return true;
    }

    for (String line : lines) {
      String[] parts = line.split(",");

      if (parts.length < 2) {
        continue;
      }

      String u = parts[0];
      String id = parts[1];

      if (u.equals(username) && id.equals(postId)) {
        return false;
      }
    }

    return true;
  }

  public int getNumberOfLikes() {
    ArrayList<String> lines = readFromDatabase("post_likes.txt");
    int count = 0;

    if (lines == null) {
      return count;
    }

    for (String line : lines) {
      String[] parts = line.split(",");

      if (parts.length < 2) {
        continue;
      }

      String id = parts[1];

      if (id.equals(postId)) {
        count++;
      }
    }

    return count;
  }

  public int getNumberOfComments() {
    ArrayList<String> lines = readFromDatabase("comments.txt");
    int count = 0;

    if (lines == null) {
      return count;
    }

    for (String line : lines) {
      String[] parts = line.split(",");

      if (parts.length < 3) {
        continue;
      }

      String id = parts[1];

      if (id.equals(postId)) {
        count++;
      }
    }

    return count;
  }

  public String getPostId() {
    return postId;
  }

  public void setPostId(String postId) {
    this.postId = postId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
