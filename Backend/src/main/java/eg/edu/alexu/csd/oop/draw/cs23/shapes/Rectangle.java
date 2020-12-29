package eg.edu.alexu.csd.oop.draw.cs23.shapes;

public class Rectangle extends Polygen {
    private Float x;
    private Float y;
    private Float length;
    private Float width;

    public Rectangle(int id,Pair<Float, Float>[] vertices) {
        super("rectangle",id,vertices);
    }

    public  Rectangle(int id,float x , float y , float length , float width,String color,String incolor){
        super("rectangle",id, new Pair[4]);
        this.x = x;
        this.y = y;
        this.length = length;
        this.width = width;
        setColor(color);
        setInColor(incolor);
        Pair<Float, Float>[] vertices = new Pair[4];
        vertices[0] = new Pair<>(x,y);
        vertices[1] = new Pair<>(x,y);
        vertices[2] = new Pair<>(x,y);
        vertices[3] = new Pair<>(x,y);
        vertices[1].setFirst(vertices[1].getFirst()+length);
        vertices[2].setFirst(vertices[2].getFirst()+length);
        vertices[3].setSecond(vertices[3].getSecond()+width);
        vertices[2].setSecond(vertices[2].getSecond()+width);
        super.setVertices(vertices);
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getY() {
        return y;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public void setVerticesfromPoint() {
        Pair<Float, Float>[] v = new Pair[4];
        v[0] = new Pair<>(x,y);
        v[1] = new Pair<>(x,y);
        v[2] = new Pair<>(x,y);
        v[3] = new Pair<>(x,y);
        v[1].setFirst(v[1].getFirst() +length);
        v[2].setFirst(v[2].getFirst() + length);
        v[3].setSecond(v[3].getSecond() +width);
        v[2].setSecond(v[2].getSecond() + width);
        super.setVertices(v);
    }
}
