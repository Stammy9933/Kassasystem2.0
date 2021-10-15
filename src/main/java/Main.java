import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private Order order;
    private Membership membership;
    private Customer customer;
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private Scanner keyboardInput = new Scanner(System.in);
    private ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args){
        Main program = new Main();
        program.run();
    }

    private void createProducts(){
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

    private void showProducts(){
        for(Product p : products){
            System.out.println(p);
        }
    }


    public void run(){
        createProducts();
        startUp();

    }

    private void startUp(){
        System.out.println("Welcome!");
        addCustomer();
        addMembership();
        addOrder();
        printCommands();
        commandLoop();
    }

    private void printCommands(){
        System.out.println("The following commands exist");
        System.out.println("1.Show products");
        System.out.println("2.Add product");
        System.out.println("3.Add discount");
        System.out.println("4.Remove product ");
        System.out.println("5.Pay");
        System.out.println("");
    }

    private void addCustomer(){
        System.out.println("What is your name?");
        String name = keyboardInput.nextLine();
        System.out.println("What is your social security number?");
        String ssn = keyboardInput.nextLine();
        System.out.println("How much money do you have?");
        double money = keyboardInput.nextDouble();
        customer = new Customer(name, ssn, new Money(money));
    }

    private void addMembership(){
        System.out.println("Do you want to be a member? Answer yes or no");
        String choice = keyboardInput.nextLine();
        if(choice.equalsIgnoreCase("yes")){
            membership = new Membership(customer);
        }
    }

    private void addOrder(){
        if(membership != null){
            order = new Order(membership);
        }
        else{
            order = new Order();
        }
    }

    private void commandLoop(){
        String choice;
        do{
            System.out.println("Command?>");
            choice = keyboardInput.nextLine();
            switch(choice){
                case "1":
                    showProducts();
                    break;
                case "2":
                    //
                    break;
                case "3":
                    //
                    break;
                case "4":
                    //
                    break;
                case "5":
                    //
                    break;
                case "6":
                    //
                    break;
                case "7":
                    //
                    break;
                default:
                    System.out.println("Unknown command.");
                    printCommands();
            }
        }
        while(!choice.equals("exit"));
    }

}