import java.util.*;

public class Graph {

    public static ArrayList<Graph> nodeArray = new ArrayList<>();
    public static ArrayList<Graph> arrayCopy = new ArrayList<>();
    ArrayList<Graph> neighbour = new ArrayList<>();
    ArrayList<Graph> network = new ArrayList<>();

    boolean visited;
    int iX;
    int iY;
    int iR;
    int data;

    public Graph(int iX, int iY, int iR, int data) {// Konstuktorn för grafen
        this.iX = iX;
        this.iY = iY;
        this.iR = iR;
        this.data = data;
        this.neighbour = new ArrayList<>();
    }

    public Graph(String string) {
    }

    public void addNeighbours(Graph neighbourNode) {
        this.neighbour.add(neighbourNode);
    }

    public void setNeighbours(ArrayList<Graph> neighbours) {
        this.neighbour = neighbours;
    }

    public List<Graph> getNeighbours() {
        return neighbour;
    }

    public int amountOfNetworks() {// kollar hur många nätvärk det är i området
        int counter = 0;
        arrayCopy.addAll(nodeArray);

        for (int i = 0; i < arrayCopy.size(); i++) {
            DFS(arrayCopy.get(i));

            if (network.size() >= 1) 
                counter++;

            for (int k = 0; k < network.size(); k++) {
                for (int j = 1; j < arrayCopy.size(); j++){
                    if (network.contains(arrayCopy.get(j))) 
                        arrayCopy.remove(j);
                }
            }
        }

        return counter;
    }

    public void DFS(Graph other){//Djupet först sökning (Deapth First Search)
        Stack<Graph> stack = new Stack<Graph>(); //Skapar en stack
        stack.add(other);

        while(!stack.isEmpty()){// När stacken inte är tom så popar vi värden, har vi inte besökt värdet så addar vi den till nätverket
            Graph value = stack.pop();

            if(!value.visited){
                network.add(value);
                value.visited = true;
            }

            List<Graph> neighbours = value.getNeighbours();//Skapar en ny lista med värdena ifrån Neigbours 
            for(int i = 0; i < neighbours.size(); i++){
                Graph temp = neighbours.get(i);//Använder oss av Graph klassen för att göra en temporär value och hämtar värdena ifrån Nighbours

                if(temp != null && !temp.visited)//Om temp värdet inte är null och inte har besökts innan så lägger vi det på stacken så vi skapade tiodigare
                    stack.add(temp);
            }
        }
    }

    public boolean isNetwork(Graph other) {// Kollar om nätvärket är uppkopplad eller inte. Använder DFS methoden
        DFS(other);
        return network.contains(other);
    }

    public double distanceBetween(Graph other) {// Kollar distansen mellan två noder
        double currD;
        double furtherD = 0;
        double dx;
        double dy;

        DFS(other);

        for (int i = 1; i < network.size(); i++) {// For loop för network.size
            DFS(other);

            dx = network.get(i).iX - other.iX;
            dy = network.get(i).iY - other.iY;

            currD = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

            if (currD > furtherD) 
                furtherD = currD;

        }

        return furtherD;
    }

    public int BFS(Graph one, Graph other) { // Bredden först sökning (Breadth First Search)
        LinkedList<Integer> queue = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
       
        int jump = 0;
        int[] prev = new int[nodeArray.size()];

        if (one.data == other.data) // Om duplicerad , så är avståndet noll
            return jump;

        queue.add(one.data); // Lägger till den valda nodens ID till kön

        for (int i = 0; i < nodeArray.size(); i++) {// alla element i föregående sätts till -1 för att representera att dom ej är besökta
            prev[i] = -1;
        }

        while (queue.size() != 0) { // Går igenom kön tills dess att den är tom 
            int curr = queue.poll();

            for (Graph node : nodeArray.get(curr).getNeighbours()) {// Sätter in första elementet ifrån kön till curr och tar bort värdet ifrån kön
                int n = node.data; 

                if (prev[n] == -1) { //är noden = -1 så stoppar vi in current på dess plats
                    prev[n] = curr;

                    if (n == other.data) {//n blir ID för varje Node

                        while (n != one.data) {
                            res.addFirst(n);
                            n = prev[n];
                            jump++;
                        }

                        return jump;
                    }

                    queue.add(n);
                }
            }
        }

        return jump;
    }

}
