
public interface IAccount {
    public int getAccountNumber();
    public void deposit(Double amount);
    public void withdraw(Double amount);
    public void transfer(IAccount toAccount, Double amount);
}
   
