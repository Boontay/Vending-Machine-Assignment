import java.util.Scanner;

public class VendingMachineMenu {
    private Scanner input = new Scanner(System.in);
    private Coin[] coinTypes = TextFileReader.readCoinsFileToCoinTypesArray();
    private Product[] productTypes = TextFileReader.readFileToProductTypesArray();
    private VendingMachineGUI vendingMachineGUI = new VendingMachineGUI();
    private VendingMachineCLI vendingMachineCLI = new VendingMachineCLI();
    private static VendingMachine vendingMachine = new VendingMachine();

    /*
    public VendingMachineMenu() {
        input = new Scanner(System.in);
        vendingMachineGUI = new VendingMachineGUI();
        vendingMachineCLI = new VendingMachineCLI();
        coinTypes = TextFileReader.readCoinsFileToCoinTypesArray();
        productTypes = TextFileReader.readFileToProductTypesArray();
    }
    */

    public void run(VendingMachine vendingMachine) {
        boolean more = true;
        this.vendingMachine = vendingMachine;

        while (more) {
            System.out.println("C)LI or G)UI ? Q)uit");
            String command = input.nextLine().toUpperCase();

            if (command.equals("C")) {
                vendingMachineCLI.CLI(input, coinTypes, productTypes, vendingMachine);
            } else if (command.equals("G")) {
                vendingMachineGUI.GUI(coinTypes, productTypes, vendingMachine);
            } else if (command.equals("Q")) {
                System.exit(0);
            }
        }
    }

    public static VendingMachine getVendingMachine() {
        return vendingMachine;
    }
}
