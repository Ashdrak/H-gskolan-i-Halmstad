import java.util.Iterator;
import java.util.Stack;

public class TreeSetCounter<T extends Comparable<T>> implements Iterable<T> {
    
    int size;
    Node<T> root;
    int maxFreq; 
    Stack<Node<T>> stack;

    public TreeSetCounter(){ //Skapa en treeSetCounter
        this.size = 0;
        this.root = new Node<>();
        this.maxFreq = 1;
    }

    public void add(T t){   //Lägg till t.

    }

    public void clear(){  //Tar bort alla element
        size = 0;
        root = null;
    }

    public int getMaxFrequency(){   //Returnerar antalet förekomster(counter-värden) för det värde som förekommer mest
        return maxFreq;
    }

    public boolean contains(T t){  //Finns t i mängden

    }

    public boolean isEmpty(){ //Finns det några element?
        return size == 0;
    }

    public int size(){ //Returnerar antalet element.
        return size;
    }

    public int counter(T t){  //Returnerar antalet förekomster av t (counter-värdet)
       
    }


    public Iterator<T> iterator(){  //Retunerar en iterator som itererar i den sorterade ordningen över elementen i mängden
        return new treeIterator();
    }

    public class treeIterator implements Iterator<T> {
        Stack<Node> stack = new Stack<>();

        treeIterator() {
            pushLeft(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public T next() {
            Node<T> node = stack.pop();
            pushLeft(node.childRight);
            return (T) node;
        }

        public void pushLeft(Node node) {
            while(node != null) {
                stack.push(node);
                node = node.childLeft;
            }
        }
    }
}
