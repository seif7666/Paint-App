package eg.edu.alexu.csd.oop.draw.cs23.shapes;

public class Triangle extends Polygen{
    private Float x1;
    private Float x2;
    private Float x3;
    private Float y1;
    private Float y2;
    private Float y3;

    public Triangle(int id,Pair<Float, Float>[] vertices) {
        super("triangle",id,vertices);
    }

    public  Triangle(int id ,float x1 , float y1 ,float x2 , float y2,float x3,float y3,String color,String incolor){
        super("triangle",id, new Pair[4]);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        setColor(color);
        setInColor(incolor);
        Pair<Float, Float>[] vertices = new Pair[3];
        vertices[0] = new Pair<>(x1,y1);
        vertices[1] = new Pair<>(x2,y2);
        vertices[2] = new Pair<>(x3,y3);
        super.setVertices(vertices);
    }

    public Float getX1() {
        return x1;
    }

    public void setX1(Float x1) {
        this.x1 = x1;
    }

    public Float getX2() {
        return x2;
    }

    public void setX2(Float x2) {
        this.x2 = x2;
    }

    public void setX3(Float x3) {
        this.x3 = x3;
    }

    public Float getX3() {
        return x3;
    }

    public Float getY1() {
        return y1;
    }

    public void setY1(Float y1) {
        this.y1 = y1;
    }

    public Float getY2() {
        return y2;
    }

    public void setY2(Float y2) {
        this.y2 = y2;
    }

    public Float getY3() {
        return y3;
    }

    public void setY3(Float y3) {
        this.y3 = y3;
    }

}
