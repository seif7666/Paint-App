package eg.edu.alexu.csd.oop.draw.cs23.Controller;


import eg.edu.alexu.csd.oop.draw.cs23.shapes.*;

public class ActOperator {
    public void move(Shape shape , float newX, float newY){

        switch (shape.getType()) {
            case "square": {
                ((Square) shape).setX(newX);
                ((Square) shape).setY(newY);

                ((Square)shape).setVerticesfromPoint();
                break;
            }
            case "rectangle": {
                ((Rectangle) shape).setX(newX);
                ((Rectangle) shape).setY(newY);

                ((Rectangle)shape).setVerticesfromPoint();
                break;
            }
            case "triangle": {
                float X2 = ((Triangle) shape).getX2() - ((Triangle) shape).getX1();
                float Y2 = ((Triangle) shape).getY2() - ((Triangle) shape).getY1();
                float X3 = ((Triangle) shape).getX3() - ((Triangle) shape).getX1();
                float Y3 = ((Triangle) shape).getY3() - ((Triangle) shape).getY1();
                ((Triangle) shape).setX1(newX);
                ((Triangle) shape).setY1(newY);
                ((Triangle) shape).setX2(newX + X2);
                ((Triangle) shape).setY2(newY + Y2);
                ((Triangle) shape).setX3(newX + X3);
                ((Triangle) shape).setY3(newY + Y3);
                break;
            }
            case "line": {
                float X2 = ((Line) shape).getX2() - ((Line) shape).getX1();
                float Y2 = ((Line) shape).getY2() - ((Line) shape).getY1();
                ((Line) shape).setX1(newX);
                ((Line) shape).setY1(newY);
                ((Line) shape).setX2(newX + X2);
                ((Line) shape).setY2(newY + Y2);
                break;
            }
            case "circle":
                ((Circle) shape).setX(newX);
                ((Circle) shape).setY(newY);
                break;
            case "ellipse":
                ((Ellipse) shape).setX(newX);
                ((Ellipse) shape).setY(newY);
                break;
        }

    }

    public void resize(Shape shape,float ratio){

        switch (shape.getType()) {
            case "square": {
                ((Square) shape).setLength(((Square) shape).getLength() * ratio);

                ((Square)shape).setVerticesfromPoint();
                break;
            }
            case "rectangle": {
                ((Rectangle) shape).setLength(((Rectangle) shape).getLength() * ratio);

                ((Rectangle)shape).setVerticesfromPoint();
                break;
            }
            case "triangle":
                float X2 = ((Triangle) shape).getX2() - ((Triangle) shape).getX1();
                float Y2 = ((Triangle) shape).getY2() - ((Triangle) shape).getY1();
                float X3 = ((Triangle) shape).getX3() - ((Triangle) shape).getX1();
                float Y3 = ((Triangle) shape).getY3() - ((Triangle) shape).getY1();
                ((Triangle) shape).setX2(((Triangle) shape).getX1() + X2*ratio);
                ((Triangle) shape).setY2(((Triangle) shape).getY1() + Y2*ratio);
                ((Triangle) shape).setX3(((Triangle) shape).getX1() + X3*ratio);
                ((Triangle) shape).setY3(((Triangle) shape).getY1() + Y3*ratio);
                break;
            case "line":
                float X2l = ((Line) shape).getX2() - ((Line) shape).getX1();
                float Y2l = ((Line) shape).getY2() - ((Line) shape).getY1();
                ((Line) shape).setX2(((Line) shape).getX1() + X2l*ratio);
                ((Line) shape).setY2(((Line) shape).getY1() + Y2l*ratio);

                break;
            case "circle":
                ((Circle) shape).setR(ratio * ((Circle) shape).getR());
                break;
            case "ellipse":
                ((Ellipse) shape).setRx(ratio * ((Ellipse) shape).getRx());
                ((Ellipse) shape).setRy(ratio * ((Ellipse) shape).getRy());
                break;
        }

    }

    public void recolor(Shape shape, String in, String out){

        shape.setColor(out);
        shape.setInColor(in);

    }
}
