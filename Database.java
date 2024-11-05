package TemaTest;

import java.io.*;
import java.util.ArrayList;

public class Database {
  public static ArrayList<String> readFromDatabase(String file) {
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      ArrayList<String> lines = new ArrayList<>();
      String line;

      while ((line = br.readLine()) != null) {
        lines.add(line);
      }

      return lines;
    } catch (IOException ignored) {
      return null;
    }
  }

  public static void insertToDatabase(String file, String line) {
    try (FileWriter fw = new FileWriter(file, true);
         BufferedWriter bw = new BufferedWriter(fw);
         PrintWriter out = new PrintWriter(bw)) {
      out.println(line);
    } catch (IOException ignored) {
    }
  }

  public static void deleteFromDatabase(String file, String search) {
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      ArrayList<String> lines = new ArrayList<>();
      String line;

      while ((line = br.readLine()) != null) {
        if (!line.equals(search)) {
          lines.add(line);
        }
      }

      try (FileWriter fw = new FileWriter(file, false);
           BufferedWriter bw = new BufferedWriter(fw);
           PrintWriter out = new PrintWriter(bw)) {
        for (String line2 : lines) {
          out.println(line2);
        }
      } catch (IOException ignored) {
      }
    } catch (IOException ignored) {
    }
  }

  public static void cleanupDatabase(String file) {
    try (FileWriter fw = new FileWriter(file, false);
         BufferedWriter bw = new BufferedWriter(fw);
         PrintWriter out = new PrintWriter(bw)) {
      out.println("");
    } catch (IOException ignored) {
    }
  }
}
