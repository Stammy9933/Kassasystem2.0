import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VatCategoriesTest {

    @Test
    void vat6IsCorrectlyImplemented() {
        Vat test = new Vat6();
        assertEquals(0.06, test.getVat());
    }

    @Test
    void vat12IsCorrectlyImplemented() {
        Vat test = new Vat12();
        assertEquals(0.12, test.getVat());
    }

    @Test
    void vat25IsCorrectlyImplemented() {
        Vat test = new Vat25();
        assertEquals(0.25, test.getVat());
    }






}