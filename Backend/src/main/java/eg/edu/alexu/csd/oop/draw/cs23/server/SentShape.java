package eg.edu.alexu.csd.oop.draw.cs23.server;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SentShape {
    String nameOfShape;
    float x;
    float y;
    float length; //radX
    float width; //radY
    float angle;

    float x2;
    float y2;

    float x3;
    float y3;


    String fill;
    String stroke;

    public SentShape() {
    }

    public SentShape(String nameOfShape, float x, float y, float length, float width, float angle, float x2, float y2, float x3, float y3, String fill, String stroke) {
        this.nameOfShape = nameOfShape;
        this.x = x;
        this.y = y;
        this.length = length;
        this.width = width;
        this.angle = angle;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.fill = fill;
        this.stroke = stroke;
    }

    @Override
    public String toString() {
        String sb = "SentShape{" + "nameOfShape='" + nameOfShape + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", length=" + length +
                ", width=" + width +
                ", angle=" + angle +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", x3=" + x3 +
                ", y3=" + y3 +
                ", fill='" + fill + '\'' +
                ", stroke='" + stroke + '\'' +
                '}';
        return sb;
    }

    @XmlAttribute
    public String getNameOfShape() {
        return nameOfShape;
    }

    public void setNameOfShape(String nameOfShape) {
        this.nameOfShape = nameOfShape;
    }
    @XmlAttribute
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    @XmlAttribute
    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    @XmlAttribute
    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }
    @XmlAttribute
    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    @XmlAttribute
    public float getX2() {
        return x2;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    @XmlAttribute
    public float getY2() {
        return y2;
    }

    public void setY2(float y2) {
        this.y2 = y2;
    }

    @XmlAttribute
    public float getX3() {
        return x3;
    }

    public void setX3(float x3) {
        this.x3 = x3;
    }

    @XmlAttribute
    public float getY3() {
        return y3;
    }

    public void setY3(float y3) {
        this.y3 = y3;
    }

    @XmlAttribute
    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    @XmlAttribute
    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

}
