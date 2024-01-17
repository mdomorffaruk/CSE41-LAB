public class Program {
    public static void main(String args[]) {
        Webpage myPage = new BasicWebPage();
        myPage = new AuthorizedWebpage(myPage);
        myPage = new AuthenticatedUser(myPage);
        myPage.display();
    }
}