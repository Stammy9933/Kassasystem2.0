import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MembershipTest {

    @Test
    void discountSaved(){
        Membership m = new Membership(new Customer(new Money(1000)));
        m.getPoints().addPoints(100000);
        m.buyStaticDiscount();
        assertEquals(new StaticDiscount(100, m).getDiscount(), m.getDiscount().getDiscount());
    }

    @Test
    void discountRemoved(){
        Membership m = new Membership(new Customer(new Money(1000)));
        m.getPoints().addPoints(100000);
        m.buyStaticDiscount();
        m.removeDiscount();
        assertEquals(0, m.getDiscount().getDiscount());
    }

    @Test
    void secondDiscountAdded(){
        Membership m = new Membership(new Customer(new Money(1000)));
        m.getPoints().addPoints(100000);
        m.buyStaticDiscount();
        m.getPoints().addPoints(200000);
        assertThrows(IllegalStateException.class, m::buyStaticDiscount);
    }
}