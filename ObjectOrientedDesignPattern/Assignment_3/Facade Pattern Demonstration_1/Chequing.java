
public class Chequing implements IAccount {

    private int accountNumber;
    private int chequingAmount;

    Chequing(int accountNumber, int chequingAmount){
        this.accountNumber = accountNumber;
        this.chequingAmount = chequingAmount;
    }

    @Override
    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public void deposit(Double amount) {
        System.out.println("Amount deposited");
        this.chequingAmount += amount;
    }

    @Override
    public void withdraw(Double amount) {
        if(chequingAmount >= amount){
            System.out.println("Withdrawn successful");
            chequingAmount -= amount;
        } else{
            System.out.println("Not enough money to withdraw");
        }
    }

    @Override
    public void transfer(IAccount toAccount, Double amount) {
        if(chequingAmount >= amount){
            System.out.println("Transaction successful");
            chequingAmount -= amount;

        } else{
            System.out.println("Not enough money to transfer");
        }
    }

}