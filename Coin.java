public class Coin {
    private double value;
    private String name;

    public Coin(double value, String name) {
        this.value = value;
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + "\t\u20ac" + value;
    }
}
