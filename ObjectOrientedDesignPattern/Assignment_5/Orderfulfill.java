import java.util.ArrayList;

public class Orderfulfill {
    private ArrayList <Warhouse> warhouses;
    Orderfulfill(ArrayList <Warhouse> warhouses){
        this.warhouses = warhouses;
    }

    public void fulfillorder(Order order){
        for (Item item: order.itemList) {
            
            for (Warhouse warhouse: warhouses) {
                if(warhouse.currentInventory(item) >= item.amount){
                    warhouse.fulfillorder(new Order(item));
                }  
            }
        }
    }
}
