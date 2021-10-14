import java.util.ArrayList;

public class Receipt {

    private final Order order;

    public Receipt(Order order) {
        this.order = order;
    }

    public String writeProductList() {
        StringBuilder sb = new StringBuilder();
        for (Product p : order.getProductList()) {
            sb.append(p.toString()).append("\n");
        }
        sb.append("Total price: ").append(order.getTotalPrice());
        return sb.toString();
    }
}
