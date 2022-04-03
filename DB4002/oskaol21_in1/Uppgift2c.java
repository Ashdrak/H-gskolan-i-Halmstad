import java.util.Comparator;

public class Uppgift2c {
    public static <T extends Comparable<T>> MyArrayList<T> findMinMax(MyArrayList<T> list){
        T big = list.get(0);
        T small = list.get(0);
        for (int i = 1; i < list.size; i++) {
            if(list.get(i).compareTo(big) > 0){   
                big = list.get(i);  
            }
            if((list.get(i).compareTo(small) < 0)){
                small = list.get(i);
            }
        }
        MyArrayList<T> pair = new MyArrayList<>();
        pair.add(small);
        pair.add(big);
        return pair;
    }

    public static <T extends Comparable<T>> MyArrayList<T> findMinMax(MyArrayList<T> list, Comparator<T> c){
        T big = list.get(0);
        T small = list.get(0);
        for (int i = 1; i < list.size; i++) {
            if(c.compare(list.get(i),big) > 0){   
                big = list.get(i);  
            }
            if(c.compare(list.get(i),small) < 0){
                small = list.get(i);
            }
        }
        MyArrayList<T> pair = new MyArrayList<>();
        pair.add(small);
        pair.add(big);
        return pair;
    }
}