public class Checkout {
    private Order order;
    private Customer customer;

    public Checkout(Order order, Customer customer){
        this.order = order;
        this.customer = customer;
        this.order.setTotalPrice();
    }

    public Checkout(Order order){
        this.order = order;
        this.customer = order.getMembership().getCustomer();
        this.order.setTotalPrice();
    }

    public void pay(){
        if(customer.getMoney().getAmount() < order.getTotalPrice()){
            throw new IllegalStateException("The customer don't have enough money");
        }
        customer.getMoney().subtractMoney(order.getTotalPrice());
        order.gotPaid();
        Receipt newReceipt = new Receipt(order);
        if(!order.getMembership().getCustomer().getName().equals("DEFAULT")){
            order.getMembership().getPoints().addPoints(order.getTotalPrice());
            order.getMembership().addReceipt(newReceipt);
        }
    }
}
