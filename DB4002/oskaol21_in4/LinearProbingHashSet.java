import java.util.LinkedList;

public class LinearProbingHashSet<Key> {
    
    HashElement<Key>[] array; //array
    int m; //storleken
    int n; //antal element 

    public LinearProbingHashSet(int m){ //Skapa en instans där kapaciteten, eller arraystorleken är m
        this.array = new HashElement[m];
        this.m = m;
        this.n = 0;
    }

    public LinearProbingHashSet(){  //Skapa en instans där kapaciteten m är satt till ett default-värde
        this(10);
    }

    public int hash(Key key){ //Returnera hashvärdet för key;
        return (key.hashCode() & 0x7fffffff) % m; 
    }

    public int getCapacity(){ //Returnera arraystorleken m
        return this.m;
    }

    public void insert(Key key){ //Lägg till key i hashtabellen eller öka förekomsten med 1 om den redan finns

        if (key == null)
            throw new IllegalArgumentException("Null is not allowed to put in");

        if(loadFactor() >= 0.5)
            resize(m * 2);
        
        else if(loadFactor() <= 1.0 / 8)
            resize(m / 2);

        insertNoResize(key, 1);
        return;
    }

    public boolean contains(Key key){ //Finns nyckeln key
        int index = hash(key);

        while(array[index] != null){
            if(array[index].key.equals(key)){
                return true;
            }
            index = updateIndex(index);
        }
        return false;
    }

    public void decrease(Key key){ //Minska ner förekomsten för key med 1. Om förekomsten blir 0, så tas nyckeln bort ifrån arrayen
        int index = hash(key);

        while(array[index] != null){
            if(array[index].key.equals(key)){
                array[index].decrement();
                if(array[index].counter == 0){
                    delete(key);
                }
            }

            index = updateIndex(index);
        }

        return;
    }

    public void delete(Key key){  //Ta bort key
        int index = hash(key);

        while(array[index] != null){  //letar efter nyckeln
            if(array[index].key.equals(key)){   //hittar vi nyckeln
                array[index] = null;
                resize(m);           //ändrar storlek då vi taggit bort nyckeln
            }
            index = updateIndex(index);
        }
        return;
    }

    public Iterable<Key> keys(){ //Returnera en Iterable<Key> objekt där itereringen sker över nycklarna i förekomstordning. 
                                //Den mest förekommande nyckeln kommer först, den näst mest förekommmande kommer sen osv.
        
        LinkedList<Key> list = new LinkedList<Key>();

        for(int i = 0; i < m; i++){
            if(array[i] != null)
                list.add(array[i].getKey());
        }
        return list;
    }

    //Custom method
    private double loadFactor(){
        return (double) n / m;
    }

    public int updateIndex(int i){ //uppdaterar index
        return ++i % m;
    }

    public void resize(int newSizeM){  //reziar vår hashtabell
        LinearProbingHashSet temp = new LinearProbingHashSet<>(newSizeM);
        
        for(int i = 0; i < m; i++){
            if(array[i] != null){
                temp.insertNoResize(array[i].key, array[i].getFrequencey()); //
            }
        }
        this.m = temp.m;
        this.array = temp.array;
        return;
    }

    private void insertNoResize(Key key, int counter){ //omorganiserar hashtabellen ifall något ligger på vårt index eller att vi taggit bor det som liggat på indexet vi vill använda
        int index = hash(key); //skapar en int index där vi stoppar in hash(key) 

        while(array[index] != null){  //så länge index ej är null
            if(array[index].key.equals(key)){ //jämför nyckeln i index emot nyckel
                array[index] = new HashElement<Key>(key, array[index].getFrequencey()); // 
                array[index].increment(); //ökar föremkomsten med 1 
            }

            index = updateIndex(index); 
        }

        array[index] = new HashElement<>(key, counter); //
        n++; //ökar antal element med 1 
        return;
    }

    @Override
    public String toString() {
        String string = "{";
        for (HashElement<Key> element : this.array) {
            string += element + ", ";
        }
        string += "}";
        return string;
    }

    public static void main(String[] args) {
        LinearProbingHashSet<Character> hashSet1 = new LinearProbingHashSet<>(10);
        // hashSet1.a[3] = new HashElement<Character>('b');
        // hashSet1.insert('b');
        // System.out.println(hashSet1.hash('c'));
        // System.out.println(hashSet1.hash('m'));

        // System.out.println(hashSet1.hash(hashSet1.a[3].key));
        // hashSet1.insert('c');
        
        // hashSet1.insert('m');
        // System.out.println(hashSet1.hash('m'));
        // System.out.println("arrsize: " + hashSet1.m);
        // System.out.println(hashSet1);

        hashSet1.insert('m');
        // System.out.println(hashSet1.hash('m'));
        // System.out.println("arrsize: " + hashSet1.m);
        // System.out.println("nr.elements: " + hashSet1.n);
        // System.out.println(hashSet1);

        hashSet1.insert('m');
        // System.out.println(hashSet1.hash('m'));
        // System.out.println("arrsize: " + hashSet1.m);
        // System.out.println("nr.elements: " + hashSet1.n);
        // System.out.println(hashSet1);

        hashSet1.insert('b');
        // System.out.println(hashSet1.hash('m'));
        // System.out.println("arrsize: " + hashSet1.m);
        // System.out.println("nr.elements: " + hashSet1.n);
        // System.out.println(hashSet1);

        hashSet1.insert('c');
        // System.out.println(hashSet1.hash('m'));
        // System.out.println("arrsize: " + hashSet1.m);
        // System.out.println("nr.elements: " + hashSet1.n);
        System.out.println(hashSet1);

        hashSet1.insert('v');
        // System.out.println(hashSet1.hash('m'));
        // System.out.println("arrsize: " + hashSet1.m);
        // System.out.println("nr.elements: " + hashSet1.n);
        System.out.println(hashSet1);

        System.out.println(hashSet1.hash('v'));
        hashSet1.decrease('m');
        hashSet1.decrease('m');
        hashSet1.decrease('m');
        System.out.println(hashSet1.hash('v'));
        System.out.println(hashSet1);

        
        hashSet1.delete('b');
        // System.out.println(hashSet1);
        hashSet1.delete('v');
        // System.out.println(hashSet1);
        hashSet1.delete('c');



        // System.out.println("arrsize: " + hashSet1.m);
        // System.out.println("nr.elements: " + hashSet1.n);
        System.out.println(hashSet1);

        // for (Character element : hashSet1.keys()) {
        // System.out.println(element);
        // }



    }
}
