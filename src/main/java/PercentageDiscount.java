public class PercentageDiscount {
    private double discount;

    public PercentageDiscount(double discount) {
        checkDiscount(discount);
        this.discount = discount;
    }

    private void checkDiscount(double discount) {
        if (discount < 0.0 || discount >= 1)
            throw new IllegalArgumentException("Discount invalid");
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "" + discount * 100 + "%";
    }
}