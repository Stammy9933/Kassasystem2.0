import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MembershipTest {

    @Test
    void discountSaved(){
        Membership m = new Membership(new Customer(new Money(1000)));
        m.getPoints().addPoints(100);
        m.buyStaticDiscount();
        //m.getPoints().addPoints(50);
        assertEquals(new Discount())
    }
}