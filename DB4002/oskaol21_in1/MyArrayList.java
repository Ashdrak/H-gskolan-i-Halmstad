import java.util.*;

public class MyArrayList<T> implements Iterable<T>{

    private T[] myList;
    int size;
    public char[] set;

    public MyArrayList(){
        myList = (T[]) new Object[10];
        size = 0;
    }

    public boolean add(T t){
        if(size == myList.length){
            Object[] temp = new Object[myList.length];
            for(int i = 0; i < myList.length; i++){
                temp[i] = myList[i];
            }

            myList = (T[]) new Object[myList.length*2];


            for(int i = 0; i < myList.length; i++){
                myList[i] = (T) temp[i];
            }
        }
        myList[size] = t;
        size++;
        return true;
    }

    public void add(int index, T t){
        myList[index] = t;
    }

    public boolean contains(T t){
        for(int i = 0; i < myList.length; i++){
            if(myList[i] == t)
                return true;
        }
        return false;
    }

    public T get(int index){
        if (!contains(myList[index]))
            return null;
            
        return myList[index];
    }

    public int indexOf(T t){
        for(int i = 0; i < myList.length; i++){
            if(myList[i] == t)
                return i;
        }
        return -1;
    }

    public T Remove(int index){
        if(index <= size && index >= 0){
            myList[index] = null;
            for(int i = index+1; i < myList.length-1; i++){
              myList[i-1] = myList[i];
            }
            size = size - 1;
        }
        return null;
    }

    public boolean remove(T t){
        for(int i = 0; i < myList.length; i++){
            if(myList[i] == t){
                Remove(i); 
                return true;
            }
        }
        return false;
    }

    public int removeAll(T t){
        int count = 0;
        for(int i = 0; i < myList.length - 1; i++){
            while(myList[i] == t){
                myList[i] = null;
                count++;
                for(int j = i; j < myList.length-1; j++){
                    myList[j] = myList[j+1];
                }
            }
        }
        size = size - count;
        return count;
    }

    public T set (int index, T t){
        T temp = myList[index];
        myList[index] = t;

        return temp;
    }

    public boolean isEmpty(){
        for(int i = 0; i < myList.length; i++){
            if(myList[i] == null)
            return true;
        }
        return false;
    }

    public int size(){
        return(myList.length);
    }

    public Iterator<T> iterator(){

        return new Iterator<T>(){
            int index = 0;
            public boolean hasNext(){
                return index < size;
            }
            public T next() {
                return myList[index++];
            }
        };
    }

    public void clear(){
        for (int i = 0; i < myList.length; i++){
            myList[i] = null;
        }
    }

    public String toString(){
        String string = "[";
        int counter = 0;
        for (T element : myList) {
          if (element != null) {
            string += element;
    
            if (counter < size - 1 && size > 1) {
              string += ", ";
            }
          }
          counter++;
        }
        string += "]";
        return string;

    }
}