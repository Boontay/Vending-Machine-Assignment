import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TextFileWriter {
    private static FileWriter fileWriter;
    private static PrintWriter out;

    private static int duplicateProductsAmount(ArrayList<Product> products, Product p) {
        int amount = 0;
        int length = products.size();

        for (int i = 0; i < length; i++) {
            if (products.get(i).equals(p)) {
                amount++;
            }
        }

        return amount;
    }

    private static int duplicateCoinAmount(ArrayList<Coin> coins, Coin c) {
        int amount = 0;
        int length = coins.size();

        for (int i = 0; i < length; i++) {
            if (coins.get(i).equals(c)) {
                amount++;
            }
        }

        return amount;
    }

    private static ArrayList<Product> removeDuplicateProducts(ArrayList<Product> products) {
        ArrayList<Product> productsArrayListWithoutDuplicates = new ArrayList<Product>();
        int length = products.size();

        for (int i = 0; i < length; i++) {
            if (!(productsArrayListWithoutDuplicates.contains(products.get(i)))) {
                productsArrayListWithoutDuplicates.add(products.get(i));
            }
        }

        return productsArrayListWithoutDuplicates;
    }

    private static ArrayList<Coin> removeDuplicateCoins(ArrayList<Coin> coins) {
        ArrayList<Coin> coinsArrayListWithoutDuplicates = new ArrayList<Coin>();
        int length = coins.size();

        for (int i = 0; i < length; i++) {
            if (!coinsArrayListWithoutDuplicates.contains(coins.get(i))) {
                coinsArrayListWithoutDuplicates.add(coins.get(i));
            }
        }

        return coinsArrayListWithoutDuplicates;
    }

    public static void writeProductsToFile(ArrayList<Product> products) {
        Product product;

        if (TextFileReader.getProductsFile().exists()) {
            try {
                fileWriter = new FileWriter(TextFileReader.getProductsFile());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            out = new PrintWriter(fileWriter);

            ArrayList<Product> productsWithoutDuplicates = removeDuplicateProducts(products);
            productsWithoutDuplicates.trimToSize();
            int length = productsWithoutDuplicates.size();

            for (int i = 0; i < length; i++) {
                product = productsWithoutDuplicates.get(i);
                out.println(product.getDescription() + "," + product.getPrice() + "," + duplicateProductsAmount(products, product));
            }

            out.close();
        } else {
            System.out.println(TextFileReader.getProductsFileError());
        }
    }

    public static void writeCoinsToFile(ArrayList<Coin> coins) {
        Coin coin;

        if (TextFileReader.getCoinsFile().exists()) {
            try {
                fileWriter = new FileWriter(TextFileReader.getCoinsFile());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            out = new PrintWriter(fileWriter);

            ArrayList<Coin> coinsWithoutDuplicates = removeDuplicateCoins(coins);
            coinsWithoutDuplicates.trimToSize();
            int length = coinsWithoutDuplicates.size();

            for (int i = 0; i < length; i++) {
                coin = coinsWithoutDuplicates.get(i);
                out.println(coin.getValue() + "," + coin.getName() + "," + duplicateCoinAmount(coins, coin));
            }

            out.close();
        } else {
            System.out.println(TextFileReader.getCoinsFileError());
        }
    }
}