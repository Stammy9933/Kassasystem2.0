import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MainTest {

    Main m = new Main() {
        @Override
        protected void commandLoop(String first, String second) {
            //do {
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
        //  while (!first.equals("exit"));
        //}

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
    };

    @Test
    void commandLoopCaseOne() {
        m.createProducts();
        m.commandLoop("1", "");
        assertEquals(m.showProducts(), "Coffee, " + 16.8 + "\n" +
                "Milk, " + 14.56 + "\n" +
                "Ham, " + 25.0 + "\n" +
                "Cheese, " + 37.5 + "\n" +
                "Butter, " + 50.0 + "\n" +
                "Loaf, " + 28.0 + "\n" +
                "Snus, " + 5.3 + "\n");
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
        m.addOrder();
        m.addMembership();
        m.getOrder().getMembership().getPoints().addPoints(1000000);
        m.buyDiscount();
        m.commandLoop("3", "");
        assertTrue(m.getOrder().discountIsUsed()); //Går aldrig in i ifstatementet i addstaticdiscount i order
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
        m.addOrder();
        m.commandLoop("5", "");
        assertTrue(m.getOrder().isPaid());
    }

    @Test
    void commandLoopCaseSix() {
        m.createProducts();
        m.addOrder();
        m.addMembership();
        m.getOrder().getMembership().getPoints().addPoints(200);
        m.commandLoop("6", "");
        assertNull(m.getOrder().getMembership().getDiscount());
    }

    @Test
    void commandLoopCaseSeven() { // Hur testa void metod?
        m.addOrder();
        m.createProducts();
        m.printOrder();
    }
}
