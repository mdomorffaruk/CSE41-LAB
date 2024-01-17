public class Animal extends Pet {
    Animal(String petName, String petType) {
        super(petName, petType);
    }

    boolean adorable, affordable;
    void AnimalQuality(boolean adorable, boolean affordable){
        this.adorable = adorable;
        this.affordable = affordable;
    }
    
}
