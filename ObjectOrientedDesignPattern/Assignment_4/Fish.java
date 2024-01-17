public class Fish implements IStructure{
    public String name;
    public String type;

    Fish(String name, String type){
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {    
        return this.name;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public void show() {
        System.out.println("The fish "+ type);
    }
}
