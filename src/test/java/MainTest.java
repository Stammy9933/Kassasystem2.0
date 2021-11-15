import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
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
    void correctlyGetsProducts() {
        m = new Main();
        m.createProducts();
        assertEquals(7, m.getProducts().size());
    }

    @Test
    void creatingProducts() {
        m = new Main();
        m.createProducts();
        Product p = new Product("Coffee", 15, new ProductGroup("Drinks", new Vat12()));
        assertEquals(p.toString(), m.getProducts().get(0).toString());
    } 

    @Test
    void orderIsCorrectlyPrinted() {
        m = new Main();
        m.createProducts();
        m.addOrder();
        System.setOut(new PrintStream(outputStreamCaptor));
        Product p = new Product("Ham", 20, new ProductGroup("Toppings", new Vat25()));
        m.getOrder().addProduct(p);
        m.printOrder();
        assertEquals("Ham, " + 25.0, outputStreamCaptor.toString().trim());
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
        InputStream in = createStream("no");
        m = new Main(in);
        Money money = new Money(1000.0);
        Customer customer = new Customer(money);
        m.setCustomer(customer);
        assertNull(m.getMembership());
    }

    @Test
    void askForMoneyTest() {
        InputStream in = createStream("1000.0");
        m = new Main(in);

        Customer customer = new Customer(new Money(m.askForMoney()));
        assertEquals(1000.0, customer.getMoney().getAmount());
    }

    @Test
    void exitCommandLoop() {
        InputStream in = createStream("0");
        m = new Main(in);
        System.setOut(new PrintStream(outputStreamCaptor));

        m.commandLoop();

        String[] arr = outputStreamCaptor.toString().trim().split("\n");

        assertEquals("Exiting program", arr[9]);
    }

    @Test
    void setCustomerSetsCorrectCustomer() {
        Money money = new Money(1000.0);
        Customer customer = new Customer(money);
        m = new Main();
        m.setCustomer(customer);
        m.addOrder();
        assertEquals(customer, m.getOrder().getCustomer());
    }

    @Test //"\r\n" ska byta ut "\n" för windows OS. För unix och linux funkar detta test annars som vanligt
    void menuIsCorrectlyPrinted() {
        m = new Main();
        System.setOut(new PrintStream(outputStreamCaptor));
        m.printCommands();
        assertEquals("The following commands exist. Please write the number of the command\n"
        + "1.Show products\n"
        + "2.Add product\n"
        + "3.Add discount\n"
        + "4.Remove product\n"
        + "5.Pay\n"
        + "6.Buy discount for points\n"
        + "7.Show order", outputStreamCaptor.toString().trim());
    }
};