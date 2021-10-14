import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private ProductGroup DEFAULT_PRODUCT_GROUP = new ProductGroup("default", new Vat6());

    @Test
    void priceBelowZero(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Product("Mjölk", -3, DEFAULT_PRODUCT_GROUP);
        });
    }

    @Test
    void nameIsNull(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(null, 10, DEFAULT_PRODUCT_GROUP);
        });
    }

    @Test
    void nameIsEmpty(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Product("", 15, DEFAULT_PRODUCT_GROUP);
        });
    }

    @Test
    void correctToString(){
        Product p = new Product("Kaffe", 5, DEFAULT_PRODUCT_GROUP);
        assertEquals("Kaffe, 5.0", p.toString());
    }

    @Test
    void priceIsChanged(){
        Product p = new Product("Bönor", 6, DEFAULT_PRODUCT_GROUP);
        p.setPrice(7);
        assertEquals(7, p.getPrice());
    }

    @Test
    void correctPriceWithVat(){
        Product p = new Product("Salami", 15, DEFAULT_PRODUCT_GROUP);
        assertEquals(15.9, p.getPricePlusVat());
    }


}