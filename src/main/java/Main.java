import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private Order order;
    private Membership membership;
    private Customer customer;
    private Scanner keyboardInput;
    private ArrayList<Product> products = new ArrayList<>();

    public Main() {
        this.keyboardInput = new Scanner(System.in);
    }

    public Main(InputStream in) {
        this.keyboardInput = new Scanner(in);
    }

    public static void main(String[] args) {
        Main program = new Main();
        program.run();
    }

    protected void createProducts() {
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

    protected String showProducts() {
        StringBuilder sb = new StringBuilder();
        for (Product p : products) {
            sb.append(p.toString()).append("\n");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    protected void run() {
        createProducts();
        startUp();
    }

    protected void startUp() {
        System.out.println("Welcome!");
        askForMembership();
        addOrder();
        commandLoop();
    }

    protected void askForMembership() {
        System.out.println("Do you have a membership? Answer yes or no");
        String choice = keyboardInput.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            addCustomer();
            addMembership();
        } else if (choice.equalsIgnoreCase("no")) { //If there's not a membership, create a empty customer with only the amount of brought money
            customer = new Customer(new Money(askForMoney()));
        } else {
            System.out.println("Not a valid answer");
        }
    }

    protected void printOrder() {
        for (Product p : order.getProducts()) {
            System.out.println(p);
        }
    }

    protected ArrayList<Product> getProducts() {
        return new ArrayList<Product>(products);
    }

    protected Order getOrder() {
        return order;
    }

    protected Membership getMembership() {
        return membership;
    }

    protected double askForMoney() {
        System.out.println("How much money do you have?");
        return keyboardInput.nextDouble();
    }

    protected void printCommands() {
        System.out.println("The following commands exist. Please write the number of the command");
        System.out.println("1.Show products");
        System.out.println("2.Add product");
        System.out.println("3.Add discount");
        System.out.println("4.Remove product ");
        System.out.println("5.Pay");
        System.out.println("6.Buy discount for points");
        System.out.println("7.Show order");
    }

    protected void addCustomer() {
        System.out.println("What is your name?");
        String name = keyboardInput.nextLine();
        System.out.println("What is your social security number?");
        String ssn = keyboardInput.nextLine();
        System.out.println("How much money do you have?");
        double money = keyboardInput.nextDouble();
        customer = new Customer(name, ssn, new Money(money));
    }

    protected void addMembership() {
        membership = new Membership(customer);
    }

    protected void addOrder() {
        if (membership != null) {
            order = new Order(membership);
        } else {
            order = new Order(customer);
        }
    }

    protected void addProduct() {
        System.out.println("What is the name of the product?");
        String productName = keyboardInput.nextLine().toLowerCase();
        Product product = null;
        for (Product p : products) {
            if (p.getName().toLowerCase().equals(productName)) {
                product = p;
            }
        }
        Scan scan = new Scan(order);
        scan.scanProduct(product);
        System.out.println("Product has been added");
    }

    protected void addDiscount() {
        order.addStaticDiscount(membership.getDiscount());
    }

    protected void askForMembershipNo() {
        System.out.println("Do you want a membership?");
    }

    protected void removeProduct() {
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

    protected void pay() {
        Checkout checkout = new Checkout(order);
        checkout.pay();
    }

    protected void buyDiscount() {
        membership.buyStaticDiscount();
        System.out.println("Your points are converted to a discount");
    }

    protected void setCustomer(Customer newCustomer) {
        customer = newCustomer;
    }

    protected void commandLoop() {
        printCommands();
        boolean running = true;
        while (running) {
            System.out.println("Command?>");
            String choice = keyboardInput.next();
            keyboardInput.nextLine();
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
                case "0":
                    System.out.println("Exiting program");
                    running = false;
                    break;
                default:
                    System.out.println("Unknown command.");
                    printCommands();
                    break;
            }
        }
        keyboardInput.close();
    }
}
