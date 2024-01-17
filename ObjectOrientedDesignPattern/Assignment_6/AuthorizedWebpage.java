public class AuthorizedWebpage extends WebPageDecorator {
    public AuthorizedWebpage(Webpage decoratedpage) {
        super(decoratedpage);
    }

    public void authorizedUser() {
        System.out.println("Authorizing user");
    }

    public void display() {
        super.display();
        this.authorizedUser();
    }
}
