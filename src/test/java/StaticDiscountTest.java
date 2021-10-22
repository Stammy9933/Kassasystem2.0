import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StaticDiscountTest {
  private static final Membership DEFAULT_MEMBERSHIP = new Membership(new Customer("Name Name", "971010-6789", new Money(10000)));

  @Test
  void discountBelowZero(){
      assertThrows(IllegalArgumentException.class, () -> {
          new StaticDiscount(-3, DEFAULT_MEMBERSHIP);
      });
  }

  @Test
  void correctToString() {
    StaticDiscount discount = new StaticDiscount(120, DEFAULT_MEMBERSHIP);
    assertEquals("120 kr", discount.toString());
  }

  @Test
  void getCorrectDiscount() {
    StaticDiscount discount = new StaticDiscount(150, DEFAULT_MEMBERSHIP);
    assertEquals(150, discount.getDiscount());
  }
}
