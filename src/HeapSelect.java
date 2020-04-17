import java.util.Vector;
import java.util.Scanner;
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
    public static void main(String[] args) {
        System.out.print("Enter an array of integers: ");
        Scanner input = new Scanner(System.in);
        String inVector = input.nextLine();
        int[] array = getInputVector(inVector);
        System.out.print("Enter an integer: ");
        int k = input.nextInt();

        heapSelect(array, k);
    }

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

    public static void heapSelect(int[] array, int k){
        Vector<Pair> h1 = new Vector<Pair>(10);
        for (int i : array)
            h1.add(new Pair(i, null));
        if (k < h1.size()/2)
            buildMinHeap(h1);
        else
            buildMaxHeap(h1);
        Vector<Pair> h2 = new Vector<Pair>(k);
        Pair root = h1.get(0);
        root.setPosition(0);
        h2.add(root);

        if (k <= h1.size()/2)
            minHeapSelect(h1, h2, k);
        else
            maxHeapSelect(h1, h2, k);

        System.out.println(h2.get(0).getKey());
    }

    /**
     * Selects k-th element in a vector with O(k log k) time complexity
     * @param h1 first vector of Pairs. REQUIRED not empty
     * @param h2 second vector of Pairs. REQUIRED not empty
     * @param k index for the selected element
     */
    public static void minHeapSelect(Vector<Pair> h1, Vector<Pair> h2, int k){
        Pair left, right, root;
        for(int i = 0; i <= k-1; i++) {
            root = h2.get(0);
            int rootPos = root.getPosition();
            int l = rootPos * 2 + 1;
            int r = rootPos * 2 + 2;
            extract(h2, true);
            if(l < h1.size()) {
                left = h1.get(l);
                left.setPosition(l);
                h2.add(left);
                while(l > 0 && h2.get(l).getKey() < h2.get((l - 1) / 2).getKey()) {
                    Collections.swap(h2, l, (l - 1) / 2);
                    l = (l - 1) / 2;
                }
            }
            if(r < h1.size()){
                right = h1.get(r);
                right.setPosition(r);
                h2.add(right);
                while(r > 0 && h2.get(r).getKey() < h2.get((r - 1) / 2).getKey()) {
                    Collections.swap(h2, r, (r - 1) / 2);
                    r = (r - 1) / 2;
                }
            }
        }
    }

    /**
     * Selects k-th element in a vector with O(k log k) time complexity
     * @param h1 first vector of Pairs. REQUIRED not empty
     * @param h2 second vector of Pairs. REQUIRED not empty
     * @param k index for the selected element
     */
    public static void maxHeapSelect(Vector<Pair> h1, Vector<Pair> h2, int k){
        Pair left, right, root;
        for(int i = h1.size()-1; i > k-1; i--) {
            root = h2.get(0);
            int rootPos = root.getPosition();
            int l = rootPos * 2 + 1;
            int r = rootPos * 2 + 2;
            extract(h2, false);
            if(l < h1.size()) {
                left = h1.get(l);
                left.setPosition(l);
                h2.add(left);
                while(l > 0 && h2.get(l).getKey() > h2.get((l - 1) / 2).getKey()) {
                    Collections.swap(h2, l, (l - 1) / 2);
                    l = (l - 1) / 2;
                }
            }
            if(r < h1.size()){
                right = h1.get(r);
                right.setPosition(r);
                h2.add(right);
                while(r > 0 && h2.get(r).getKey() > h2.get((r - 1) / 2).getKey()) {
                    Collections.swap(h2, r, (r - 1) / 2);
                    r = (r - 1) / 2;
                }
            }
        }
    }

    /**
     * Given a vector of pairs, builds a minHeap
     * @param vector the vector of pairs
     */
    public static void buildMinHeap(Vector<Pair> vector) {
        for(int i = vector.size()/2 - 1; i >= 0; i--) {
            minHeapify(vector, i);
        }
    }

    /**
     * Given a vector of pairs, builds a maxHeap
     * @param vector the vector of pairs
     */
    public static void buildMaxHeap(Vector<Pair> vector) {
        for(int i = vector.size()/2 - 1; i >= 0; i--) {
            maxHeapify(vector, i);
        }
    }

    /**
     * maxHeapify algorithm with O(log n) or O(log k) time complexity
     * @param vector the vector of pairs that will be turned into a minHeap
     * @param i starting index for the algorithm. REQUIRED 0 <= i< vector length
     */
    private static void maxHeapify(Vector<Pair> vector, int i) {
        int n = vector.size();
        int greatest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;

        if(l < n && vector.get(l).getKey() > vector.get(greatest).getKey())
            greatest = l;
        if(r < n && vector.get(r).getKey() > vector.get(greatest).getKey())
            greatest = r;
        if(greatest != i) {
            Collections.swap(vector, i, greatest);
            maxHeapify(vector, greatest);
        }
    }


    /**
     * minHeapify algorithm with O(log n) or O(log k) time complexity
     * @param vector the vector of pairs that will be turned into a minHeap
     * @param i starting index for the algorithm. REQUIRED 0 <= i< vector length
     */
    public static void minHeapify(Vector<Pair> vector, int i) {
        int n = vector.size();
        int smallest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;

        if(l < n && vector.get(l).getKey() < vector.get(smallest).getKey())
            smallest = l;
        if(r < n && vector.get(r).getKey() < vector.get(smallest).getKey())
            smallest = r;
        if(smallest != i) {
            Collections.swap(vector, i, smallest);
            minHeapify(vector, smallest);
        }
    }

    /**
     * Extracts the upper element from the vector making sure to keep the minHeap valid
     * @param vector the heap vector. REQUIRED as a minHeap or a maxHeap
     * @param min
     */
    public static void extract(Vector<Pair> vector, boolean min) {
        Collections.swap(vector, 0, vector.size() - 1);
        vector.remove(vector.size() - 1);
        if(min)
            minHeapify(vector, 0);
        else
            maxHeapify(vector, 0);
    }
}
