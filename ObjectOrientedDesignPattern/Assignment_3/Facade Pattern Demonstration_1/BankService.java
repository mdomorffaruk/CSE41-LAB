import java.util.Hashtable;

public class BankService {
    private Hashtable<Integer, IAccount> bankAccounts;

    BankService() {
        this.bankAccounts = new Hashtable<Integer, IAccount>();
    }

    public int createNewAccount(String type, int initAmount, int accountNumber) {
        IAccount newAccount = null;
        switch (type) {
            case "chequing":
                newAccount = new Chequing(accountNumber, initAmount);
                break;
            case "saving":
                newAccount = new Saving(accountNumber, initAmount);
                break;
            case "investment":
                newAccount = new Investment(accountNumber, initAmount);
                break;
            default:
                System.out.println("Invalid account type");
                break;
        }
        if (newAccount != null) {
            this.bankAccounts.put(newAccount.getAccountNumber(),
                    newAccount);
            return newAccount.getAccountNumber();
        }
        return -1;
    }

    public void transferMoney(int to, int from, Double amount) {
        IAccount toAccount = this.bankAccounts.get(to);
        IAccount fromAccount = this.bankAccounts.get(from);
        fromAccount.transfer(toAccount, amount);
    }
}
