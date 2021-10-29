import java.util.Scanner;

public class InputOutput {
  Scanner scanner;

  public InputOutput(Scanner scanner) {
    this.scanner = scanner;
  }

  public String getInput() {
    scanner = new Scanner(System.in);
      return scanner.nextLine();
  }

  public void closeScanner() {
    scanner.close();
  }
}