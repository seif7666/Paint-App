package eg.edu.alexu.csd.oop.draw.cs23.shapes;


public abstract class Curve extends Shape {
    private  String function;

    public Curve (String type,int id,String function){
        this.function = function;
        this.setId(id);
        this.setType(type);
    }
    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
