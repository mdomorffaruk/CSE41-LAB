public class AccountNumberCheck {
    private int accunntNumber = 12345678;
    public int getAccountNumber() {return accunntNumber;}
    
    public boolean accountActive(int accountNumber) {
        return this.accunntNumber==accountNumber;
    }
}
