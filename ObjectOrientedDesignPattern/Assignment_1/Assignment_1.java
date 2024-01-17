// Interface for the first behavior
interface Swimming {
    void swim();
}

// Interface for the second behavior
interface Flying {
    void fly();
}

// Class that implements both Swimming and Flying interfaces
class Bird implements Swimming, Flying {
    @Override
    public void swim() {
        System.out.println("Bird is swimming");
    }

    @Override
    public void fly() {
        System.out.println("Bird is flying");
    }
}

public class Assignment_1 {
    public static void main(String[] args) {
        Bird bird = new Bird();
        bird.swim(); 
        /*<<<<<<< HEAD*/
        bird.fly();  
/*=======*/
        bird.fly(); 
/*>>>>>>> f3b321e324d5fcb614cc0182337afbedddaa89a3*/
    }
}
