import java.util.Arrays;
import java.util.Iterator;

public class DataBuffer<T> implements Iterable<T> {
   
    int bufferSize;
    int size;
    int front;
    int back;
    T[] a;

    public DataBuffer(int bufferSize){ //Skapa en databuffert med bufferstorlek bufferSize
        if(bufferSize < 1) //stoppar oss ifrån att stoppa in 0 eller null i bufferSize
            throw new IllegalAccessError("The Buffer can't have a size that is lesser then 1");

        this.bufferSize = bufferSize;   //sätter storleken på bufferSize, antal element och vilket index front och back börjar på
        this.size = 0;
        this.front = 0;
        this.back = -1;
        this.a = (T[])new Comparable[bufferSize];
    }

    public synchronized void enqueue(T t){ //Lägg till t i bufferten
        if(bufferSize == size)
            throw new IllegalAccessError("The buffer is full and needs more space to be able to add anymore");

        back = (back + 1)%bufferSize;
        a[back] = t;
        size++;
    }

    public synchronized T dequeue(){ // tar bort första elementet i bufferten
        if(isEmpty()) //kollar så att bufferten inte är tom
            return null;

        T  element = a[front]; // stoppar front in i element 

        if(size == 1){  //dequeue om det bara finns 1 element 
            back = -1;
            front = 0;
            size--;
            return element;
        }
        
        front = (front + 1)%bufferSize;
        size--;
        return element;
    }

    public void changeBufferSize(int newBufferSize){    //Ändrar kapaciteten på bufferten till newBufferSize. Om newBufferSize är minder än den
                                                        //nuvarande kapaciteten så slängs eventuella värden som är sist i bufferten som inte får plats.
        
        if(newBufferSize < 1) //försöker man sätta newBufferSize till 0 så säger den ifrån
            throw new IllegalArgumentException("The buffer size can't be less then 1!");

        if(newBufferSize == bufferSize) //är newBuffersize lika stor som bufferSize bara returna
            return;

        if(newBufferSize < bufferSize){ // är newBufferSize mindre än bufferSize så släng dom sista värderna i bufferSize
            T[] Temp_Array = (T[])new Comparable[newBufferSize]; //gör en ny array för att spara elementen från front till back 
            int Temp_index = 0;
            size = 0;
            back = -1;
            for(int i = front; i < newBufferSize; i++){ //stoppar in elementen ifrån front till back
                Temp_Array[Temp_index] = a[i%bufferSize];
                Temp_index++;
                size++;
                back++;
            }
            front = 0; //sätter front på index 0
            a = Temp_Array;  // sätter vår temp array till våran array a
            return;
        }

        a = Arrays.copyOf(a, newBufferSize); //kopierar våran newBufferSize i a
        bufferSize = newBufferSize; //sätter att våran newBufferSize till bufferSize
        return;
        
    }

    public boolean isFull(){ //är bufferten full ?
        return size == a.length; 
    }

    public boolean isEmpty(){ // är buffeten tom ?
        return size == 0;
    }

    public int size(){ //Returnerar antalet element i bufferten
        return size; 
    }

    public int bufferSize(){ // returnerna kapaciteten på bufferten dvs hur mycket den kan innehålla
        return bufferSize; 
    }

    public Iterator<T> iterator() { // Returnerar en iterator

        return new Iterator<T>() {

            int index = front;
            int count = 0;
            public boolean hasNext() {

                return count < size;
            }

            public T next() {

                count++;
                return a[index++ % bufferSize];
            }
        };
    }

    //Test av dataBuffer
    public void printBuffer() {
        // custom class

        String s = "(";
        for (T t : a) {
            s += t + ", ";
        }

        if (!isEmpty())
            s = s.substring(0, s.length() - 2);
        s += ")";
        System.out.println(s);
        System.out.println("back: " + this.back);
        System.out.println("front: " + this.front);
        System.out.println("size: " + this.size);
        System.out.println();
    }

    public static void main(String[] args) {
        DataBuffer<Integer> buffer1 = new DataBuffer<>(8);
        buffer1.enqueue(4);
        buffer1.enqueue(7);
        buffer1.enqueue(2);
        buffer1.enqueue(9);
        buffer1.enqueue(9);
        buffer1.enqueue(2);
        buffer1.enqueue(9);
        buffer1.enqueue(9);

        buffer1.dequeue();
        buffer1.dequeue();
        buffer1.dequeue();
        buffer1.dequeue();
        buffer1.dequeue();
        buffer1.dequeue();
        buffer1.dequeue();
        buffer1.dequeue();

        
        buffer1.printBuffer();
        buffer1.enqueue(9);
        buffer1.printBuffer();
        System.out.println(buffer1.bufferSize());

    }
}
