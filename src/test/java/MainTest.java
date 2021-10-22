import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MainTest {

    Main m = new Main() {
        @Override
        protected void commandLoop(String first, String second) {
            switch (first) {
                case "1":
                    m.showProducts();
                    break;
                case "2":
                    m.addProduct(second);
                    break;
                case "3":
                    m.addDiscount();
                    break;
                case "4":
                    m.removeProduct("Second");
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
        protected void addProduct(String testInput) {
            System.out.println("What is the name of the product?");
            testInput = testInput.toLowerCase();
            Product product = null;
            for (Product p : getProducts()) {
                if (p.getName().toLowerCase().equals(testInput)) {
                    product = p;
                }
            }
            Scan scan = new Scan(product, getOrder());
        }

        @Override
        protected void removeProduct(String testInput) {
            System.out.println("What is the name of the product?");
            testInput = testInput.toLowerCase();
            Product product = null;
            for (Product p : m.getProducts()) {
                if (p.getName().toLowerCase().equals(testInput)) {
                    product = p;
                }
            }
            if (product != null) {
                getOrder().removeProduct(product);
            } else {
                System.out.println("There is no product with that name.");
            }
        }

        @Override
        protected void askForMembership(String testInput) {
            System.out.println("Do you have a membership? Answer yes or no");
            if (testInput.equalsIgnoreCase("yes")) {
                addCustomer("Test Input", "101010-1010", 10000);
                addMembership();
            } else if (testInput.equalsIgnoreCase("no")) { //If there's not a membership, create a empty customer with only the amount of brought money
                Customer customer = new Customer(new Money(askForMoney(10000)));
                //setCustomer(customer);
            }
        }

        @Override
        protected void addCustomer(String testInputName, String testInputSSN, double testInputMoney) {
            System.out.println("What is your name?");
            System.out.println("What is your social security number?");
            System.out.println("How much money do you have?");
            Customer customer = new Customer(testInputName, testInputSSN, new Money(testInputMoney));
            setCustomer(customer);
        }

        @Override
        protected double askForMoney(double testInput) {
            System.out.println("How much money do you have?");
            return testInput;
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
        m.commandLoop("1", "");
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
        m.commandLoop("2", "Milk");
        assertTrue(m.getOrder().getProducts().contains(m.getOrder().findProduct("Milk")));
    }

    @Test
    void commandLoopCaseThree() {
        m.addMembership();
        m.addOrder();
        m.getOrder().getMembership().getPoints().addPoints(100000);
        m.buyDiscount();
        m.commandLoop("3", "");
        assertTrue(m.getOrder().discountIsUsed());
    }

    @Test
    void commandLoopCaseFour() {
        m.createProducts();
        m.addOrder();
        m.commandLoop("4", "Milk");
        assertFalse(m.getOrder().getProducts().contains(m.getOrder().findProduct("Milk")));
    }

    @Test
    void commandLoopCaseFive() {
        m.createProducts();
        m.addCustomer("Test Input", "971010-1010", 10000);
        m.addOrder();
        m.commandLoop("5", "");
        assertTrue(m.getOrder().isPaid());
    }

    @Test
    void commandLoopCaseSix() {
        m.createProducts();
        m.addMembership();
        m.addOrder();
        m.getOrder().getMembership().getPoints().addPoints(1000);
        m.commandLoop("6", "");
        assertTrue(m.getOrder().getMembership().getDiscount().getDiscount() > 0);
    }

    @Test
    void userIsAskedForMembershipAndSaysYes() {
        m.addOrder();
        m.askForMembership("Yes");
        assertNotNull(m.getMembership());
    }

    @Test
    void userIsAskedForMembershipAndSaysNo() {
        m.addOrder();
        m.askForMembership("No");
        assertNull(m.getMembership());
    }
}
