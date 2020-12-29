package eg.edu.alexu.csd.oop.draw.cs23.shapes;

public class Pair<K, V> {

    private K First;
    private V Second;

    public static <K, V> Pair<K, V> createPair(K First, V Second) {
        return new Pair<K, V>(First, Second);
    }

    public Pair(K element0, V Second) {
        this.First = element0;
        this.Second = Second;
    }

    public K getFirst() {
        return First;
    }

    public V getSecond() {
        return Second;
    }

    public void setFirst(K First){
        this.First = First;
    }

    public void setSecond(V Second){
        this.Second = Second;
    }
}
