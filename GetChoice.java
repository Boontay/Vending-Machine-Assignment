import java.util.ArrayList;

public class GetChoice {
    public GetChoice() {
        // default constructor.
    }

    public String getCoinChoices(Coin[] coinOptions) {
        String output = "";

        if (coinOptions.length > 0) {
            int option = 1;

            for (Coin coin : coinOptions) {
                output += option + ". " + coin.toString() + "\n";
                option++;
            }
        } else {
            output = "Nothing to view.";
        }

        return output;
    }

    public String getProductChoices(Product[] productOptions) {
        String output = "";

        if (productOptions.length > 0) {
            int option = 1;

            for (Product product : productOptions) {
                output += option + ". " + product.toString() + "\n";
                option++;
            }
        } else {
            output = "Nothing to view.";
        }

        return output;
    }

    public String getProductChoices(ArrayList<Product> productOptions) {
        String output = "";

        if (productOptions.size() > 0) {
            int option = 1;

            for (Product product : productOptions) {
                output += option + ". " + product.toString() + "\n";
                option++;
            }
        } else {
            output = "Nothing to view.";
        }

        return output;
    }

    public Product getSelectedProduct(ArrayList<Product> productOptions, int pos) {
        pos -= 1;

        return productOptions.get(pos);
    }

    public Coin getSelectedCoin(Coin[] coinOptions, int pos) {
        pos -= 1;

        return coinOptions[pos];
    }

    public Product getSelectedProduct(Product[] productOptions, int pos) {
        pos -= 1;

        return productOptions[pos];
    }

    public Object getSelectedChoiceFromArray(Object[] choices, int pos) {
        if (!(choices.length <= 0)) {
            return choices[pos - 1];
        } else {
            return null;
        }
    }

    public String getAvailableChoices(Object[] choices) {
        try {
            if (choices.length <= 0) {
                return "No available choices.";
            } else {
                String output = "";

                Object choice;
                int length = choices.length;

                for (int i = 0; i < length; i++) {
                    choice = choices[i];
                    output += "\n" + (i + 1) + ". " + choice.toString();
                }

                return output;
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}