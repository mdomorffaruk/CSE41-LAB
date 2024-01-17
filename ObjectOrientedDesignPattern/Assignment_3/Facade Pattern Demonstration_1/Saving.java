
public class Saving implements IAccount {

    private int accountNumber;
    private int savingAmount;

    Saving(int accountNumber, int savingAmount){
        this.accountNumber = accountNumber;
        this.savingAmount = savingAmount;
    }

    @Override
    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public void deposit(Double amount) {
        System.out.println("Amount deposited");
        this.savingAmount += amount;
    }

    @Override
    public void withdraw(Double amount) {
        if(savingAmount >= amount){
            System.out.println("Withdrawn successful");
            savingAmount -= amount;
        } else{
            System.out.println("Not enough money to withdraw");
        }
    }

    @Override
    public void transfer(IAccount toAccount, Double amount) {
        if(savingAmount >= amount){
            System.out.println("Transaction successful");
            savingAmount -= amount;

        } else{
            System.out.println("Not enough money to transfer");
        }
    }

}