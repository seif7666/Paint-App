package eg.edu.alexu.csd.oop.draw.cs23.server;

import eg.edu.alexu.csd.oop.draw.cs23.Controller.*;

import eg.edu.alexu.csd.oop.draw.cs23.shapes.Circle;
import eg.edu.alexu.csd.oop.draw.cs23.shapes.Ellipse;
import eg.edu.alexu.csd.oop.draw.cs23.shapes.Line;
import eg.edu.alexu.csd.oop.draw.cs23.shapes.Rectangle;
import eg.edu.alexu.csd.oop.draw.cs23.shapes.Shape;
import eg.edu.alexu.csd.oop.draw.cs23.shapes.Square;
import eg.edu.alexu.csd.oop.draw.cs23.shapes.Triangle;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileOutputStream;

@Service

public class Server {

    Selector selector = new Selector();
    Factory factory = new Factory();
    Drawer drawer = new Drawer();
    Prototype prototype = new Prototype(factory);
    ActOperator actOperator = new ActOperator();


    private static final Server service = new Server();

    private Server(){}

    public static Server getService() {
        return service;
    }

    public SentShape deleteShape(float x , float y){
        Shape s = selector.select(drawer,x,y);
        if(s == null)
            return null;

        drawer.addAct(s,null);

        return Server.getSentShape(s);
    }

    public SentShape[] moveShape(float currX , float currY , float newX , float newY ){
        Shape oldS = selector.select(drawer,currX,currY);

        if(oldS== null)
            return null;//no shape in selected point

        Shape newS = prototype.createClone(oldS);
        actOperator.move(newS,newX,newY);

        drawer.addAct(oldS,newS);
        return new SentShape[]{Server.getSentShape(oldS) , Server.getSentShape(newS)};
    }
    public SentShape[] resizeShape(float x , float y , float ratio){
        Shape oldS = selector.select(drawer,x,y);

        if(oldS== null)
            return null;//no shape in selected point

        Shape newS = prototype.createClone(oldS);
        actOperator.resize(newS,ratio);

        drawer.addAct(oldS,newS);
        return new SentShape[]{Server.getSentShape(oldS) , Server.getSentShape(newS)};
    }

    public SentShape changeColor(float x,float y ,String fill,String stroke){
        Shape oldS = selector.select(drawer,x,y);

        if(oldS== null)
            return null;//no shape in selected point

        Shape newS = prototype.createClone(oldS);
        actOperator.recolor(newS,fill,stroke);

        drawer.addAct(oldS,newS);
        return Server.getSentShape(newS);

    }

    public void redo(){
        Act act = drawer.redo();
    }

    public void undo(){
        Act act = drawer.undo();
    }

    public void draw(SentShape shape){
        shape.x2 = shape.length==0? shape.x2:shape.length;
        shape.y2 = shape.width==0? shape.y2:shape.width;
        Shape newS = factory.draw(drawer.getShapeId(),shape.nameOfShape,shape.x,shape.y,shape.x2,shape.y2,shape.x3,shape.y3,shape.fill,shape.stroke);
        if(newS!= null){
            drawer.setShapeId(drawer.getActId()+1);
            drawer.addAct(null,newS);
        }
    }

    public SentShape copy(float currX , float currY , float newX , float newY){
        Shape oldS = selector.select(drawer,currX,currY);
        if(oldS== null)
            return null;//no shape in selected point
        Shape newS = prototype.createClone(oldS);
        actOperator.move(newS,newX,newY);
        newS.setId(drawer.getShapeId());
        drawer.setShapeId(drawer.getShapeId()+1);
        drawer.addAct(null,newS);
        return Server.getSentShape(newS);
    }

    /**
     * Returns an xml document
     */
    public void save(String path , String file) throws Exception{
        path = path+"\\"+file+".xml";
        System.out.println("Path is "+path);
        JAXBContext contextObj = JAXBContext.newInstance(ShapeLists.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        ShapeLists shapes = new ShapeLists();
        shapes.sentShapes = sendShapes();
        marshallerObj.marshal(shapes, new FileOutputStream(path));
//        SentShape shape = new SentShape("Seif" , 10 , 65 ,20 , 48 ,0 ,14 , 0 ,35 ,5 , "ff" ,"dd");
//        marshallerObj.marshal(shape, new FileOutputStream(path));
    }

    public void load(String path) throws Exception{
        path+=".xml";
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(path);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("sentShapes");
        SentShape[] shapes = new SentShape[nodeList.getLength()];

        for(int i = 0; i<nodeList.getLength() ; i++){
            SentShape shape = shapes[i];
            shape = new SentShape();
//            System.out.println(nodeList.item(i).getAttributes().getNamedItem("nameOfShape"));
//            System.out.println(node.getAttributes().item(i).getAttributes().getLength());
            shape.setFill(nodeList.item(i).getAttributes().getNamedItem("fill").getTextContent());
            shape.setLength(Float.parseFloat(nodeList.item(i).getAttributes().getNamedItem("length").getTextContent()));
            shape.setNameOfShape(nodeList.item(i).getAttributes().getNamedItem("nameOfShape").getTextContent());
            shape.setStroke(nodeList.item(i).getAttributes().getNamedItem("stroke").getTextContent());
            shape.setWidth(Float.parseFloat(nodeList.item(i).getAttributes().getNamedItem("width").getTextContent()));
            shape.setX(Float.parseFloat(nodeList.item(i).getAttributes().getNamedItem("x").getTextContent()));
            shape.setX2(Float.parseFloat(nodeList.item(i).getAttributes().getNamedItem("x2").getTextContent()));
            shape.setX3(Float.parseFloat(nodeList.item(i).getAttributes().getNamedItem("x3").getTextContent()));
            shape.setY(Float.parseFloat(nodeList.item(i).getAttributes().getNamedItem("y").getTextContent()));
            shape.setY2(Float.parseFloat(nodeList.item(i).getAttributes().getNamedItem("y2").getTextContent()));
            shape.setY3(Float.parseFloat(nodeList.item(i).getAttributes().getNamedItem("y3").getTextContent()));
            System.out.println(shape);
//            draw(shape);
            shapes[i] = shape;
        }
        load(shapes);

    }
    public void load(SentShape[] shapes){
        System.out.println("Entered here");
        Shape [] arr = new Shape[1000];
        drawer = new Drawer();
        for (int i = 0;i < shapes.length;i++){
            shapes[i].x2 = shapes[i].length==0? shapes[i].x2:shapes[i].length;
            shapes[i].y2 = shapes[i].width==0? shapes[i].y2:shapes[i].width;
            Shape newS = factory.draw(drawer.getShapeId(),shapes[i].nameOfShape,shapes[i].x,shapes[i].y,shapes[i].x2,shapes[i].y2,shapes[i].x3,shapes[i].y3,shapes[i].fill,shapes[i].stroke);
            arr[drawer.getShapeId()] = newS;
            drawer.setShapeId(drawer.getShapeId()+1);
        }
        drawer.setShapes(arr);

    }




    public SentShape[] sendShapes(){
        SentShape[] allShapes = new SentShape[drawer.getShapeId()+1];
        for (int i = 0;i<=drawer.getShapeId();i++){
            if(drawer.getShapes()[i]!=null)
                allShapes[i] = getSentShape(drawer.getShapes()[i]);
        }
        return allShapes;
    }



    private static SentShape getSentShape(Shape shape){
        if(shape == null)
            return null;
        String type = shape.getType();
        String color = shape.getColor();

        float x = 0 , y = 0 , length=0 , width=0 , x2=0 , y2=0 , x3 =0, y3=0 ;
        if(type.equals("square")){
            x = ((Square)shape).getX();
            y = ((Square)shape).getY();
            length = ((Square)shape).getLength();
        }
        else if(type.equals("rectangle")){
            x = ((Rectangle)shape).getX();
            y = ((Rectangle)shape).getY();
            length = ((Rectangle)shape).getLength();
            width = ((Rectangle)shape).getWidth();
        }
        else if(type.equals("triangle")){
            x = ((Triangle)shape).getX1();
            y = ((Triangle)shape).getY1();
            x2 = ((Triangle)shape).getX2();
            y2 = ((Triangle)shape).getY2();
            x3 = ((Triangle)shape).getX3();
            y3 = ((Triangle)shape).getY3();
        }
        else if(type.equals("line")){
            x = ((Line)shape).getX1();
            y = ((Line)shape).getY1();
            x2 = ((Line)shape).getX2();
            y2 = ((Line)shape).getY2();
        }
        else if(type.equals("circle")){
            x = ((Circle)shape).getX();
            y = ((Circle)shape).getY();
            length = ((Circle)shape).getR();
            width = length;
        }
        else if(type.equals("ellipse")){
            x = ((Ellipse)shape).getX();
            y = ((Ellipse)shape).getY();
            length = ((Ellipse)shape).getRx();
            width = ((Ellipse)shape).getRy();
        }
        return new SentShape(type ,x , y , length , width , 0 ,x2 , y2,x3,y3,shape.getInColor() , shape.getColor() );
    }
}
