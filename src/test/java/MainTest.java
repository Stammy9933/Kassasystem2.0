import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MainTest {
    String[] stringArr;
    static InputStream sysIn;
    InputStream iStream;
    FileRead fReader = new FileRead();

    @BeforeAll
    static void saveSysIn() {
        sysIn = System.in;

        //scanner = new Scanner(System.in);
        //inOut = new InputOutput(System.in);
    }

    @AfterEach
    void restoreSysIn() {
        System.setIn(sysIn);
    }

    // @AfterAll
    // void closeScanner() {
    //     scanner.close();
    // }

    InputStream createStream(String fileInput) {
        String input = "";
        try {
            input = fReader.readFile("testInput.txt", fileInput);
        } catch (IOException e) {
            e.getMessage();
        }
        InputStream in = new ByteArrayInputStream(input.getBytes());
        iStream = new BufferedInputStream(in);
        System.setIn(iStream);
        return iStream;
    }

    Main m;

    String[] input(String fileInput) {
        String input = "";
        try {
            input = fReader.readFile("testInput.txt", fileInput);
        } catch (IOException e) {
            e.getMessage();
        }

        if (input.contains(",")) {
            stringArr = input.split(",");
            return stringArr;
        } else {
            stringArr = new String[1];
            stringArr[0] = input;
            return stringArr;
        }
       /* InputStream in = new ByteArrayInputStream(input.getBytes());
        iStream = new BufferedInputStream(in);
        System.setIn(iStream);
        m = new Main(iStream); */
    }

    @Test
    void orderIsCorrectlyAdded() {
        m = new Main();
        m.addOrder();
        assertNotNull(m.getOrder());
    }

    @Test
    void membershipIsCorrectlyAdded() {
        m = new Main();
        m.addMembership();
        assertNotNull(m.getMembership());
    }

    @Test
    void commandLoopCaseOne() {
        String[] inputs = input("A");
        InputStream in = createStream(inputs[0]);
        m = new Main(in);

        m.createProducts();
        assertEquals("Coffee, " + 16.8 + "\n" +
                "Milk, " + 14.56 + "\n" +
                "Ham, " + 25.0 + "\n" +
                "Cheese, " + 37.5 + "\n" +
                "Butter, " + 50.0 + "\n" +
                "Loaf, " + 28.0 + "\n" +
                "Snus, " + 5.3 + "\n", m.showProducts());
    }

    @Test
    void commandLoopCaseTwo() {
        input("B");
        m.createProducts();
        m.addOrder();
        assertTrue(m.getOrder().getProducts().contains(m.getOrder().findProduct("Coffee")));
    }

    @Test
    void commandLoopCaseThree() {
        String[] inputs = input("C");
        InputStream in = createStream(inputs[0]);
        m = new Main(in);
        m.addMembership();
        m.addOrder();
        m.getOrder().getMembership().getPoints().addPoints(100000);
        m.buyDiscount();
        assertTrue(m.getOrder().discountIsUsed());
    }

    @Test
    void commandLoopCaseFour() {
        input("D");
        m.createProducts();
        m.addOrder();
        assertFalse(m.getOrder().getProducts().contains(m.getOrder().findProduct("Coffee")));
    }

    @Test
    void commandLoopCaseFive() {
        String[] inputs = input("E");
        InputStream inOne = createStream(inputs[0]);
        InputStream inTwo = createStream(inputs[1]);
        InputStream inThree = createStream(inputs[2]);
        m = new Main(inOne);
        m = new Main(inTwo);
        m = new Main(inThree);
        m.createProducts();
        m.addCustomer();
        m.addOrder();
        assertTrue(m.getOrder().isPaid());
    }

    @Test
    void commandLoopCaseSix() {
        String[] inputs = input("F");
        InputStream in = createStream(inputs[0]);
        m = new Main(in);
        m.createProducts();
        m.addMembership();
        m.addOrder();
        m.getOrder().getMembership().getPoints().addPoints(1000);
        assertTrue(m.getOrder().getMembership().getDiscount().getDiscount() > 0);
    }

    @Test
    void userIsAskedForMembershipAndSaysYes() {
        String[] inputs = input("G");
        InputStream in = createStream(inputs[0]);
        m = new Main(in);
        m.addOrder();
        m.askForMembership();
        assertNotNull(m.getMembership());
    }

    @Test
    void userIsAskedForMembershipAndSaysNo() {
        String[] inputs = input("H");
        InputStream in = createStream(inputs[0]);
        m = new Main(in);
        m.addOrder();
        m.askForMembershipNo();
        assertNull(m.getMembership());
    }
};