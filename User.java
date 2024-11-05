package TemaTest;

import java.util.ArrayList;
import java.util.Objects;

import static TemaTest.Database.*;

public class User {
  private String username;
  private String password;

  public static User getUser(String username) {
    ArrayList<String> lines = readFromDatabase("users.txt");

    if (lines == null) {
      return null;
    }

    for (String line : lines) {
      String[] parts = line.split(",");

      if (parts.length < 2) {
        continue;
      }

      String u = parts[0];
      String p = parts[1];

      if (u.equals(username)) {
        User user = new User();
        user.setUsername(u);
        user.setPassword(p);
        return user;
      }
    }

    return null;
  }

  public static void createUser(String u, String p) {
    String line = u + "," + p;
    insertToDatabase("users.txt", line);
  }

  public static void followUser(String u, String username) {
    String line = u + "," + username;
    insertToDatabase("followers.txt", line);
  }

  public static void unfollowUser(String search) {
    deleteFromDatabase("followers.txt", search);
  }

  public int getNumberOfFollowers() {
    ArrayList<String> lines = readFromDatabase("followers.txt");

    if (lines == null) {
      return 0;
    }

    int count = 0;

    for (String line : lines) {
      String[] parts = line.split(",");

      if (parts.length < 2) {
        continue;
      }

      String u = parts[1];

      if (u.equals(username)) {
        count++;
      }
    }

    return count;
  }

  public int getNumberOfLikes() {
    ArrayList<String> lines1 = readFromDatabase("posts.txt");
    ArrayList<String> lines2 = readFromDatabase("comments.txt");

    if (lines1 == null || lines2 == null) {
      return 0;
    }

    int count = 0;

    for (String line : lines1) {
      String[] parts = line.split(",");

      if (parts.length < 4) {
        continue;
      }

      String u = parts[1];

      if (u.equals(username)) {
        count += Objects.requireNonNull(Post.getPost(parts[0])).getNumberOfLikes();
      }
    }

    for (String line : lines2) {
      String[] parts = line.split(",");

      if (parts.length < 5) {
        continue;
      }

      String u = parts[2];

      if (u.equals(username)) {
        count += Objects.requireNonNull(Comment.getComment(parts[0])).getNumberOfLikes();
      }
    }

    return count;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
