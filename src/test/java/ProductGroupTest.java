import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductGroupTest {
    @Test
    void addsProduct(){
        ProductGroup pg = new ProductGroup("Bönor", new Vat6());
        Product p = new Product("Bönor", 5, pg);
        pg.addProduct(p);
        assertTrue(pg.getProducts().contains(p));
    }

    @Test
    void removesProduct(){
        ProductGroup pg = new ProductGroup("Bönor", new Vat6());
        Product p = new Product("Bönor", 5, pg);
        pg.addProduct(p);
        pg.removeProduct(p);
        assertFalse(pg.getProducts().contains(p));
    }
}