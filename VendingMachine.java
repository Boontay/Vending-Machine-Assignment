import java.util.ArrayList;

public class VendingMachine {
    private static ArrayList<Product> readProducts;
    private CoinSet inputtedCoins;
    private CoinSet storedCoins;
    private ArrayList<Operator> listOfOperators;

    public VendingMachine() {
        readProducts = TextFileReader.readFileToProductsArrayList();
        inputtedCoins = new CoinSet();
        storedCoins = TextFileReader.readCoinsFileToCoinSet();
        listOfOperators = TextFileReader.readFileToOperatorsArrayList();
    }

    public ArrayList<Product> getProductTypesArrayList() {
        ArrayList<Product> products = new ArrayList<Product>();
        int length = products.size();
        Product thisProduct;

        for (int i = 0; i < length; i++) {
            thisProduct = readProducts.get(i);

            if (!(products.contains(thisProduct))) {
                products.add(thisProduct);
            }
        }

        return products;
    }

    public Product[] getProductTypesArray() {
        ArrayList<Product> productTypesArrayList = getProductTypesArrayList();
        productTypesArrayList.trimToSize();

        Product[] productTypesArray = new Product[productTypesArrayList.size()];
        productTypesArray = productTypesArrayList.toArray(productTypesArray);

        return productTypesArray;
    }

    public String addCoin(Coin c) {
        inputtedCoins.addCoin(c);

        return String.format("Your balance is %.2f\n", inputtedCoins.getTotal());
    }

    public String addCoin(String name, double value) {
        Coin c = new Coin(value, name);
        return addCoin(c);
    }

    public String removeMoney() {
        return "Removed \u20ac" + storedCoins.removeCoins();
    }

    public CoinSet getStoredCoins() {
        return storedCoins;
    }

    public Coin[] getCoinTypes() {
        return TextFileReader.readCoinsFileToCoinTypesArray();
    }

    public String buyProduct(Product p) {
        String result = "";
        double cost = p.getPrice();
        double valueInputted = inputtedCoins.getTotal();

        if (cost > valueInputted) {
            result = "Insufficient funds.";
        } else if (cost <= valueInputted) {
            readProducts.remove(p);
            storedCoins.addCoin(inputtedCoins.getCoins());
            inputtedCoins.removeCoins();

            result = "Purchased " + p.getDescription() + ".";

            if (cost < valueInputted) {
                result += " \u20ac" + inputtedCoins.getChange(cost, valueInputted) + " change remaining.";
            }
        }

        writeToFiles();
        return result;
    }

    public String removeProduct(Product p, int quantity) {
        String result = "";

        if (readProducts.size() != 0) {
            int count = 0;
            int length = readProducts.size();

            for (int i = 0; i < length; i++) {
                if (p.equals(readProducts.get(i))) {
                    count++;
                }
            }

            for (int i = 0; i < quantity; i++) {
                readProducts.remove(p);
            }

            if (quantity >= count) {
                result = "All " + p.getDescription() + "s removed.";
            } else {
                result = quantity + " " + p.getDescription() + "(s) removed.";
            }
        }

        writeToFiles();
        return result;
    }

    public void addProduct(Product p, int quantity) {
        for (int i = 0; i < quantity; i++) {
            readProducts.add(p);
        }

        writeToFiles();
    }

    public void addProduct(String description, double price, int quantity) {
        Product p = new Product(description, price);

        addProduct(p, quantity);
    }

    public boolean isValidOperatorCode(String input) {
        boolean correct = false;
        listOfOperators = TextFileReader.readFileToOperatorsArrayList();
        int length = listOfOperators.size();

        for (int i = 0; i < length; i++) {
            if (input.equals(listOfOperators.get(i).getPassword())) {
                correct = true;
            }
        }

        if (correct) {
            return true;
        } else {
            return false;
        }
    }

    public void closeDown() {
        TextFileWriter.writeProductsToFile(readProducts);
        TextFileWriter.writeCoinsToFile(storedCoins.getCoins());
    }

    public void writeToFiles() {
        closeDown();
    }

    public String getStartMenuOptions() {
        return "1. Show Products\n2. Insert Money\n3. Buy Products";
    }

    public String getOperatorOptions() {
        return "1. Add/Remove Products\n2. Remove All Coins\n";
    }
}