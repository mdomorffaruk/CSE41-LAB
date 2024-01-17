public class Customer {
    public static void main(String[] args) {
        BankService bankService = new BankService();
        int x = bankService.createNewAccount("saving", 1000, 1234);
        System.out.println(x);
    }
}
