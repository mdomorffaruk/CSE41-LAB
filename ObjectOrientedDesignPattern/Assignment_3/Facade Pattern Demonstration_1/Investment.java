
public class Investment implements IAccount {

    private int accountNumber;
    private int investAmount;

    Investment(int accountNumber, int investAmount){
        this.accountNumber = accountNumber;
        this.investAmount = investAmount;
    }

    @Override
    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public void deposit(Double amount) {
        System.out.println("Amount deposited");
        this.investAmount += amount;
    }

    @Override
    public void withdraw(Double amount) {
        if(investAmount >= amount){
            System.out.println("Withdrawn successful");
            investAmount -= amount;
        } else{
            System.out.println("Not enough money to withdraw");
        }
    }

    @Override
    public void transfer(IAccount toAccount, Double amount) {
        if(investAmount >= amount){
            System.out.println("Transaction successful");
            investAmount -= amount;

        } else{
            System.out.println("Not enough money to transfer");
        }
    }

}