package eg.edu.alexu.csd.oop.draw.cs23.shapes;

public class Line extends Polygen {
    private Float x1;
    private Float x2;
    private Float y1;
    private Float y2;

    public Line(int id,Pair<Float, Float>[] vertices) {
        super("line",id,vertices);
    }

    public  Line(int id ,float x1 , float y1 ,float x2 , float y2,String color,String incolor){
        super("line",id, new Pair[4]);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        setColor(color);
        setInColor(incolor);
        Pair<Float, Float>[] vertices = new Pair[2];
        vertices[0] = new Pair<>(x1,y1);
        vertices[1] = new Pair<>(x2,y2);
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
}
