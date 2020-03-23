import java.util.Scanner;
import java.util.Vector;
import java.util.Collections;

/**
 * Defines pair object
 */
class Pair {
    Integer key;
    Integer position;

    /**
     * Constructor for a pair
     * @param key REQUIRED Not null
     * @param position REQUIRED not null
     */
    Pair(Integer key, Integer position) {
        this.key = key;
        this.position = position;
    }

    /**
     * getter for key
     */
    public Integer getKey() {
        return key;
    }

    /**
     * setter for key
     */
    public void setKey(Integer key) {
        this.key = key;
    }


    /**
     * getter for position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * setter for position
     */
    public void setPosition(Integer position) {
        this.position = position;
    }
}

public class HeapSelect {
    /*
    public static void main(String[] args) {
        System.out.print("Enter an array of integers: ");
        Scanner input = new Scanner(System.in);
        String inVector = input.nextLine();
        int[] array = getInputVector(inVector);
        System.out.print("Enter an integer: ");
        int k = input.nextInt();

        Vector<Pair> h1 = new Vector<Pair>(10);
        for (int i: array)
            h1.add(new Pair(i, null));
        buildHeap(h1);
        Vector<Pair> h2 = new Vector<Pair>(h1.capacity());

        heapSelect(h1, h2, k);

        System.out.println(h2.get(0).getKey());
    }
    */

    /**
     * Splits the input line in all the different values
     * @param inputLine required as a string of ints divided by one (or more) blank spaces
     * @return an int array containing all the ints from the input
     */
    static int[] getInputVector(String inputLine) {
        String els[] = inputLine.split("\\s+");
        int length = els.length;
        int[] output = new int[length];
        for (int i = 0; i < length; i++) {
            output[i] = Integer.parseInt(els[i]);
        }
        return output;
    }


    /**
     * Selects k-th element in a vector with O(n log n) time complexity
     * @param h1 first vector of Pairs. REQUIRED not empty
     * @param h2 second vector of Pairs. REQUIRED not empty
     * @param k index for the selected element
     */
    public static void heapSelect(Vector<Pair> h1, Vector<Pair> h2, int k){
        Pair root = h1.get(0);
        root.setPosition(0);
        h2.add(root);
        for(int i = 0; i < k - 1; i++) { //k volte
            root = h2.get(0);
            int rootPos = root.getPosition();
            int l = rootPos * 2 + 1;
            int r = rootPos * 2 + 2;
            if(l < h1.size()) {
                Pair left = h1.get(l);
                left.setPosition(l);
                h2.add(left);
            }
            if(r < h1.size()){
                Pair right = h1.get(r);
                right.setPosition(r);
                h2.add(right);
            }
            extract(h2);
        }
    }

    /**
     * USELESS
     */
    public static void setPositions(Vector<Pair> V) {
        int pos = 0;
        for(Pair pair : V)
            pair.setPosition(pos++);
    }


    /**
     * Given a vector of pairs, builds a minHeap
     * @param V the vector of pairs
     */
    public static void buildHeap(Vector<Pair> V) {
        for(int i = V.size()/2 - 1; i >= 0; i--)
            minHeapify(V, V.size(), i);
    }


    /**
     * minHeapify algorithm with O(log n) time complexity
     * @param V the vector of pairs that will be turned into a minHeap
     * @param n size of the vector V. REQUIRED as a minHeap
     * @param i starting index for the algorithm. REQUIRED 0 <= i< vector length
     */
    public static void minHeapify(Vector<Pair> V, int n, int i) {
        int smallest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;

        if(l < n && V.get(l).getKey() < V.get(smallest).getKey())
            smallest = l;
        if(r < n && V.get(r).getKey() < V.get(smallest).getKey())
            smallest = r;
        if(smallest != i) {
            Collections.swap(V, i, smallest);
            minHeapify(V, n, smallest);
        }
    }


    /**
     * Adds an element inside the vector making sure to keep the minHeap valid
     * @param V the minHeap vector
     * @param pair the new Pair that will be added to the minHeap
     */
    public static void insert(Vector<Pair> V, Pair pair) {
        V.add(pair);
        buildHeap(V);
    }


    /**
     * Extracts the upper element from the vector making sure to keep the minHeap valid
     * @param V the minHeap vector. REQUIRED as a minHeap
     */
    public static void extract(Vector<Pair> V) {
        Collections.swap(V, 0, V.size() - 1);
        V.remove(V.size() - 1);
        minHeapify(V, V.size(), 0);
    }
}
