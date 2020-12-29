package eg.edu.alexu.csd.oop.draw.cs23.server;
public class Operation {

    public String operation;
    public float x1;
    public float y1;

    public float x2;
    public float y2;

    public float newLen;
    public float newWid;

    String fill;
    String stroke;

    float ratio;


    public Operation(String operation, float x1, float y1, float x2, float y2, float newLen, float newWid, String fill, String stroke, float ratio) {
        this.operation = operation;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.newLen = newLen;
        this.newWid = newWid;
        this.fill = fill;
        this.stroke = stroke;
        this.ratio = ratio;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Operation{");
        sb.append("operation='").append(operation).append('\'');
        sb.append(", x1=").append(x1);
        sb.append(", y1=").append(y1);
        sb.append(", x2=").append(x2);
        sb.append(", y2=").append(y2);
        sb.append(", newLen=").append(newLen);
        sb.append(", newWid=").append(newWid);
        sb.append(", fill='").append(fill).append('\'');
        sb.append(", stroke='").append(stroke).append('\'');
        sb.append(", ratio=").append(ratio);
        sb.append('}');
        return sb.toString();
    }
}

