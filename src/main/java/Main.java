import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private Order order;
    private Membership membership;
    private Customer customer;
    private final Scanner keyboardInput = new Scanner(System.in);
    private ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        Main program = new Main();
        program.run();
    }

    private void createProducts() {
        Vat12 vat12 = new Vat12();
        Vat6 vat6 = new Vat6();
        Vat25 vat25 = new Vat25();
        ProductGroup drinks = new ProductGroup("Drinks", vat12);
        Product coffee = new Product("Coffee", 15, drinks);
        products.add(coffee);
        Product milk = new Product("Milk", 13, drinks);
        products.add(milk);
        ProductGroup toppings = new ProductGroup("Toppings", vat25);
        Product ham = new Product("Ham", 20, toppings);
        products.add(ham);
        Product cheese = new Product("Cheese", 30, toppings);
        products.add(cheese);
        Product butter = new Product("Butter", 40, toppings);
        products.add(butter);
        ProductGroup bread = new ProductGroup("Bread", vat12);
        Product loaf = new Product("Loaf", 25, bread);
        products.add(loaf);
        ProductGroup tobacco = new ProductGroup("Tobacco", vat6);
        Product snus = new Product("Snus", 5, tobacco);
        products.add(snus);
    }

    private void showProducts() {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public void run() {
        createProducts();
        startUp();

    }

    private void startUp() {
        System.out.println("Welcome!");
        askForMembership();
        addOrder();
        commandLoop();
    }

    private void askForMembership() {
        System.out.println("Do you have a membership? Answer yes or no");
        String choice = keyboardInput.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            addCustomer();
            addMembership();
        } else if (choice.equalsIgnoreCase("no")) { //If there's not a membership, create a empty customer with only the amount of brought money
            customer = new Customer(new Money(askForMoney()));
        }
    }

    private void printOrder() {
        for (Product p : order.getProducts()) {
            System.out.println(p);
        }
    }

    private double askForMoney() {
        System.out.println("How much money do you have?");
        return keyboardInput.nextDouble();
    }

    private void printCommands() {
        System.out.println("The following commands exist. Please write the number of the command");
        System.out.println("1.Show products");
        System.out.println("2.Add product");
        System.out.println("3.Add discount");
        System.out.println("4.Remove product ");
        System.out.println("5.Pay");
        System.out.println("6.Buy discount for points");
        System.out.println("7.Show order");
    }

    private void addCustomer() {
        System.out.println("What is your name?");
        String name = keyboardInput.nextLine();
        System.out.println("What is your social security number?");
        String ssn = keyboardInput.nextLine();
        System.out.println("How much money do you have?");
        double money = keyboardInput.nextDouble();
        customer = new Customer(name, ssn, new Money(money));
    }

    private void addMembership() {
        membership = new Membership(customer);
    }

    private void addOrder() {
        if (membership != null) {
            order = new Order(membership);
        } else {
            order = new Order(customer);
        }
    }

    private void addProduct() {
        System.out.println("What is the name of the product?");
        String productName = keyboardInput.nextLine().toLowerCase();
        Product product = null;
        for (Product p : products) {
            if (p.getName().toLowerCase().equals(productName)) {
                product = p;
            }
        }
        Scan scan = new Scan(product, order);
    }

    private void addDiscount() {
        order.addStaticDiscount(membership.getDiscount());
    }

    private void removeProduct() {
        System.out.println("What is the name of the product?");
        String productName = keyboardInput.nextLine().toLowerCase();
        Product product = null;
        for (Product p : products) {
            if (p.getName().toLowerCase().equals(productName)) {
                product = p;
            }
        }
        if (product != null) {
            order.removeProduct(product);
        } else {
            System.out.println("There is no product with that name.");
        }
    }

    public void pay() {
        Checkout checkout = new Checkout(order);
        checkout.pay();
    }

    public void buyDiscount() {
        membership.buyStaticDiscount();
        System.out.println("Your points are converted to a discount");
    }

    protected String askForInput(String testInput, String programOutput){
        return keyboardInput.nextLine();
    }

    private void commandLoop() {
        String choice;
        do {
            System.out.println("Command?>");
            choice = keyboardInput.nextLine();
            switch (choice) {
                case "1":
                    showProducts();
                    break;
                case "2":
                    addProduct();
                    break;
                case "3":
                    addDiscount();
                    break;
                case "4":
                    removeProduct();
                    break;
                case "5":
                    pay();
                    break;
                case "6":
                    buyDiscount();
                    break;
                case "7":
                    printOrder();
                    break;
                default:
                    System.out.println("Unknown command.");
                    printCommands();
            }
        }
        while (!choice.equals("exit"));
    }
}