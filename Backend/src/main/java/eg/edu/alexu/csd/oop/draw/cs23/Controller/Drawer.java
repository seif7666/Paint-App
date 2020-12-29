package eg.edu.alexu.csd.oop.draw.cs23.Controller;


import eg.edu.alexu.csd.oop.draw.cs23.shapes.*;
public class Drawer {
    private Act[] acts;
    private Shape[] shapes;
    private int actId;
    private int shapeId;

    public Drawer(){
        this.acts = new Act[1000];
        this.shapes = new Shape[1000];
        this.actId = 0;
        this.shapeId = 0;
    }

    public void addAct(Shape oldS,Shape newS){
        if(oldS!=null)
            shapes[oldS.getId()] = newS;
        else
            shapes[newS.getId()] = newS;
        Act act = new Act(actId,newS,oldS);
        acts[actId++] = act;
        int i = actId;
        while(acts[i++]!=null){
            acts[i]=null;
        }
    }

    public Act undo(){
        if(actId>0) {
            actId--;
            if(acts[actId].getOldShape()!=null)
                shapes[acts[actId].getOldShape().getId()] = acts[actId].getOldShape();
            else
                shapes[acts[actId].getNewShape().getId()] = acts[actId].getOldShape();
            return acts[actId];
        }
        return null;
    }

    public Act redo(){
        if(acts[actId]!=null){
            if(acts[actId].getOldShape()!=null)
                shapes[acts[actId].getOldShape().getId()] = acts[actId].getNewShape();
            else
                shapes[acts[actId].getNewShape().getId()] = acts[actId].getNewShape();
            actId++;
            return acts[actId-1];
        }
        return null;
    }


    public Act[] getActs() {
        return acts;
    }

    public int getActId() {
        return actId;
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public int getShapeId() {
        return shapeId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public void setShapeId(int shapeId) {
        this.shapeId = shapeId;
    }

    public void setShapes(Shape[] shapes) {
        this.shapes = shapes;
    }
}
