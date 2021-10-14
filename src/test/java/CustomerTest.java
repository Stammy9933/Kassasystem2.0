import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private static Money DEFAULT_MONEY = new Money(10000);

    @Test
    void nameIsSetByConstructor(){
        Customer c1 = new Customer("Leo Andersson", "9710184450", DEFAULT_MONEY);
        assertEquals("Leo Andersson", c1.getName());
    }

    @Test
    void exceptionThrownWhenNameIsNull(){
        assertThrows(IllegalArgumentException.class, () -> new Customer(null, "9710184450", DEFAULT_MONEY));
    }

    @Test
    void exceptionThrownWhenNameIsEmpty(){
        assertThrows(IllegalArgumentException.class, () -> new Customer("", "9710184450", DEFAULT_MONEY));
    }

    @Test
    void ssnIsSetByConstructor(){
        Customer c1 = new Customer("Leo Andersson", "9710184450", DEFAULT_MONEY);
        assertEquals("9710184450", c1.getSsn());
    }
    @Test
    void exceptionThrownWhenSsnIsTooLong(){
        assertThrows(IllegalArgumentException.class, () -> new Customer("Leo Andersson", "97101844505", DEFAULT_MONEY));
    }

    @Test
    void exceptionThrownWhenSsnIsTooShort(){
        assertThrows(IllegalArgumentException.class, () -> new Customer("Leo Andersson", "971018445", DEFAULT_MONEY));
    }

    @Test
    void addressIsSet(){
        Customer c1 = new Customer("Leo Andersson", "9710184450", DEFAULT_MONEY);
        c1.setAddress("Ekgatan 10");
        assertEquals("Ekgatan 10", c1.getAddress());
    }

    @Test
    void phoneNumberIsSet(){
        Customer c1 = new Customer("Leo Andersson", "9710184450", DEFAULT_MONEY);
        c1.setPhoneNumber("0704479933");
        assertEquals("0704479933", c1.getPhoneNumber());
    }

    @Test
    void emailAddressIsSet(){
        Customer c1 = new Customer("Leo Andersson", "9710184450", DEFAULT_MONEY);
        c1.setEmailAddress("Leo.And@gmail.com");
        assertEquals("Leo.And@gmail.com", c1.getEmailAddress());
    }

    @Test
    void toStringIsCorrect(){
        Customer c1 = new Customer("Leo Andersson", "9710184450", DEFAULT_MONEY);
        c1.setAddress("Ekgatan 10");
        c1.setPhoneNumber("0704479933");
        c1.setEmailAddress("Leo.And@gmail.com");
        assertEquals("name: Leo Andersson, ssn: 9710184450, address: Ekgatan 10, phone number: 0704479933, email address: Leo.And@gmail.com", c1.toString());
    }
}