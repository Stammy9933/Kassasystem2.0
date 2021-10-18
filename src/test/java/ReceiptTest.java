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
        assertEquals("1970-01-28 at 02:50:23 IST" + "\n" + "Milk, " + 5.3 + "\n" + "Total price: " + 5.3 + "kr", r.receiptToString());
    }
}