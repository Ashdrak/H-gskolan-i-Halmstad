public class InsertionSort {
    public static Comparable[] sort(Comparable[] a){  //insättningssortering 
        DoublyLinkedList list = new DoublyLinkedList<>();  //sakapar ny lista 
        Comparable[] sortedArray = new Comparable[a.length]; //sakapar en ny array med längden av a
       
       for(int i = 0; i < a.length; i++){  //itererar över a varje element t i a läggs till först i listan
           list.addAtFirstSmaller(a[i]);  
       }
        
       for(int i = 0; i < a.length; i++){ // itererar över listan och läger respektive element på plats a i listan
           sortedArray[i] = list.get(i);    
       }
        return sortedArray;
    }
}
