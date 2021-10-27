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


}