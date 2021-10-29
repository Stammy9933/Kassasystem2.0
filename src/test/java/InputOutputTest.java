import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class InputOutputTest {
  @Test
  void takesInput() {
      InputOutput inOut = new InputOutput(new Scanner(System.in));
      String input = "test";
      InputStream in = new ByteArrayInputStream(input.getBytes());
      System.setIn(in);

      assertEquals("test", inOut.getInput());
  }

  @Test
  void scannerCloses() {
    Scanner scanner = new Scanner(System.in);
    InputOutput inOut = new InputOutput(scanner);
    String input = "test";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    inOut.closeScanner();

    assertThrows(IllegalStateException.class, () -> {
      scanner.hasNextLine();
    });
  }
}
