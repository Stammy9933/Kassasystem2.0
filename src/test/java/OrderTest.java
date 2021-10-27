import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class OrderTest {

    @Test
    void getOrderWithProducts() {
        Customer customer = new Customer(new Money(20));
        Order o = new Order(customer);
        Product product = new Product("Mjölk", 4, new ProductGroup("Dryck", new Vat6()));
        o.addProduct(product);
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(product);
        assertEquals(products, o.getProducts());
    }

    @Test
    void orderWithoutMembershipFailed(){
        Customer customer = new Customer(new Money(1));
        Order o = new Order(customer);
        o.addProduct(new Product("Kaffe", 2, new ProductGroup("Dryck", new Vat6())));
        Checkout checkout = new Checkout(o);
        assertThrows(IllegalStateException.class, checkout::pay);
    }

    @Test
    void orderWithMembershipDiscountSucceeded(){
        Membership ms = new Membership(new Customer("Name Name", "971010-6789", new Money(20)));
        Order o = new Order(ms);
        o.addProduct(new Product("Kaffe", 30, new ProductGroup("Dryck", new Vat6())));
        o.addStaticDiscount(new StaticDiscount(15, ms));
        Checkout checkout = new Checkout(o);
        checkout.pay();
        assertTrue(o.isPaid());
    }

    @Test
    void findProductFromOrder() {
        Membership ms = new Membership(new Customer("Name LastName", "990811-3238", new Money(200)));
        Order o = new Order(ms);
        Product product = new Product("Lammbiff", 40, new ProductGroup("Kött", new Vat6()));
        o.addProduct(product);
        assertEquals(product, o.findProduct(product.getName()));
    }

    @Test
    void getCustomerFromOrder() {
        Customer customer = new Customer("Name LastName", "920301-3828", new Money(100));
        Membership ms = new Membership(customer);
        Order o = new Order(ms);
        assertEquals(customer, o.getCustomer());
    }

    @Test
    void removeProductFromOrder() {
        Membership ms = new Membership(new Customer("Name LastName", "990811-3238", new Money(200)));
        Order o = new Order(ms);
        Product product = new Product("Lammbiff", 40, new ProductGroup("Kött", new Vat6()));
        o.addProduct(product);
        o.removeProduct(product);
        assertEquals(0, o.getProducts().size());
    }
}