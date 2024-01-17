import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static ArrayList<String> arraylist1;
    static ArrayList<Integer> arraylist2;
    static ArrayList < Warhouse > warhouses;
    public static void main(String[] args) {
        arraylist1 = new ArrayList<String>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N"));
        arraylist2 = new ArrayList<Integer>(Arrays.asList(6,3,4,7,6,3,1,6,8,9,5,3,1,6,9,8,5,3,2,5,7,8,5,3,2,8,9));
        warhouses = new ArrayList<Warhouse>(3);

        Warhouse warhouse1 = new Warhouse("Rajshahi");
        warhouse1.CreateStock(arraylist1, arraylist2, 0, 5);
        Warhouse warhouse2 = new Warhouse("Khulna");
        warhouse2.CreateStock(arraylist1, arraylist2, 3, 9);
        Warhouse warhouse3 = new Warhouse("Dhaka");
        warhouse3.CreateStock(arraylist1, arraylist2, 8, 13);

        warhouses.add(warhouse1);    
        warhouses.add(warhouse2);    
        warhouses.add(warhouse3);    

        // warhouse1.showStock();
        // warhouse2.showStock();
        // warhouse3.showStock();

        Order order1 = new Order(arraylist1, arraylist2, 5, 12);
        // order1.showOrder();

        Orderfulfill orderfulfill = new Orderfulfill(warhouses);
        orderfulfill.fulfillorder(order1);

        System.out.println("\n\nHello\n\n");
        // warhouse1.showStock();
        // warhouse2.showStock();
        // warhouse3.showStock();

    }
}
