import java.util.Scanner;

public class VendingMachineCLI {
    private VendingMachine vendingMachine;

    public void CLI(Scanner in, Coin[] coinTypes, Product[] productTypes, VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        GetChoice getChoice = new GetChoice();
        boolean more = true;

        while (more) {
            System.out.println("\n" + vendingMachine.getStartMenuOptions() + "\n4. Quit");
            String command = in.nextLine().toUpperCase();

            if (command.equals("1")) {
                System.out.println(getChoice.getAvailableChoices(productTypes));
            } else if (command.equals("2")) {
                System.out.println(getChoice.getAvailableChoices(coinTypes));
                String posString = in.nextLine();
                int pos = Integer.parseInt(posString);
                System.out.println(vendingMachine.addCoin((Coin) getChoice.getSelectedChoiceFromArray(coinTypes, pos)));
            } else if (command.equals("3")) {
                System.out.println(getChoice.getAvailableChoices(productTypes));
                int pos = Integer.parseInt(in.nextLine());
                System.out.println(vendingMachine.buyProduct((Product) getChoice.getSelectedChoiceFromArray(productTypes, pos)));
            } else if (command.equals("4")) {
                vendingMachine.closeDown();
                System.exit(0);
            } else if (command.equals("#*#")) {
                System.out.println("Input password: ");
                String password = in.nextLine();

                if (!vendingMachine.isValidOperatorCode(password)) {
                    System.out.println("Incorrect code.");
                } else {
                    System.out.println("You have logged in.");
                    boolean anotherOperatorInput = true;

                    while (anotherOperatorInput) {
                        System.out.println("\n" + vendingMachine.getOperatorOptions());
                        command = in.nextLine();

                        if (command.equals("1")) {
                            boolean anotherProductOption = true;
                            System.out.println("1. Add Product(s)\n2. Remove Product(s)");

                            while (anotherProductOption) {
                                command = in.nextLine();

                                if (command.equals("1")) {
                                    System.out.println("Description: ");
                                    String description = in.nextLine();

                                    System.out.println("Price: ");
                                    double price = Double.parseDouble(in.nextLine());

                                    System.out.println("Quantity: ");
                                    int quantity = Integer.parseInt(in.nextLine());

                                    vendingMachine.addProduct(new Product(description, price), quantity);
                                    System.out.println("Added " + quantity + " of " + description + ", costing \u20ac" + price);
                                    anotherProductOption = false;
                                } else if (command.equals("2")) {
                                    System.out.println(getChoice.getAvailableChoices(productTypes));

                                    System.out.println("\nProduct value to remove: ");
                                    int pos = Integer.parseInt(in.nextLine());

                                    System.out.println("Quantity to remove: ");
                                    int quantity = Integer.parseInt(in.nextLine());

                                    System.out.println(vendingMachine.removeProduct((Product) getChoice.getSelectedChoiceFromArray(productTypes, pos), quantity));
                                    anotherProductOption = false;
                                }
                            }
                        } else if (command.equals("2")) {
                            System.out.println(vendingMachine.removeMoney());
                            anotherOperatorInput = false;
                        }
                    }
                }
            }
        }
    }
}