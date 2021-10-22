import java.text.SimpleDateFormat;
import java.util.Date;

public class Receipt {

    private final Order order;
    private final Date date;


    public Receipt(Order order) {
        this.order = order;
        this.date = new Date(System.currentTimeMillis());
    }

    public Receipt(Order order, Date date) {
        this.order = order;
        this.date = date;
    }

    private String getDateFormat() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        return formatter.format(date);
    }

    public String receiptToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDateFormat()).append("\n");
        for (Product p : order.getProductList()) {
            sb.append(p.toString()).append("\n");
        }
        double roundedPrice =  Math.round(order.getTotalPrice()*100.0)/100.0;
        sb.append("Total price: ").append(roundedPrice);

        return sb.toString();
    }
}
