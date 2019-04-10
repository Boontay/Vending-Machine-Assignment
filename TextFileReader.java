import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextFileReader {
    private static Scanner in;
    private static String[] fileElements;
    private static File productsFile = new File("src/stock.txt");
    private static File coinsFile = new File("src/money.txt" );
    private static File coinTypesFile = new File("src/coinTypes.txt");
    private static File operatorsFile = new File("src/operators.txt");

    public static File getProductsFile() {
        return productsFile;
    }

    public static File getCoinsFile() {
        return coinsFile;
    }

    public static File getCoinTypesFile() {
        return coinTypesFile;
    }

    public static File getOperatorsFile() {
        return operatorsFile;
    }

    public static String getProductsFileError() {
        return productsFile.getName() + " does not exist.";
    }

    public static String getCoinsFileError() {
        return coinsFile.getName() + " does not exist.";
    }

    public static String getCoinTypesFileError() {
        return coinTypesFile.getName() + " does not exist.";
    }

    public static String getOperatorsFileError() {
        return operatorsFile.getName() + " does not exist.";
    }

    public static CoinSet readCoinsFileToCoinSet() {
        Coin coin;
        CoinSet machineCoins;


        if (coinsFile.isFile()) {
            machineCoins = new CoinSet();
            int quantity;

            try {
                in = new Scanner(coinsFile);
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }

            while (in.hasNext()) {
                fileElements = in.nextLine().split(",");
                coin = new Coin(Double.parseDouble(fileElements[0]), fileElements[1]);
                quantity = Integer.parseInt(fileElements[2]);

                for (int i = 0; i < quantity; i++) {
                    machineCoins.addCoin(coin);
                }
            }

            return machineCoins;
        } else {
            try {
                coinsFile.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            System.out.println(coinsFile.getName() + " does not exist.");
            return null;
        }
    }

    public static ArrayList<Coin> readCoinsFileToCoinTypesArrayList() {
        ArrayList<Coin> coinTypesArrayList;

        if (coinTypesFile.isFile()) {
            coinTypesArrayList = new ArrayList<Coin>();
            Coin coin;
            int pos = 0;

            try {
                in = new Scanner(coinTypesFile);
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }

            while (in.hasNext()) {
                fileElements = in.nextLine().split(",");
                coin = new Coin(Double.parseDouble(fileElements[0]), fileElements[1]);
                coinTypesArrayList.add(coin);
                pos++;
            }

            coinTypesArrayList.trimToSize();
            return coinTypesArrayList;
        } else {
            try {
                coinTypesFile.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            System.out.println(getCoinTypesFileError());
            return null;
        }
    }

    public static Coin[] readCoinsFileToCoinTypesArray() {
        ArrayList<Coin> coinTypesArrayList = readCoinsFileToCoinTypesArrayList();
        Coin[] coinTypesArray = new Coin[coinTypesArrayList.size()];
        coinTypesArray = coinTypesArrayList.toArray(coinTypesArray);
        return coinTypesArray;
    }

    public static ArrayList<Product> readFileToProductTypesArrayList() {
        ArrayList<Product> productTypesArrayList;

        if (productsFile.exists()) {
            productTypesArrayList = new ArrayList<Product>();
            Product product;

            try {
                in = new Scanner(productsFile);
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }

            while (in.hasNext()) {
                fileElements = in.next().split(",");
                product = new Product(fileElements[0], Double.parseDouble(fileElements[1]));
                productTypesArrayList.add(product);
            }

            return productTypesArrayList;
        } else {
            try {
                productsFile.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            System.out.println(getProductsFileError());
            return null;
        }
    }

    public static Product[] readFileToProductTypesArray() {
        ArrayList<Product> productTypesArrayList = readFileToProductTypesArrayList();
        Product[] productTypesArray = new Product[productTypesArrayList.size()];
        productTypesArray = productTypesArrayList.toArray(productTypesArray);
        return productTypesArray;
    }

    public static ArrayList<Product> readFileToProductsArrayList() {
        ArrayList<Product> productsArrayList;

        if (productsFile.isFile()) {
            productsArrayList = new ArrayList<Product>();
            Product product;
            int quantity;

            try {
                in = new Scanner(productsFile);
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }

            while (in.hasNext()) {
                fileElements = in.nextLine().split(",");
                product = new Product(fileElements[0], Double.parseDouble(fileElements[1]));
                quantity = Integer.parseInt(fileElements[2]);

                for (int i = 0; i < quantity; i++) {
                    productsArrayList.add(product);
                }
            }

            return productsArrayList;
        } else {
            try {
                productsFile.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            System.out.println(getProductsFileError());
            return  null;
        }
    }

    public static ArrayList<Operator> readFileToOperatorsArrayList() {
        ArrayList<Operator> operatorsArrayList;
        Operator operator;

        if (operatorsFile.isFile()) {
            operatorsArrayList = new ArrayList<Operator>();

            try {
                in = new Scanner(operatorsFile);
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }

            while (in.hasNext()) {
                fileElements = in.nextLine().split(",");
                operator = new Operator(fileElements[0].trim(), fileElements[1].trim());
                operatorsArrayList.add(operator);
            }

            return operatorsArrayList;
        } else {
            try {
                operatorsFile.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            System.out.println(getOperatorsFileError());
            return null;
        }
    }
}