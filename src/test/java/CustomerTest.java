import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private static Money DEFAULT_MONEY = new Money(10000.0);
    Customer c1 = new Customer("Leo Andersson", "971010-1010", DEFAULT_MONEY);

    @Test
    void nameIsSetByConstructor() {
        assertEquals("Leo Andersson", c1.getName());
    }

    @Test
    void exceptionThrownWhenNameIsNull() {
        assertThrows(NullPointerException.class, () -> new Customer(null, "971010-1010", DEFAULT_MONEY));
    }

    @Test
    void exceptionThrownWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("", "971010-1010", DEFAULT_MONEY));
    }

    @Test
    void exceptionThrownWhenNameIsShorterThanFiveCharacters() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("Bo A", "971010-1010", DEFAULT_MONEY));
    }

    @Test
    void exceptionThrownWhenNameContainsPeriod() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("Leo .Andersson", "971010-1010", DEFAULT_MONEY));
    }

    @Test
    void exceptionThrownWhenDoesNotContainWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("LeoAndersson", "971010-1010", DEFAULT_MONEY));
    }

    @Test
    void exceptionThrownWhenNameContainsNumbers() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("L3o 4ndersson", "971010-1010", DEFAULT_MONEY));
    }

    @Test
    void ssnIsSetByConstructor() {
        assertEquals("971010-1010", c1.getSsn());
    }

    @Test
    void exceptionThrownWhenSSNIsNull() {
        assertThrows(NullPointerException.class, () -> new Customer("Leo Andersson", null, DEFAULT_MONEY));
    }

    @Test
    void exceptionThrownWhenSSNIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("Leo Andersson", "", DEFAULT_MONEY));
    }

    @Test
    void exceptionThrownWhenIncorrectDateIsEntered() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("Leo Andersson", "971032-1010", DEFAULT_MONEY));
    }

    @Test
    void exceptionThrownWhenSSNDoesNotStartWithSixNumbers() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("Leo Andersson", "9710-1010", DEFAULT_MONEY));
    }

    @Test
    void exceptionThrownWhenSSNDoesNotEndWithFourNumbers() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("Leo Andersson", "971010-10", DEFAULT_MONEY));
    }

    @Test
    void exceptionThrownWhenSSNDoesNotContainHyphen() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("Leo Andersson", "9710101010", DEFAULT_MONEY));
    }

    @Test
    void moneyIsSet() {
        assertEquals(10000.0, DEFAULT_MONEY.getAmount());
    }

    @Test
    void exceptionThrownWhenMoneyIsNull() {
        assertThrows(NullPointerException.class, () -> new Customer("Leo Andersson", "971010-1010", null));
    }

    @Test
    void toStringIsCorrect() {
        assertEquals("name: Leo Andersson, ssn: 971010-1010, money: 10000.0", c1.toString());
    }
}