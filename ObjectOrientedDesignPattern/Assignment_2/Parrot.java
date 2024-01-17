
public class Parrot extends Bird {   
    Parrot(String petName, String petType) {
        super(petName, petType);
    }

    void ParrotInformation(String collectedFrom){
        System.out.println("This parrot is collected from "+ collectedFrom);
    }
}
