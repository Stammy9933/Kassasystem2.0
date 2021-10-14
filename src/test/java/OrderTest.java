import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @Test
    void orderWithoutMembershipFailed(){
        Order o = new Order();
        o.addProduct(new Product("Kaffe", 2, new ProductGroup("Dryck", new Vat6())));
        Checkout checkout = new Checkout(o, new Customer("Robert", "8704121610", new Money(1)));
        assertThrows(IllegalStateException.class, checkout::pay);
    }

    @Test
    void orderWithMembershipDiscountSucceeded(){
        Membership ms = new Membership(new Customer("Ola", "0108153344", new Money(20)));
        Order o = new Order(ms);
        o.addProduct(new Product("Kaffe", 30, new ProductGroup("Dryck", new Vat6())));
        o.addStaticDiscount(new StaticDiscount(15, ms));
        Checkout checkout = new Checkout(o, ms.getCustomer());
        checkout.pay();
        assertTrue(o.isPaid());
    }


}