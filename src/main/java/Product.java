public class Product {
    private String name;
    private double price; //eventuellt ändra till objekt av Money
    private final ProductGroup productGroup;

    public Product(String name, double price, ProductGroup productGroup){
        if(name == null || name.equals("")){
            throw new IllegalArgumentException("Invalid product name");
        }
        this.name = name;
        checkPrice(price);
        this.price = price;
        this.productGroup = productGroup;
    }

    private void checkPrice(double price){
        if(price < 0){
            throw new IllegalArgumentException("Price can't be below zero");
        }
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public double getPricePlusVat(){
        return price + price * productGroup.getVat().getVat();
    }

    public void setPrice(double newPrice){
        checkPrice(newPrice);
        price = newPrice;
    }

    @Override
    public String toString() {
        return name + ", " + getPricePlusVat();
    }
}
