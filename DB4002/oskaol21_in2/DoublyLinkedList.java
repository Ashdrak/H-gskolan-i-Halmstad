import java.util.Iterator;

public class DoublyLinkedList<T extends Comparable<T>> implements Iterable<T> {

    ListNode<T> head;
    ListNode<T> tail;
    int size;

    DoublyLinkedList() { // Skapar en tom dubbel länkad lista
        head = null;
        tail = null;
        size = 0;
    }

    public void checkIfNull(Object value) { //kollar så att man inte försöker stoppa in null

        if (value == null) {
            throw new NullPointerException("Null is not allowed!");
        }
        return;
    }

    public void checkIfNegative(int value) { //kollar så att man inte försöker stoppa in negativa nummer

        if (value < 0) {
            throw new IllegalArgumentException("Negative values is not allowed!");
        }
        return;
    }

    public void add(T t) {
        checkIfNull(t);

        if (isEmpty()) {
            head = new ListNode<T>(t);// skapar en nya nod som head pekar på head
            tail = head;              // tail blir vårt gammla head
            size++;
        } else {                       // stpååar in alla våran noder i den temporära listan
            ListNode<T> tNode = head;
            while (tNode.next != null) {
                tNode = tNode.next;
            }
            tNode.next = new ListNode<T>(t, tNode, null); //skapar en ny nod som vi gör till tail
            tail = tNode.next;
            size++;
        }
    }

    public void add(int index, T t) {
        checkIfNegative(index);

        if (index < 0) { // när index är out of bounds skicka meddelande
            throw new IndexOutOfBoundsException(
                    "The provided index(int index, T t) is not allowed.");
        }

        if (index >= size) { // lägger till i slutet om index är out of bounds
            add(t);
            return;
        }

        if (index == 0) { // index == 0 skappa ny node vid head öka stoleken
            head = new ListNode<T>(t, null, head);
            head.next.prev = head; // kopplar ihop nya head med nästa nod
            size++;
            return;
        }
        ListNode<T> tNode = head; // skapar temporär node
        for (int i = 0; i < index - 1; i++)
            tNode = tNode.next;

        tNode.next = new ListNode<T>(t, tNode, tNode.next);
        tNode.next.next.prev = tNode.next;
        size++;
    }

    public T get(int index) {
        checkIfNegative(index);

        if (isEmpty()) // är index tomt
            return null;

        if (index > size - 1) // är index out of bound
            return null;

        ListNode<T> tNode = head;
        for (int i = 0; i < index; i++) {
            tNode = tNode.next;
        }
        return tNode.value;
    }

    public T getFirst() { // plockar första värdet
        if (isEmpty())
            return null;

        return head.value;
    }

    public T getLast() { // plockar sista värdet
        return tail.value;
    }

    public int remove(T t) { // tar bort alla förekomster av t returnar alla t som tas bort annas t
        checkIfNull(t);
        
        if (isEmpty())
            return 0;

        int count = 0;
        if (head.value.equals(t)) { //om t finns i head, flytta head
            removeFirst();
            count++;
        }

        // skapar nya noder för att gå igenom listan och removar t
        ListNode<T> tNode1 = null;
        ListNode<T> tNode2 = head;
        ListNode<T> tNode3 = tNode2.next;

        while (tNode3 != null) {
            if (tNode2.value.equals(t)) { 
                tNode1.next = tNode3; // men vi särar konektionen mellan mitten noden utav 3 noder
                tNode3.prev = tNode1; // och ersätter mitten noden med den 3:e noden och knyter
                                      // ihop dom med den första och 3.next nod
                tNode2 = tNode3;
                tNode3 = tNode2.next;
                size--;
                count++;
            } else {
                tNode1 = tNode2;
                tNode2 = tNode3;
                tNode3 = tNode3.next;
            }
        }

        if (tail.value.equals(t)) { // tar bort sista värdet
            removeLast();
            count++;
        }
        return count;
    }

    public T remove(int index) {          //tar bort värdet på index och returnerar det
        checkIfNegative(index);

        if (isEmpty())
            return null;

        if (index == 0) 
            return removeFirst();

        if (index == size - 1)
            return removeLast();

        ListNode<T> tNode1 = null;        // skapar nya noder för att gå igenom listan och tar bort värdet på index
        ListNode<T> tNode2 = head;
        ListNode<T> tNode3 = tNode2.next;
        T vNode = null;                  // skapar en nod där vi sparar index värde i
        for (int i = 0; i <= index; i++) { // loopar igenom listan och tar bort index
            if (i == index) {
                tNode1.next = tNode3;
                tNode3.prev = tNode1;

                vNode = tNode2.value;   // sparar index innan den tas bort så att vi kan returna värdet
                tNode2 = tNode3;
                tNode3 = tNode2.next;
            } else {
                tNode1 = tNode2;
                tNode2 = tNode3;
                tNode3 = tNode3.next;
            }
        }
        return vNode;
    }

    public T removeLast() { // tar bort sista värdet i listan och returnerar det
        if (isEmpty())
            return null;

        ListNode<T> tNode = head;
        while (tNode.next != null) { // gör en temp lista och stoppar
            tNode = tNode.next; // in våra noder i den
        }

        if (size == 1) { // Om det enbart finns en nod i listan
            head = null; // Ta bort värdet och returna
            tail = null;
            size--;
            return tNode.value;
        }

        tail = tNode.prev; // gör noden framför den sista till tail
        tNode.prev.next = null; // nya tail pekar på null
        tNode.prev = null; // sätter gammla tail(tNode) till null
        size--; // minskar size på listan
        return tNode.value;
    }

    public T removeFirst() { // tar bort första värdet i listan och rturnerar det
        if (isEmpty())
            return null;

        ListNode<T> tNode = head;
        if (size == 1) { // Om det enbart finns en nod i listan
            head = null; // Ta bort värdet och returna
            tail = null;
            size--;
            return tNode.value;
        }

        head = tNode.next; // gör noden efter den sista till tail
        tNode.prev = null; // sätter gammla head(tNode) till null
        size--; // minskar size på listan
        return tNode.value;
    }

    public boolean isEmpty() { // returnar true om storleken är 0 dvs listan är tom
        return this.size == 0;

    }

    public int size() { // skicka tillbaka storleken på listan
        return size;
    }

    public Iterator<T> iterator() { // går igenom listan
        return new Iterator<T>() {
            ListNode<T> node = head;

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

    public void clear() { // tar bort elementen i listan "tömmer den"
        head = null;
        tail = null;
        size = 0;
    }

    public String toString() { // konverterar listan till stränge
        String string = "(";
        for (T t : this) { // för varje nod i listan stoppa in nod + , i string
            string += t + ", ";
        }

        if (!isEmpty()) // om listan är tom
            string = string.substring(0, string.length() - 2); // tar bort space mellan sista noden i string och )
        string += ")";
        return string;
    }

    // uppgift 2b
    public void reverse() {  // stoppar listan i stackena och vänder på den
        if (isEmpty()) // är index tomt
            return;

        MyStack<T> stack = new MyStack<T>();

        for (T t: this) { //stoppar alla noder i stacken 
            stack.push(t);
        }

        int SizeStack = size;
        this.clear();           //tömmer listan
        for (int i = 0; i < SizeStack; i++) { //stoppar tillbaka alla noder i omvänd ordning
            add(stack.pop());
        }
    }

    // uppgift 3a
    public void addAtFirstSmaller(T t) { //börja bakifrån och leta efter första noden med mindre värde än t. sätt in te bakom den noden
        checkIfNull(t);
        
        ListNode<T> tNode = tail;

        if (isEmpty()) { //är listan tom adda t
            add(t);
            return;
        }
        
        for(int i = 0; i < size; i++) {         //letar igenom listans noder
            if (tNode.value.compareTo(t) <= -1) { //-1 betyder att vi letar efter ett mindre tal
                add(size - i, t);                //säger vilket index vi skall stoppa in add på
                return;
            } 

            if (tNode.prev == null) { // om vi går hela vägen tail till null så stoppar vi in t på index 0
                add(0, t);
                return;
            }
            tNode = tNode.prev;  //skiftar igenom noderna
        }
    }

    public void printNode(int index) {
        
        ListNode<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        String str_prev;
        String str_next;

        try {
			str_prev = String.valueOf(node.prev.value);
		}
		catch(NullPointerException e) {
			str_prev = "null";
		}

        try {
			str_next = String.valueOf(node.next.value);
		}
		catch(NullPointerException e) {
			str_next = "null";
		}

        System.out.println("" + str_prev + " | " + node.value + " | " + str_next);
        return;
    }

}