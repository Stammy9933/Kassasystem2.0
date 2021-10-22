import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScanTest {
  private static final Product DEFAULT_PRODUCT = new Product("name", 200, new ProductGroup("name", new Vat6()));
  private static final Order DEFAULT_ORDER = new Order(new Membership(new Customer("Name Name", "971010-6789", new Money(100.0, "SEK"))));
  @Test
  void successfulScan() {
    new Scan(DEFAULT_PRODUCT, DEFAULT_ORDER);
    int productIndex = DEFAULT_ORDER.getProducts().indexOf(DEFAULT_PRODUCT);
    assertEquals(DEFAULT_PRODUCT, DEFAULT_ORDER.getProducts().get(productIndex));
  }

  @Test
  void noProductScan() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Scan(null, DEFAULT_ORDER);
    });
  }

  @Test
  void noOrderScan() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Scan(DEFAULT_PRODUCT, null);
    });
  }
}
