package eg.edu.alexu.csd.oop.draw.cs23.shapes;

public class Ellipse extends Curve{
    private Float x;
    private Float y;
    private Float rx;
    private Float ry;

    public Ellipse(int id,String f) {
        super("ellipse",id,f);
    }

    public Ellipse(int id,float x,float y, float rx, float ry,String color,String incolor){
        super("ellipse",id,"");
        this.x = x;
        this.y = y;
        this.rx = rx;
        this.ry = ry;
        setColor(color);
        setInColor(incolor);
        String function = "(x – " + x + ")^2/" + (rx*rx) + " + (y – " + y + ")^2/ " + (ry*ry)  + " = 1" ;
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

    public void setRx(Float rx) {
        this.rx = rx;
    }

    public Float getRx() {
        return rx;
    }

    public void setRy(Float ry) {
        this.ry = ry;
    }

    public Float getRy() {
        return ry;
    }
}
