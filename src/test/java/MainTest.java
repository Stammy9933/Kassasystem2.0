import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.*;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MainTest {
    final PrintStream standardOut = System.out;
    final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    String[] stringArr;
    static InputStream sysIn;
    static PrintStream stdOut;
    InputStream iStream;
    FileRead fReader = new FileRead();

    @BeforeAll
    static void saveSysIn() {
        sysIn = System.in;
        stdOut = System.out;
    }

    @AfterEach
    void restoreSysIn() {
        System.setIn(sysIn);
        System.setOut(stdOut);
    }

    InputStream createStream(String fileInput) {
        InputStream in = new ByteArrayInputStream(fileInput.getBytes());
        iStream = new BufferedInputStream(in);
        System.setIn(iStream);
        return iStream;
    }

    Main m;

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
        m = new Main();
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
        InputStream in = createStream("Coffee");
        m = new Main(in);
        m.addOrder();
        m.createProducts();
        m.addProduct();
        
        assertTrue(m.getOrder().getProducts().contains(m.getOrder().findProduct("Coffee")));
    }

    @Test
    void commandLoopCaseThree() {
        m = new Main();

        m.addMembership();
        m.addOrder();
        m.getOrder().getMembership().getPoints().addPoints(100000);
        m.addDiscount();
        assertTrue(m.getOrder().discountIsUsed());
    }

    @Test
    void commandLoopCaseFour() {
        InputStream in = createStream("Coffee");
        m = new Main(in);

        m.createProducts();
        m.addOrder();
        m.removeProduct();
        assertFalse(m.getOrder().getProducts().contains(m.getOrder().findProduct("Coffee")));
    }

    @Test
    void commandLoopCaseFive() {
        InputStream inOne = createStream("F");
        m = new Main(inOne);
        m.createProducts();
        Customer customer = new Customer("Jane doe", "940910-0940", new Money(10000.0));
        m.setCustomer(customer);
        m.addOrder();
        m.pay();
        assertTrue(m.getOrder().isPaid());
    }

    @Test
    void commandLoopCaseSix() {
        m = new Main();
        m.createProducts();
        m.addMembership();
        m.addOrder();
        m.getOrder().getMembership().getPoints().addPoints(1000);
        m.buyDiscount();
        assertTrue(m.getOrder().getMembership().getDiscount().getDiscount() > 0);
    }

    @Test
    void userIsAskedForMembershipAndSaysYes() {
        InputStream in = createStream("yes");
        m = new Main(in);
        m.addOrder();
        Customer customer = new Customer("Jane doe", "940910-0940", new Money(10000.0));
        m.setCustomer(customer);
        m.askForMembership();
        assertNotNull(m.getMembership());
    }

    @Test
    void userIsAskedForMembershipAndSaysNo() {
        InputStream in = createStream("1000.0");
        m = new Main(in);
        Customer customer = new Customer(new Money(m.askForMoney()));
        m.setCustomer(customer);
        assertEquals(1000.0, customer.getMoney().getAmount());
    }

    @Test
    void exitCommandLoop() {
        InputStream in = createStream("0");
        m = new Main(in);
        System.setOut(new PrintStream(outputStreamCaptor));

        m.commandLoop();

        assertEquals("Exiting program", outputStreamCaptor.toString().trim().substring(187));
    }
};