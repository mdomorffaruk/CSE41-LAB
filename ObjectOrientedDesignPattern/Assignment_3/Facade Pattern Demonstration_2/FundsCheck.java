public class FundsCheck {
    private double cashInAccount = 1000.00;

    public double getCashInAccount() { return cashInAccount;}

    public void decreaseCashInAccount(double cashToWithdrawan){
        cashInAccount -= cashToWithdrawan;
    }
    
    public void inreaseCashInAccount(double cashDeposited){
        cashInAccount += cashDeposited;
    }

    public boolean haveEnoughMoney(double cashToGet) {
        if(cashToGet > getCashInAccount()) {
            System.out.println("Error: You don't have enough money");
            System.out.println("Current Balance: "+ cashInAccount);
            return false;
        }else{
            decreaseCashInAccount(cashToGet);
            System.out.println("Withdrawal Complete: Current Balance is " + getCashInAccount());
            return true;
        }
    }

    public void makeDeposit(double cashToDeposit) {
        inreaseCashInAccount(cashToDeposit);
        System.out.println("Deposit Complete: Current Balance is "+ getCashInAccount());
    }
}
