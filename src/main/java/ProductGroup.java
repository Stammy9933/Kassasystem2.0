import java.util.ArrayList;

public class ProductGroup {
    private String name;
    private ArrayList<Product> products = new ArrayList<>();
    private Vat vat;

    public ProductGroup(String name, Vat vat) {
        this.name = name;
        this.vat = vat;
    }

    public void addProduct(Product newProduct) {
        products.add(newProduct);
    }

    public void removeProduct(Product p) {
        products.remove(p);
    }

    public Vat getVat() {
        return vat;
    }

    public ArrayList<Product> getProducts() {
        return new ArrayList<Product>(products);
    }
}
