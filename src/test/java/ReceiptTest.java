import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {

    @Test
    void receiptIsCorrectlyPrintedOut() {
        Product p1 = new Product("Milk", 5.0, new ProductGroup("Dairy", new Vat6()));
        Product p2 = new Product("Loaf", 8.0, new ProductGroup("Bread", new Vat12()));
        Order o = new Order();
        o.addProduct(p1);
        o.addProduct(p2);
        Date date = new Date(2323223232L);
        Receipt r = new Receipt(o, date);
        Checkout checkout = new Checkout(o);
        checkout.pay();
        assertEquals("1970-01-27 at 22:20:23 CET" + "\n"
                    + "Milk, " + 5.3 + "\n"
                    + "Loaf, " + 8.96 + "\n" + "Total price: " + 14.26, r.receiptToString());
    }
}