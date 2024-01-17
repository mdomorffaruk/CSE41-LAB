import java.util.ArrayList;
import java.util.Hashtable;

public class Warhouse implements IOrder {
    private Hashtable<String, Integer> stock;
    private String address;

    public String getAddress(){
        return this.address;
    }

    Warhouse(String address){
        this.address = address;
        this.stock = new Hashtable<>();
    }

    void CreateStock(ArrayList<String> sku, ArrayList<Integer> amount, int x, int y){
        for(int i=x;i<y;i++){
            stock.put(sku.get(i), amount.get(i));
        }
    }   

    @Override
    public void fulfillorder(Order order) {
        System.out.println(order.item.sku + " -> " + order.item.amount + "\t");
        if(order.itemList.isEmpty()) this.stock.replace(order.item.sku, stock.get(order.item.sku).intValue()-order.item.amount);
        else
        for(Item item : order.itemList){
            this.stock.replace(item.sku, stock.get(item.sku).intValue()-1);
        }
    }

    public void showStock(){
        System.out.println(stock.keySet());
        System.out.println(stock.values());
    }

    public int currentInventory(Item item) {
        if(stock.containsKey(item.sku))
            return stock.get(item.sku).intValue();
        return 0;
    }
}