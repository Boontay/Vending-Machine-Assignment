public class VendingMachineSimulation {
    public static void main(String[] args) {
        VendingMachineMenu menu = new VendingMachineMenu();
        VendingMachine vendingMachine = new VendingMachine();
        menu.run(vendingMachine);
    }
}
