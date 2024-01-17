public class Pet{
    String petName;
    String petType;

    Pet(String petName, String petType){
        this.petName = petName;
        this.petType = petType;
    }

    int num,price;

    void sale(int num,int price){
        System.out.println("How many "+petName+" you want to buy");
        this.num = num;
        this.price = num*price;
    }
}