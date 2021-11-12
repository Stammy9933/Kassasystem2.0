import java.io.*;
import java.util.Scanner;

public class FileRead {
    String line;

    public String readFile(String fileName, String lineValue) throws IOException {
        try {
            FileReader file = new FileReader(fileName);
            BufferedReader in = new BufferedReader(file);
            while ((line = in.readLine()) != null) {
                if (line.equals(lineValue)) {
                    line = in.readLine();
                    break;
                }
            }
            return line;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    // public static void main(String[] args) throws IOException {
    //     TestingReader test = new TestingReader();
    //     //test.tryingToReadFromFile("testInput.txt");
    //     test.readFile("testInput.txt", "E");
    //     System.out.println(readFile("testInput.txt", "E"));

    // }
}