import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PercentageDiscountTest {
  @Test
  void discountBelowZero() {
      assertThrows(IllegalArgumentException.class, () -> {
          new PercentageDiscount(-0.3);
      });
  }

  @Test
  void discountAboveOne() {
    assertThrows(IllegalArgumentException.class, () -> {
      new PercentageDiscount(1.3);
    });
  }

  @Test
  void correctToString() {
    PercentageDiscount discount = new PercentageDiscount(0.4);
    assertEquals("40.0%", discount.toString());
  }
}
