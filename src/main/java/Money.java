public class Money {

    private double amount;
    private final String currency;

    public Money(double amount) {
        this.amount = amount;
        this.currency = "Svenska kronor";
    }

    public Money(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setMoney(double newAmount) {
        this.amount = newAmount;
    }

    public void addMoney(double amountToAdd) {
        this.amount += amountToAdd;
    }

    public void subtractMoney(double amountToSubtract) {
        this.amount -= amountToSubtract;
    }

    public String toString() {
        return "" + this.amount + " " + this.currency;
    }
}
