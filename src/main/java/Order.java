import java.util.ArrayList;

public class Order {
    private static final Membership NO_MEMBERSHIP = new Membership(new Customer("DEFAULT DEFAULT", "971010-1010", new Money(10000)));
    private ArrayList<Product> products = new ArrayList<>();
    private Membership membership;
    private Customer customer;
    private double totalPrice;
    private boolean isPaid;


    public Order(Membership membership) {
        this.membership = membership;
        customer = membership.getCustomer();
    }

    public Order(Customer customer) {
        this.customer = customer;
        membership = NO_MEMBERSHIP;
    }

    public ArrayList<Product> getProducts() {
        return new ArrayList<Product>(products);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void setTotalPrice() {
        for (Product p : products) {
            totalPrice += p.getPricePlusVat();
        }
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addStaticDiscount(StaticDiscount discount) {
        if (discount.getDiscount() > 0) {
            totalPrice -= discount.getDiscount();
            discount.getMembership().removeDiscount();
        }
    }

    public ArrayList<Product> getProductList() {
        return new ArrayList<Product>(products);
    }

    public void gotPaid() {
        isPaid = true;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public Membership getMembership() {
        return membership;
    }

    public Customer getCustomer(){
        return customer;
    }
}
