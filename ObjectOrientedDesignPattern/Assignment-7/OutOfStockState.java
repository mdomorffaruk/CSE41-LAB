public class OutOfStockState implements State {

    @Override
    public void insertDollar(VendingMachine vendingMachine) {
        System.out.println("Out of stock");
        vendingMachine.doReturnMoney();
    }

    @Override
    public void ejectMoney(VendingMachine vendingMachine) {
        vendingMachine.insertDollar();
    }

    @Override
    public void dispense(VendingMachine vendingMachine) {
        System.out.println("Out of stock");
    }
    
}
