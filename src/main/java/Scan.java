public class Scan {
    private Order order;

    public Scan(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order missing");
        }
        this.order = order;
    }

    public void scanProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product missing");
        } else {
            order.addProduct(product);
        }
    }
}
