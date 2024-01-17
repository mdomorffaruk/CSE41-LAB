import java.util.ArrayList;

public class Pond implements IStructure{
    public String location, type;

    private ArrayList < IStructure > fishes;

    Pond(String address, String type){
        this.fishes = new ArrayList< IStructure > ();
        this.location = address;
        this.type = type; 
    }

    public int addISructure(IStructure fish){
        this.fishes.add(fish);
        return this.fishes.size()-1;
    }

    public IStructure getIStructure(int number){
        return this.fishes.get(number);
    }

    @Override
    public String getName() {
        return location;    
    }

    @Override
    public String getType() {
        return type;
    }
    
    public void show() {
        System.out.println("We are in "+ type);
    }
}
