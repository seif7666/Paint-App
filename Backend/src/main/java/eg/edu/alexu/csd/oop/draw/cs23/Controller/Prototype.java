package eg.edu.alexu.csd.oop.draw.cs23.Controller;

import eg.edu.alexu.csd.oop.draw.cs23.shapes.*;


public class Prototype {
    private Factory factory;

    public Prototype(Factory factory){
        this.factory = factory;
    }

    public Shape createClone(Shape shape){
        Shape prototype = null;
        if(shape.getType().equals("square"))
            prototype = factory.createSquare(shape.getId(),((Square)shape).getX(),((Square)shape).getY(),((Square)shape).getLength(),shape.getColor(),shape.getInColor());
        else if(shape.getType().equals("rectangle"))
            prototype = factory.createRectangle(shape.getId(),((Rectangle) shape).getX(),((Rectangle) shape).getY(),((Rectangle)shape).getLength(),((Rectangle)shape).getWidth(),shape.getColor(),shape.getInColor());
        else if(shape.getType().equals("triangle"))
            prototype = factory.createTriangle(shape.getId(), ((Triangle)shape).getX1(),((Triangle)shape).getY1(),((Triangle)shape).getX2(),((Triangle)shape).getY2(),((Triangle)shape).getX3(),((Triangle)shape).getY3(),shape.getColor(),shape.getInColor());
        else if(shape.getType().equals("line"))
            prototype = factory.createLine(shape.getId(),((Line) shape).getX1(),((Line) shape).getY1(), ((Line) shape).getX2(),((Line) shape).getY2(),shape.getColor(),shape.getInColor());
        else if(shape.getType().equals("circle"))
            prototype = factory.createCircle(shape.getId(),((Circle)shape).getX(),((Circle)shape).getY(),((Circle)shape).getR(),shape.getColor(),shape.getInColor());
        else if(shape.getType().equals("ellipse"))
            prototype = factory.createEllipse(shape.getId(),((Ellipse)shape).getX(),((Ellipse)shape).getY(),((Ellipse)shape).getRx(),((Ellipse)shape).getRy(),shape.getColor(),shape.getInColor());

        return prototype;
    }
}
