package eg.edu.alexu.csd.oop.draw.cs23.shapes;

import java.util.List;

public class Square extends Polygen{
    private Float x;
    private Float y;
    private Float length;
    public Square(int id,Pair<Float, Float>[] vertices) {
        super("square",id,vertices);
    }

    public  Square(int id,float x1 , float y1 , float length,String color, String incolor){
        super( "square",id,new Pair[4]);
        this.x = x1;
        this.y = y1;
        this.length = length;
        setColor(color);
        setInColor(incolor);
        Pair<Float, Float>[] vertices = new Pair[4];
        vertices[0] = new Pair<>(x1,y1);
        vertices[1] = new Pair<>(x1,y1);
        vertices[2] = new Pair<>(x1,y1);
        vertices[3] = new Pair<>(x1,y1);
        vertices[1].setFirst(vertices[1].getFirst() + length);
        vertices[2].setFirst(vertices[2].getFirst() + length);
        vertices[3].setSecond(vertices[3].getSecond() + length);
        vertices[2].setSecond(vertices[2].getSecond() + length);
        super.setVertices(vertices);
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x1) {
        this.x = x1;
    }


    public Float getY() {
        return y;
    }

    public void setY(Float y1) {
        this.y = y1;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public void setVerticesfromPoint() {
        Pair<Float, Float>[] v = new Pair[4];
        v[0] = new Pair<>(x,y);
        v[1] = new Pair<>(x,y);
        v[2] = new Pair<>(x,y);
        v[3] = new Pair<>(x,y);
        v[1].setFirst(v[1].getFirst() +length);
        v[2].setFirst(v[2].getFirst() + length);
        v[3].setSecond(v[3].getSecond() +length);
        v[2].setSecond(v[2].getSecond() + length);
        super.setVertices(v);
    }
}
