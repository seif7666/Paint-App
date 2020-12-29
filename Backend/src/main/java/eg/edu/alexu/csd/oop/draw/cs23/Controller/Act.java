package eg.edu.alexu.csd.oop.draw.cs23.Controller;

import eg.edu.alexu.csd.oop.draw.cs23.shapes.Shape;

public class Act {
    private int id;
    private Shape oldShape;
    private Shape newShape;

    public Act(int id,Shape newShape,Shape oldShape){
        this.newShape = newShape;
        this.oldShape = oldShape;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Shape getNewShape() {
        return newShape;
    }

    public void setNewShape(Shape newShape) {
        this.newShape = newShape;
    }

    public Shape getOldShape() {
        return oldShape;
    }

    public void setOldShape(Shape oldShape) {
        this.oldShape = oldShape;
    }
}
