import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {

    @Test
    void correctAmountIsSet() {
        Money m1 = new Money(45.0);
        assertEquals(45.0, m1.getAmount());
    }

    @Test
    void correctDefaultCurrency() {
        Money m1 = new Money(50);
        assertEquals("Svenska kronor", m1.getCurrency());
    }

    @Test
    void correctChangedCurrency() {
        Money m1 = new Money(10, "USD");
        assertEquals("USD", m1.getCurrency());
    }

    @Test
    void changedAmountIsCorrect() {
        Money m1 = new Money(10);
        m1.setMoney(2);
        assertEquals(2, m1.getAmount());
    }

    @Test
    void correctAmountIsAdded() {
        Money m1 = new Money(50);
        m1.addMoney(10);
        assertEquals(60, m1.getAmount());
    }

    @Test
    void correctAmountIsSubtracted() {
        Money m1 = new Money(50);
        m1.subtractMoney(10);
        assertEquals(40, m1.getAmount());
    }

    @Test
    void toStringIsCorrect() {
        Money m1 = new Money(30);
            assertEquals("" + 30.0 + " " + m1.getCurrency(), m1.toString());
    }
}
