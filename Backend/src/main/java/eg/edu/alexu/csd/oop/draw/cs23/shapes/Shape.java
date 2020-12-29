package eg.edu.alexu.csd.oop.draw.cs23.shapes;

abstract public class Shape {
    private int id;
    private String color;
    private String inColor;
    private String type;

    public void setColor(String color) {
        this.color = color;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setInColor(String incolor) {
        this.inColor = incolor;
    }

    public String getInColor() {
        return inColor;
    }


}
