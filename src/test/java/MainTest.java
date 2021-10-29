import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
class MainTest {

    Main m = new Main() {
        Scanner scanner = new Scanner(System.in);

        @Override
        protected void commandLoop(String testInput) {
            InputOutput inOut = new InputOutput(scanner);
            InputStream in = new ByteArrayInputStream(testInput.getBytes());
            System.setIn(in);
            String input = inOut.getInput();
            inOut.closeScanner();
      
            switch (input) {
                case "1":
                    m.showProducts();
                    break;
                case "2":
                    m.addProduct();
                    break;
                case "3":
                    m.addDiscount();
                    break;
                case "4":
                    m.removeProduct();
                    break;
                case "5":
                    pay();
                    break;
                case "6":
                    buyDiscount();
                    break;
                case "7":
                    m.printOrder();
                    break;
                default:
                    System.out.println("Unknown command.");
                    m.printCommands();
            }
        }

        @Override
        protected void addProduct() {
            System.out.println("What is the name of the product?");

            String productName = "Coffee";
            InputOutput inOut = new InputOutput(scanner);
            InputStream in = new ByteArrayInputStream(productName.getBytes());
            System.setIn(in);

            String input = inOut.getInput();
            inOut.closeScanner();

            Product product = null;
            for (Product p : getProducts()) {
                if (p.getName().toLowerCase().equals(input.toLowerCase())) {
                    product = p;
                    break;
                }
            }
            Scan scan = new Scan(getOrder());
            scan.scanProduct(product);
        }

        @Override
        protected void removeProduct() {
            System.out.println("What is the name of the product?");

            String productName = "Coffee";
            InputOutput inOut = new InputOutput(scanner);
            InputStream in = new ByteArrayInputStream(productName.getBytes());
            System.setIn(in);

            String input = inOut.getInput();
            inOut.closeScanner();

            Product product = null;
            for (Product p : m.getProducts()) {
                if (p.getName().toLowerCase().equals(input.toLowerCase())) {
                    product = p;
                    break;
                }
            }
            if (product != null) {
                getOrder().removeProduct(product);
            } else {
                System.out.println("There is no product with that name.");
            }
        }

        @Override
        protected void askForMembership() {
            System.out.println("Do you have a membership? Answer yes or no");

            String answer = "yes";
            InputOutput inOut = new InputOutput(scanner);
            InputStream in = new ByteArrayInputStream(answer.getBytes());
            System.setIn(in);

            String input = inOut.getInput();
            inOut.closeScanner();

            if (input.equalsIgnoreCase("yes")) {
                addCustomer();
                addMembership();
            } else if (input.equalsIgnoreCase("no")) { //If there's not a membership, create a empty customer with only the amount of brought money
                new Customer(new Money(askForMoney()));
            }
        }

        @Override
        protected void askForMembershipNo() {
            System.out.println("Do you have a membership? Answer yes or no");

            String answer = "no";
            InputOutput inOut = new InputOutput(scanner);
            InputStream in = new ByteArrayInputStream(answer.getBytes());
            System.setIn(in);

            String input = inOut.getInput();
            inOut.closeScanner();

            if (input.equalsIgnoreCase("yes")) {
                addCustomer();
                addMembership();
            } 

            if (input.equalsIgnoreCase("no")) { //If there's not a membership, create a empty customer with only the amount of brought money
                new Customer(new Money(askForMoney()));
            }
        }

        @Override
        protected void addCustomer() {
            String name = "Jane Doe";
            String ssn = "101010-1010";
            String money = "10000";

            System.out.println("What is your name?");
            InputOutput inOut = new InputOutput(scanner);
            InputStream inName = new ByteArrayInputStream(name.getBytes());
            System.setIn(inName);
            String nameInput = inOut.getInput();

            System.out.println("What is your social security number?");
            InputStream inSSN = new ByteArrayInputStream(ssn.getBytes());
            System.setIn(inSSN);
            String ssnInput = inOut.getInput();

            System.out.println("How much money do you have?");
            InputStream inMoney = new ByteArrayInputStream(money.getBytes());
            System.setIn(inMoney);
            String moneyInput = inOut.getInput();

            Customer customer = new Customer(nameInput, ssnInput, new Money(Integer.parseInt(moneyInput)));
            setCustomer(customer);
        }

        @Override
        protected double askForMoney() {
            String money = "100";
            System.out.println("How much money do you have?");

            InputOutput inOut = new InputOutput(scanner);
            InputStream in = new ByteArrayInputStream(money.getBytes());
            System.setIn(in);
            
            String input = inOut.getInput();

            inOut.closeScanner();

            return Double.parseDouble(input);
        }
    };

    @Test
    void orderIsCorrectlyAdded() {
        m.addOrder();
        assertNotNull(m.getOrder());
    }

    @Test
    void membershipIsCorrectlyAdded() {
        m.addMembership();
        assertNotNull(m.getMembership());
    }

    @Test
    void commandLoopCaseOne() {
        m.createProducts();
        m.commandLoop("1");
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
        m.createProducts();
        m.addOrder();
        m.commandLoop("2");
        assertTrue(m.getOrder().getProducts().contains(m.getOrder().findProduct("Coffee")));
    }

    @Test
    void commandLoopCaseThree() {
        m.addMembership();
        m.addOrder();
        m.getOrder().getMembership().getPoints().addPoints(100000);
        m.buyDiscount();
        m.commandLoop("3");
        assertTrue(m.getOrder().discountIsUsed());
    }

    @Test
    void commandLoopCaseFour() {
        m.createProducts();
        m.addOrder();
        m.commandLoop("4");
        assertFalse(m.getOrder().getProducts().contains(m.getOrder().findProduct("Coffee")));
    }

    @Test
    void commandLoopCaseFive() {
        m.createProducts();
        m.addCustomer();
        m.addOrder();
        m.commandLoop("5");
        assertTrue(m.getOrder().isPaid());
    }

    @Test
    void commandLoopCaseSix() {
        m.createProducts();
        m.addMembership();
        m.addOrder();
        m.getOrder().getMembership().getPoints().addPoints(1000);
        m.commandLoop("6");
        assertTrue(m.getOrder().getMembership().getDiscount().getDiscount() > 0);
    }

    @Test
    void userIsAskedForMembershipAndSaysYes() {
        m.addOrder();
        m.askForMembership();
        assertNotNull(m.getMembership());
    }

    @Test
    void userIsAskedForMembershipAndSaysNo() {
        m.addOrder();
        m.askForMembershipNo();
        assertNull(m.getMembership());
    }
}