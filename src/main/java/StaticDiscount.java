public class StaticDiscount {
    private final int discount;
    private final Membership membership;

    public StaticDiscount(int discount, Membership membership) {
        this.membership = membership;
        checkDiscount(discount);
        this.discount = discount;
    }

    private void checkDiscount(int discount) {
        if (discount < 0)
            throw new IllegalArgumentException("Discount cannot be less than zero");
    }

    public double getDiscount() {
        return discount;
    }

    public Membership getMembership() {
        return membership;
    }

    @Override
    public String toString() {
        return "" + discount + " kr";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof StaticDiscount){
            StaticDiscount other = (StaticDiscount) obj;
            if(this.getDiscount() == other.getDiscount()){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
}