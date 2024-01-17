public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.insertDollar();
        vendingMachine.insertDollar();
        vendingMachine.dispense();
        vendingMachine.ejectMoney();

    }
}
