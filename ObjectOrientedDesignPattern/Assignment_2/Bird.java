
public class Bird extends Pet{
    Bird(String petName, String petType) {
        super(petName, petType);
    }
    
    boolean talkingAbility,FlyAbility;
    void BirdAbility(boolean talkingAbility, boolean FlyAbility){
        this.talkingAbility = talkingAbility;
        this.FlyAbility = FlyAbility;
    }
}
