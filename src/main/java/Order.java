import java.util.ArrayList;

public class Order {
    private static final Membership NO_MEMBERSHIP = new Membership(new Customer("DEFAULT DEFAULT", "971010-1010", new Money(0)));
    private ArrayList<Product> products = new ArrayList<>();
    private Membership membership;
    private double totalPrice;
    private boolean discountUsed;
    private boolean isPaid;
    private Customer customer;



    public Order(Membership membership) {
        this.membership = membership;
        this.customer = membership.getCustomer();
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

    public void calculateTotalPrice() {
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
            totalPrice -= discount.getDiscount();
            discount.getMembership().removeDiscount();
            discountUsed = true;
    }

    public ArrayList<Product> getProductList() {
        return new ArrayList<Product>(products);
    }

    public void setAsPaid() {
        isPaid = true;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public Customer getCustomer(){
        return customer;
    }

    public Membership getMembership() {
        return membership;
    }

    public boolean discountIsUsed() {
        return discountUsed;
    }

    public Product findProduct(String p) {
        for(Product product : products) {
            if(product.getName().equals(p)) {
                return product;
            }
        }
        return null;
    }

}
