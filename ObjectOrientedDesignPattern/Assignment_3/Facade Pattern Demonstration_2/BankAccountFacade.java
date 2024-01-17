public class BankAccountFacade {
    private int accountNumber;
    private int securityCode;

    AccountNumberCheck accntChecker;
    SecurityCodeCheck codeChecker;
    FundsCheck fundsChecker;
    WelcomeToBank bankWelcome;

    public BankAccountFacade(int newAccntNum, int newSecCode){
        accountNumber = newAccntNum;
        securityCode = newSecCode;

        bankWelcome = new WelcomeToBank();
        accntChecker = new AccountNumberCheck();
        codeChecker = new SecurityCodeCheck();
        fundsChecker = new FundsCheck();
    }

    public int getAccountNumber() { return accountNumber; }
    public int getSecurityCode() { return securityCode; }

    public void withdrawCash( double cashToGet){

        if(accntChecker.accountActive(getAccountNumber()) && 
            codeChecker.isCodeCorrect(getSecurityCode()) && 
            fundsChecker.haveEnoughMoney(cashToGet)) {
                System.out.println("Transaction Complete\n");
            } else {
                System.out.println("Transaction Failed\n");
            }
    }

    public void depositCash( double cashToDeposit){
        if(accntChecker.accountActive(accountNumber) && 
            codeChecker.isCodeCorrect(getSecurityCode())){

                fundsChecker.makeDeposit(cashToDeposit);
                System.out.println("Transaction Complete\n");
            } else {
                System.out.println("Transaction Failed\n");
            }
    }
}
