import java.util.Scanner;

public class PetStore {
    static String petName,petType,collectedForm,nature; 
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Please enter parrotName , parrotType, collectedFrom");
        petName = scanner.nextLine();
        petType = scanner.nextLine();
        collectedForm = scanner.nextLine();
        Parrot p1 = new Parrot(petName, petType);
        p1.BirdAbility(true, true);
        p1.ParrotInformation(collectedForm);
        
        System.out.println("Please enter catName , catType, nature");
        petName = scanner.nextLine();
        petType = scanner.nextLine();
        nature = scanner.nextLine();
        Cat c1 = new Cat(petName,petType);
        c1.CatInformation(nature);
    }
   
}
