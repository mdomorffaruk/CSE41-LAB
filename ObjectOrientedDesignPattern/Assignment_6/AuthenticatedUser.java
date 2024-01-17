public class AuthenticatedUser extends WebPageDecorator {
    public AuthenticatedUser(Webpage decoratedpage) {
        super(decoratedpage);
    }

    public void authenticateUser() {
        System.out.println("Authenticating user");
    }

    public void display() {
        super.display();
        this.authenticateUser();
    }
}
