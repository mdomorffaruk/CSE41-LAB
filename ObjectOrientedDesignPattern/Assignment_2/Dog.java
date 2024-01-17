public class Dog extends Animal{
    Dog(String petName, String petType) {
        super(petName, petType);
    }
    
    void DogInformation(String nature){
        System.out.println("This dog is "+nature+" in nature");
    }
}
