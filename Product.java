public class Product {
    private String description;
    private double price;

    public Product(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public String toString() {
        return description + "\t" + price;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
