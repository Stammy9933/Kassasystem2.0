public class Scan {
    private Product product;
    private Order order;

    public Scan(Product product, Order order) {
        if (product == null) {
            throw new IllegalArgumentException("Product missing");
        }
        if (order == null) {
            throw new IllegalArgumentException("Order missing");
        }
        order.addProduct(product);
    }
}
