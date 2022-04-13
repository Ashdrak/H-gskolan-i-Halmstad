import java.util.Iterator;

public class MyStack<T extends Comparable<T>> implements Iterable<T> {
    
    DoublyLinkedList<T> stack;      //"kallar in DoubleLinkedList"

    public MyStack() {              // skapar en ny stack ifrån DoubleLinkedLIst
        stack = new DoublyLinkedList<T>();
    }

    public boolean isEmpty() {      // kollar om stacken är tom
        return stack.isEmpty();
    }

    public T peek() {
        return stack.getLast();
    }

    public T pop() {               //plockar ut och removar översta värdet i listan
        return stack.removeLast();
    }


    public void push(T t) {       // lägger till värdet överst i listan
        stack.add(t);
    }

    public Iterator<T> iterator() { // går igenom listan
        return new Iterator<T>() {
            ListNode<T> node = stack.head;

            public boolean hasNext() { // returnar noderna tills den stöter på null
                return node != null;
            }

            public T next() { // returnerar värdet på varje nod
                ListNode<T> tNode = node;
                node = node.next;
                return tNode.value;
            }
        };        
    }
}
