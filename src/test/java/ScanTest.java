import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScanTest {
  private static final Product DEFAULT_PRODUCT = new Product("name", 200, new ProductGroup("name", new Vat6()));
  private static final Order DEFAULT_ORDER = new Order(new Membership(new Customer("Name Name", "971010-6789", new Money(100.0, "SEK"))));
  @Test
  void successfulScan() {
    Scan scan = new Scan(DEFAULT_ORDER);
    scan.scanProduct(DEFAULT_PRODUCT);
    int productIndex = DEFAULT_ORDER.getProducts().indexOf(DEFAULT_PRODUCT);
    assertEquals(DEFAULT_PRODUCT, DEFAULT_ORDER.getProducts().get(productIndex));
  }

  @Test
  void noProductScan() {
    Scan scan = new Scan(DEFAULT_ORDER);
    assertThrows(IllegalArgumentException.class, () -> {
      scan.scanProduct(null);
    });
  }

  @Test
  void noOrderScan() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Scan(null);
    });
  }
}
