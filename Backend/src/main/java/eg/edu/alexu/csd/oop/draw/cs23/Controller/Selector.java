package eg.edu.alexu.csd.oop.draw.cs23.Controller;


import eg.edu.alexu.csd.oop.draw.cs23.shapes.*;


import eg.edu.alexu.csd.oop.draw.cs23.shapes.*;


public class Selector {
    public Shape select(Drawer drawer,double x,double y){

        Shape[] shapes = drawer.getShapes();
        double dis;
        double dis2;
        for (int i = drawer.getShapeId()+1;i>=0;i--){
            if(shapes[i]==null)
                continue;
            switch (shapes[i].getType()) {
                case "line":
                    if ((((Line)shapes[i]).getX1()==x) && ((Line)shapes[i]).getY1()==y)
                        return shapes[i];
                    if ((((Line)shapes[i]).getX2()==x) && ((Line)shapes[i]).getY2()==y)
                        return shapes[i];

                    dis = (((Line)shapes[i]).getX1()- x) * (((Line)shapes[i]).getY1()-y);
                    dis2 = (x - ((Line)shapes[i]).getX2()) * (y - ((Line)shapes[i]).getY2());
                    dis = dis * dis;
                    dis2 = dis2 * dis2;

                    if(Math.abs(dis-dis2)<2)
                        return shapes[i];
                    break;
                case "square":
                    if( ( (x - ((Square)shapes[i]).getX()) > ((Square)shapes[i]).getLength()) || ((x - ((Square)shapes[i]).getX()) < 0))
                        continue;
                    if( ( (y - ((Square)shapes[i]).getY()) > ((Square)shapes[i]).getLength()) || ((y - ((Square)shapes[i]).getY()) < 0))
                        continue;
                    return shapes[i];
                case "rectangle":
                    if( ((x - ((Rectangle)shapes[i]).getX()) > ((Rectangle)shapes[i]).getWidth())  || ((x - ((Rectangle)shapes[i]).getX()) < 0))
                        continue;
                    if( ((y - ((Rectangle)shapes[i]).getY()) > ((Rectangle)shapes[i]).getLength())   || ((y - ((Rectangle)shapes[i]).getY()) < 0))
                        continue;
                    return shapes[i];
                case "triangle":
                    double d1, d2, d3;
                    boolean has_neg, has_pos;

                    d1 = (x - ((Triangle)shapes[i]).getX2()) * (((Triangle)shapes[i]).getY1() - ((Triangle)shapes[i]).getY2()) - (((Triangle)shapes[i]).getX1() - ((Triangle)shapes[i]).getX2()) * (y - ((Triangle)shapes[i]).getY2());
                    d2 = (x - ((Triangle)shapes[i]).getX3()) * (((Triangle)shapes[i]).getY2() - ((Triangle)shapes[i]).getY3()) - (((Triangle)shapes[i]).getX2() - ((Triangle)shapes[i]).getX3()) * (y - ((Triangle)shapes[i]).getY3());
                    d3 = (x - ((Triangle)shapes[i]).getX1()) * (((Triangle)shapes[i]).getY3() - ((Triangle)shapes[i]).getY1()) - (((Triangle)shapes[i]).getX3() - ((Triangle)shapes[i]).getX1()) * (y - ((Triangle)shapes[i]).getY1());

                    has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
                    has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);

                    if( !(has_neg && has_pos))
                        return shapes[i];

                    break;
                case "circle":

                    dis = (x - ((Circle) shapes[i]).getX());
                    dis2 = (y - ((Circle) shapes[i]).getY());
                    float R = ((Circle) shapes[i]).getR();

                    if ((dis * dis + dis2 * dis2) <= R * R)
                        return shapes[i];

                    break;
                case "ellipse":

                    dis = (((x - ((Ellipse) shapes[i]).getX())) / ((Ellipse) shapes[i]).getRx());
                    dis = dis * dis;
                    dis2 = (((x - ((Ellipse) shapes[i]).getX())) / ((Ellipse) shapes[i]).getRx());
                    dis2 = dis2 * dis2;
                    dis = dis + dis2;
                    if (dis <= 1) {
                        return shapes[i];
                    }

                    break;
            }

        }
        return null;
    }
}