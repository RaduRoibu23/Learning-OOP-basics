package TemaTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static TemaTest.Database.*;

public class Comment implements Likeable {
  private String commentId;
  private String postId;
  private String username;
  private String text;
  private String date;

  public static Comment getComment(String commentId) {
    ArrayList<String> lines = readFromDatabase("comments.txt");

    if (lines == null) {
      return null;
    }

    for (String line : lines) {
      String[] parts = line.split(",");

      if (parts.length < 4) {
        continue;
      }

      String id = parts[0];
      String postId = parts[1];
      String u = parts[2];
      String text = parts[3];
      String date = parts[4];

      if (id.equals(commentId)) {
        Comment comment = new Comment();
        comment.setCommentId(id);
        comment.setPostId(postId);
        comment.setUsername(u);
        comment.setText(text);
        comment.setDate(date);
        return comment;
      }
    }

    return null;
  }

  public static void createComment(String commentId, String postId, String u, String text) {
    LocalDate date = LocalDate.now();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String line = commentId + "," + postId + "," + u + "," + text + "," + date.format(dateFormatter);
    insertToDatabase("comments.txt", line);
  }

  public static void deleteComment(String commentId) {
    deleteFromDatabase("comments.txt", commentId);
  }

  public boolean canBeLiked(String username) {
    ArrayList<String> lines = readFromDatabase("comment_likes.txt");

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

      if (u.equals(username) && id.equals(commentId)) {
        return false;
      }
    }

    return true;
  }

  public int getNumberOfLikes() {
    ArrayList<String> lines = readFromDatabase("comment_likes.txt");
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

      if (id.equals(commentId)) {
        count++;
      }
    }

    return count;
  }

  public String getCommentId() {
    return commentId;
  }

  public void setCommentId(String commentId) {
    this.commentId = commentId;
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
