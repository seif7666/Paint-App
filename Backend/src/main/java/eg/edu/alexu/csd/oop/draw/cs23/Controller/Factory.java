package eg.edu.alexu.csd.oop.draw.cs23.Controller;


import eg.edu.alexu.csd.oop.draw.cs23.shapes.*;

public class Factory {

    public Square createSquare(int id, float x1 , float y1 , float length, String color, String incolor){
        return new Square( id, x1 ,  y1 ,  length,color,incolor);
    }

    public Rectangle createRectangle(int id,float x1 , float y1 , float length , float width,String color,String incolor){
        return  new Rectangle( id, x1 ,  y1 ,  length ,  width,color,incolor);
    }

    public Line createLine(int id ,float x1 , float y1 ,float x2 , float y2,String color,String incolor){
        return new Line( id , x1 , y1 , x2 , y2,color,incolor);
    }

    public Triangle createTriangle(int id ,float x1 , float y1 ,float x2 , float y2,float x3,float y3,String color,String incolor){
        return new Triangle( id , x1 ,  y1 , x2 ,  y2, x3, y3,color,incolor);
    }

    public Ellipse createEllipse(int id,float x,float y, float rx, float ry,String color,String incolor){
        return new Ellipse(id, x, y, rx, ry,color,incolor);
    }

    public Circle createCircle(int id,float x,float y, float r,String color,String incolor){
        return new Circle(id, x, y, r,color,incolor);
    }

    public Shape draw(int id,String type,float n1,float n2,float n3, float n4,float n5,float n6,String in,String out){
        Shape newS=null;

        switch (type) {
            case "square":
                newS = createSquare(id, n1, n2, n3, out, in);
                break;
            case "rectangle":
                newS = createRectangle(id, n1, n2, n3, n4, out, in);
                break;
            case "triangle":
                newS = createTriangle(id, n1, n2, n3, n4, n5, n6, out, in);
                break;
            case "line":
                newS = createLine(id, n1, n2, n3, n4, out, in);
                break;
            case "circle":
                newS = createCircle(id, n1, n2, n3, out, in);
                break;
            case "ellipse":
                newS = createEllipse(id, n1, n2, n3, n4, out, in);
                break;
        }

        return newS;
    }
}