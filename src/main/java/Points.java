public class Points {
    private int amount;

    public double getAmount() {
        return amount;
    }

    public void addPoints(double money) {
        amount += (int) money;
    }

    public void removePoints(double pointsToRemove) {
        if (amount < (int) pointsToRemove) {
            throw new IllegalArgumentException();
        }
        amount -= (int) pointsToRemove;
    }
}
