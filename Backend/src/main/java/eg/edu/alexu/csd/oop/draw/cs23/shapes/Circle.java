package eg.edu.alexu.csd.oop.draw.cs23.shapes;

public class Circle extends Curve {
    private Float x;
    private Float y;
    private Float r;

    public Circle(int id,String f) {
        super("circle",id,f);
    }

    public Circle(int id,float x,float y, float r,String color,String incolor){
        super("circle",id,"");
        this.x = x;
        this.y = y;
        this.r = r;
        setColor(color);
        setInColor(incolor);
        String function = "(x – " + x + ")^2 + (y – " + y + ")^2 = +" + r + "^2";
        this.setFunction(function);
    }
    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public void setR(Float r) {
        this.r = r;
    }

    public Float getR() {
        return r;
    }
}
