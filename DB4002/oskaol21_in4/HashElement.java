public class HashElement<Key> implements Comparable<HashElement<Key>> {

    Key key;
    int counter;

    public HashElement(Key key){     //Skapar ett HashElement med nyckel och förekomst 1
        this(key, 1);
    }

    public HashElement(Key key, int counter){  // Skapar ett HashElement med nyckel key och förekomsten counter
        this.key = key; 
        this.counter = counter;
    }

    public void increment(){   //Ökar förekomst med 1
        this.counter++;
    }

    public void decrement() {  //Minskar föremomst med 1
        if(this.counter > 1)
            counter--;
        else 
            throw new IllegalStateException("Counter can't be decrement below 1.");
    }

    public int getFrequencey(){ //Returnerar förekomsten
        return this.counter;
    }

    public Key getKey(){ //Returnerar nyckeln
        return this.key;
    }

    public void setKey(Key key){ //Sätt nyckeln till key
        this.key = key;
    }

    public int compareTo(HashElement<Key> that){ //Jämför förekomsten för this med förekomsten för that

        if(this.counter > that.counter)
            return 1;
        
        if(this.counter < that.counter)
            return -1;

        return 0;
    }
}