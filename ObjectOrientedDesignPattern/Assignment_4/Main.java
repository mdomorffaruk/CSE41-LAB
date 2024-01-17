public class Main {
    public static void main(String[] args) {
        Pond ponds = new Pond("Dhaka", "Pond Management");
        Pond pnod_one = new Pond("Rajshahi", "Normal Pond");
        Pond pond_two = new Pond("Khulna", "Salted Pond");

        Fish fish_one = new Fish("CatFish", "Lives in normal water");
        Fish fish_two = new Fish("Hilsha", "Lives in salted water");
        Fish fish_three = new Fish("Salmon", "Lives in river");


        int pond1 = ponds.addISructure(pnod_one);
        int pond2 = ponds.addISructure(pond_two);

        int fish1 = pnod_one.addISructure(fish_one);
        int fish2 = pond_two.addISructure(fish_two);
        int fish3 = pnod_one.addISructure(fish_three);

        ponds.show();
        Pond currentPond = (Pond) ponds.getIStructure(pond1);
        currentPond.show();
        Fish currentFish = (Fish) currentPond.getIStructure(0);
        currentFish.show();
    }
}
