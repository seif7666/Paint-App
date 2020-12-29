package eg.edu.alexu.csd.oop.draw.cs23.shapes;

public abstract class Polygen extends Shape{
    private Pair<Float, Float>[] vertices;

    public Polygen(String type,int id,Pair<Float, Float>[] vertices) {
        this.vertices = vertices;
        this.setId(id);
        this.setType(type);
    }

    public Pair<Float, Float>[] getVertices() {
        return vertices;
    }

    public void setVertices(Pair<Float, Float>[] vertices) {
        this.vertices = vertices;
    }

}
