import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class VendingMachineGUI extends Application {
    private Coin[] coinTypes = TextFileReader.readCoinsFileToCoinTypesArray();
    private Product[] productTypes = TextFileReader.readFileToProductTypesArray();

    private TextField machineResponseTextField;
    private TextField userPromptsTextField;
    private TextArea userOptionsTextArea;

    private String userInput = "";
    private String defaultUserPrompt = "Input a number corresponding to the option you'd like, and then click 'Confirm'.";
    private String defaultMachineResponse = "Machine Response";
    private String defaultError = "Invalid input. Press 'Back' to go back to the ";

    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button zero;
    private Button hash;
    private Button asterisk;
    private Button quit;
    private Button back;
    private Button confirm;
    private Button clear;

    private Stage stage;

    private VendingMachine vendingMachine = VendingMachineMenu.getVendingMachine();

    private GetChoice getChoice = new GetChoice();

    private int buttonWidth = 60;
    private int buttonHeight = 30;
    private int textFieldAreaWidth = 700;
    private int textFieldHeight = 30;
    private int textAreaHeight = 250;

    public void GUI(Coin[] coinTypes, Product[] productTypes, VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        //this.coinTypes = coinTypes;
        //this.productTypes = productTypes;

        try {
            launch();
        } catch (Exception ex) {
            System.out.println("The GUI could not be started.\n" + ex.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();
        GridPane buttonsGridPane = new GridPane();
        GridPane textGridPane = new GridPane();

        machineResponseTextField = new TextField();
        userPromptsTextField = new TextField();
        userOptionsTextArea = new TextArea();

        machineResponseTextField.setEditable(false);
        userPromptsTextField.setEditable(false);
        userOptionsTextArea.setEditable(false);

        machineResponseTextField.setMinSize(textFieldAreaWidth, textFieldHeight);
        userPromptsTextField.setMinSize(textFieldAreaWidth, textFieldHeight);
        userOptionsTextArea.setMinSize(textFieldAreaWidth, textAreaHeight);

        userOptionsTextArea.setWrapText(true);

        textGridPane.setHgap(15);
        textGridPane.setVgap(15);
        textGridPane.add(userPromptsTextField, 0, 0);
        textGridPane.add(machineResponseTextField, 0, 1);
        textGridPane.add(userOptionsTextArea, 0, 2);
        textGridPane.setAlignment(Pos.CENTER);
        borderPane.setCenter(textGridPane);
        borderPane.setMargin(textGridPane, new Insets(20));

        one = new Button("1");
        one.setMinSize(buttonWidth, buttonHeight);
        two = new Button("2");
        two.setMinSize(buttonWidth, buttonHeight);
        three = new Button("3");
        three.setMinSize(buttonWidth, buttonHeight);
        four = new Button("4");
        four.setMinSize(buttonWidth, buttonHeight);
        five = new Button("5");
        five.setMinSize(buttonWidth, buttonHeight);
        six = new Button("6");
        six.setMinSize(buttonWidth, buttonHeight);
        seven = new Button("7");
        seven.setMinSize(buttonWidth, buttonHeight);
        eight = new Button("8");
        eight.setMinSize(buttonWidth, buttonHeight);
        nine = new Button("9");
        nine.setMinSize(buttonWidth, buttonHeight);
        zero = new Button("0");
        zero.setMinSize(buttonWidth, buttonHeight);
        hash = new Button("#");
        hash.setMinSize(buttonWidth, buttonHeight);
        asterisk = new Button("*");
        asterisk.setMinSize(buttonWidth, buttonHeight);
        quit = new Button("Quit");
        quit.setMinSize(buttonWidth, buttonHeight);
        back = new Button("Back");
        back.setMinSize(buttonWidth, buttonHeight);
        confirm = new Button("Confirm");
        confirm.setMinSize(buttonWidth, buttonHeight);
        clear = new Button("Clear");
        clear.setMinSize(buttonWidth, buttonHeight);

        buttonsGridPane.setHgap(15);
        buttonsGridPane.setVgap(15);
        buttonsGridPane.add(one, 0, 2);
        buttonsGridPane.add(two, 1, 2);
        buttonsGridPane.add(three, 2, 2);
        buttonsGridPane.add(four, 0, 3);
        buttonsGridPane.add(five, 1, 3);
        buttonsGridPane.add(six, 2, 3);
        buttonsGridPane.add(seven, 0, 4);
        buttonsGridPane.add(eight, 1, 4);
        buttonsGridPane.add(nine, 2, 4);
        buttonsGridPane.add(hash, 0, 5);
        buttonsGridPane.add(zero, 1, 5);
        buttonsGridPane.add(asterisk, 2, 5);
        buttonsGridPane.add(back, 0, 6);
        buttonsGridPane.add(confirm, 2, 6);
        buttonsGridPane.add(clear, 1, 6);
        buttonsGridPane.add(quit, 1, 7);
        buttonsGridPane.setAlignment(Pos.CENTER);
        borderPane.setLeft(buttonsGridPane);
        borderPane.setMargin(buttonsGridPane, new Insets(20));

        one.setOnAction(e -> addInput("1"));
        two.setOnAction(e -> addInput("2"));
        three.setOnAction(e -> addInput("3"));
        four.setOnAction(e -> addInput("4"));
        five.setOnAction(e -> addInput("5"));
        six.setOnAction(e -> addInput("6"));
        seven.setOnAction(e -> addInput("7"));
        eight.setOnAction(e -> addInput("8"));
        nine.setOnAction(e -> addInput("9"));
        zero.setOnAction(e -> addInput("0"));
        hash.setOnAction(e -> addInput("#"));
        asterisk.setOnAction(e -> addInput("*"));
        quit.setOnAction(e -> quit());
        clear.setOnAction(e -> clearUserInput());

        stage.setTitle("Work, for FUck's Sake");
        stage.setScene(new Scene(borderPane, 1000, 400));
        stage.setResizable(false);
        this.stage = stage;
        startMenu();
        stage.show();
    }

    public void startMenu() {
        clearUserInput();
        clearButtonActions();

        userOptionsTextArea.setText(vendingMachine.getStartMenuOptions());
        userPromptsTextField.setText(defaultUserPrompt);
        machineResponseTextField.setText(defaultMachineResponse);

        // No back option for the back button.
        confirm.setOnAction(e -> checkStartMenuOptions());
    }

    public void checkStartMenuOptions() {
        if (userInput.equals("1")) {
            showProductsMenu();
        } else if (userInput.equals("2")) {
            coinsMenu();
        } else if (userInput.equals("3")) {
            purchaseMenu();
        } else {
            machineResponseTextField.setText(defaultError + "coins menu.");
            back.setOnAction(e -> startMenu());
            // No option for confirm.
        }
    }

    public void showProductsMenu() {
        clearUserInput();
        clearButtonActions();

        userOptionsTextArea.setText("Description\t\tPrice" + getChoice.getAvailableChoices(productTypes));
        userPromptsTextField.setText(defaultUserPrompt);
        machineResponseTextField.setText(defaultMachineResponse);

        back.setOnAction(e -> startMenu());
        // No option for confirm.
    }

    public void coinsMenu() {
        clearUserInput();
        clearButtonActions();

        userPromptsTextField.setText(defaultUserPrompt);
        userOptionsTextArea.setText(getChoice.getAvailableChoices(coinTypes));
        machineResponseTextField.setText(defaultMachineResponse);

        back.setOnAction(e -> startMenu());
        confirm.setOnAction(e -> checkCoinInput());
    }

    public void checkCoinInput() {
        back.setOnAction(null);
        back.setOnAction(e -> coinsMenu());
        // No option for confirm.


        userPromptsTextField.setText("Press back to go back to the coins menu.");

        try {
            machineResponseTextField.setText(vendingMachine.addCoin(getChoice.getSelectedCoin(coinTypes, Integer.parseInt(userInput))));
            clearUserInput();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            clearUserInput();
            machineResponseTextField.setText(defaultError + "coins menu.");
        }
    }

    public void purchaseMenu() {
        showProductsMenu();
        clearUserInput();
        clearButtonActions();

        confirm.setOnAction(e -> checkPurchaseInput());
        back.setOnAction(e -> startMenu());
    }

    public void checkPurchaseInput() {
        clearButtonActions();

        try {
            machineResponseTextField.setText(vendingMachine.buyProduct((Product) getChoice.getSelectedChoiceFromArray(productTypes, Integer.parseInt(userInput))));
            userPromptsTextField.setText("Press back to go back to the to purchase menu.");
            clearUserInput();
            back.setOnAction(e -> purchaseMenu());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            machineResponseTextField.setText(defaultError + "purchase menu.");
            clearUserInput();
            back.setOnAction(e -> purchaseMenu());
        }
    }

    public void clearUserInput() {
        userInput = "";
    }

    public void clearButtonActions() {
        back.setOnAction(null);
        confirm.setOnAction(null);
    }

    public void addInput(String in) {
        userInput += in;

        if (userInput.equals("#*#")) {
            operatorLoginMenu();
        }
    }

    public void operatorLoginMenu() {
        machineResponseTextField.setText(defaultMachineResponse);
        userPromptsTextField.setText("Input Password");
        userOptionsTextArea.setText("Enter your password, and then click 'Confirm', or, hit back to return to the start menu.");

        clearButtonActions();

        confirm.setOnAction(e -> checkPassword());
        back.setOnAction(e -> startMenu());
    }

    public void checkPassword() {
        clearButtonActions();

        if (vendingMachine.isValidOperatorCode(userInput)) {
            clearUserInput();
            operatorOptions();
        } else {
            clearUserInput();
            back.setOnAction(e -> operatorLoginMenu());
            machineResponseTextField.setText(defaultError + "operator login menu.");
            userPromptsTextField.setText("Invalid Password");
        }
    }

    public void operatorOptions() {
        clearButtonActions();
        clearUserInput();

        machineResponseTextField.setText(defaultMachineResponse);
        userOptionsTextArea.setText(vendingMachine.getOperatorOptions());
        userPromptsTextField.setText(defaultUserPrompt);

        back.setOnAction(e -> startMenu());
        confirm.setOnAction(e -> checkOperatorOptions());
    }

    public void checkOperatorOptions() {
        clearButtonActions();

        if (userInput.equals("1")) {
            clearUserInput();
            productInteractionMenu();
        } else if (userInput.equals("2")) {
            clearUserInput();
            removeAllCoins();
        } else {
            clearUserInput();
            back.setOnAction(e -> operatorOptions());
            machineResponseTextField.setText(defaultError + "operator option menu.");
        }
    }

    public void productInteractionMenu() {
        Stage productMenu = new Stage();

        clearUserInput();

        int labelHeight = 25;
        int labelWidth = 50;

        Button addProductButton = new Button("Add Product");
        addProductButton.setMinSize(buttonWidth, buttonHeight);

        Button removeProductButton = new Button("Remove Product");
        removeProductButton.setMinSize(buttonWidth, buttonHeight);

        Button closeWindowButton = new Button("Close");
        closeWindowButton.setMinSize(buttonWidth, buttonHeight);

        Label descriptionLabel = new Label("Description:");
        descriptionLabel.setMinSize(labelWidth, labelHeight);

        Label priceLabel = new Label("Price:");
        priceLabel.setMinSize(labelWidth, labelHeight);

        Label quantityLabel = new Label("Quantity:");
        quantityLabel.setMinSize(labelWidth, labelHeight);

        Label responseLabel = new Label("Response:");
        responseLabel.setMinSize(labelWidth, labelHeight);

        TextField descriptionTextField = new TextField();
        descriptionTextField.setEditable(true);
        descriptionTextField.setMinSize(textFieldAreaWidth, textFieldHeight);

        TextField priceTextField = new TextField();
        priceTextField.setEditable(true);
        priceTextField.setMinSize(textFieldAreaWidth, textFieldHeight);

        TextField quantityTextField = new TextField();
        quantityTextField.setEditable(true);
        quantityTextField.setMinSize(textFieldAreaWidth, textFieldHeight);

        TextField responseTextField = new TextField();
        responseTextField.setEditable(false);
        quantityTextField.setMinSize(textFieldAreaWidth, textFieldHeight);

        GridPane gridPane = new GridPane();
        gridPane.add(responseLabel, 0, 0);
        gridPane.add(responseTextField, 1, 0);
        gridPane.add(descriptionLabel, 0, 1);
        gridPane.add(descriptionTextField, 1, 1);
        gridPane.add(priceLabel, 0, 2);
        gridPane.add(priceTextField, 1, 2);
        gridPane.add(quantityLabel, 0, 3);
        gridPane.add(quantityTextField, 1, 3);
        gridPane.add(closeWindowButton, 0, 4);
        gridPane.add(addProductButton, 1, 4);
        gridPane.add(removeProductButton, 1, 5);

        addProductButton.setOnAction(e -> responseTextField.setText(removeProduct(descriptionTextField, priceTextField, quantityTextField)));
        removeProductButton.setOnAction(e -> responseTextField.setText(addProduct(descriptionTextField, priceTextField, quantityTextField)));
        closeWindowButton.setOnAction(e -> productMenu.close());
    }

    public String removeProduct(TextField descriptionTextField, TextField priceTextField, TextField quantityTextField) {
        try {
            String description = descriptionTextField.getText();
            double price = Double.parseDouble(priceTextField.getText());
            int quantity = Integer.parseInt(quantityTextField.getText());

            return vendingMachine.removeProduct(new Product(description, price), quantity);
        } catch (ArithmeticException ex) {
            return "The product(s) could not be removed from the machine.";
        }
    }

    public String addProduct(TextField descriptionTextField, TextField priceTextField, TextField quantityTextField) {
        String output = "";
        String issueString = "The product could not be added.";

        try {
            String description = descriptionTextField.getText();
            double price = Double.parseDouble(priceTextField.getText());
            int quantity = Integer.parseInt(quantityTextField.getText());
            String amountString = "";

            output += quantity + " " + description + amountString + " been added to the machine, with a price of " + price;

            if (quantity > 0) {
                amountString = "s have";
            } else if (quantity == 0) {
                amountString = " has";
            } else {
                output = issueString;
            }
        } catch (ArithmeticException ex) {
            output = issueString;
        }

        return output;
    }

    public void removeAllCoins() {
        if (vendingMachine.getStoredCoins().getTotal() > 0) {
            machineResponseTextField.setText(vendingMachine.removeMoney());
        } else {
            machineResponseTextField.setText("No coins in the machine.");
        }
    }

    public void quit() {
        vendingMachine.closeDown();
        stage.close();
        System.exit(0);
    }
}