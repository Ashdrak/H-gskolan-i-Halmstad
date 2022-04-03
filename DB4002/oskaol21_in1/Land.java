import java.util.Comparator;

public class Land implements Comparable<Land>{

    String name;
    String capital;
    int amountOfCitizens;

    Land(String name, String capital, int amountOfCitizens){
        this.name = name;
        this.capital = capital;
        this.amountOfCitizens = amountOfCitizens;
    }

    public String toString() {
        String string = "Capital:" + this.capital + ", Name:" + this.name + ", Amount" + " of" + 
        " citizens:" + this.amountOfCitizens;
        return string;
    }

    public int compareTo(Land land) {
        if(this.amountOfCitizens < land.amountOfCitizens) 
        return -1;

        else if(this.amountOfCitizens == land.amountOfCitizens)
        return 0;

        else return 1;
    }

    public static void main(String[] args){
        Land land1 = new Land("Sweden","Stockholm",10452326);
        Land land2 = new Land("Norway","Oslo",5425270);
        Land land3 = new Land("Belgium","Brussel",11555997);
        Land land4 = new Land("Denmark","Copenhagen",5785864);
    }
}

class LandCapital implements Comparator<Land>{
    public int compare(Land land1, Land land2){
        int a = land1.capital.compareTo(land2.capital);
        if (a > 0){
            return 1;
        }

        else if (a == 0){
            return 0;
        }
        return -1;
    }
}

class LandCitizens implements Comparator<Land>{
    public int compare(Land land1, Land land2){
        if (land1.amountOfCitizens > land2.amountOfCitizens){
            return 1;
        }

        else if (land1.amountOfCitizens == land2.amountOfCitizens){
            return 0;
        }
        return -1;
    }
}

class LandName implements Comparator<Land>{
    public int compare(Land land1, Land land2){
        int a = land1.name.compareTo(land2.name);
        if (a > 0){
            return 1;
        }

        else if (a == 0){
            return 0;
        }
        return -1;
    }
}