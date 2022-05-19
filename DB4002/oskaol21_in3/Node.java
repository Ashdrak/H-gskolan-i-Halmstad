public class Node <T extends Comparable<T>> {
    T value;
    Node childLeft;
    Node childRight;
    int counter;

    public Node(T value, Node childLeft, Node childRight){
        this.value = value;
        this.childLeft = childLeft;
        this.childRight = childRight;
        this.counter = 1;
    }

    public Node(T value){
        this(value, null, null);
    }

    public Node(){
        this(null, null, null);
    }
}
