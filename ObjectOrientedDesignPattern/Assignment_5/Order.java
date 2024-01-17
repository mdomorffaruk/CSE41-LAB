import java.util.ArrayList;

public class Order {
    Item item;
    public ArrayList <Item> itemList = new ArrayList<Item>();

    Order(Item item){
        this.item = item;
    }

    Order(ArrayList <String> sku , ArrayList<Integer> amount, int x, int y){
        for(int i=x;i<y;i++){
            itemList.add(new Item(sku.get(i), amount.get(i)));
        }
    }  

    public void showOrder(){
        for(Item item : itemList){
            System.out.println(item.sku + " -> " + item.amount + "\t");
        }
    }
}
