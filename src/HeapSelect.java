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
    /*public static void main(String[] args) {
        System.out.print("Enter an array of integers: ");
        Scanner input = new Scanner(System.in);
        String inVector = input.nextLine();
        int[] array = getInputVector(inVector);
        System.out.print("Enter an integer: ");
        int k = input.nextInt();

        Vector<Pair> h1 = new Vector<Pair>(10);
        for (int i: array)
            h1.add(new Pair(i, null));
        buildHeap(h1, k);
        Vector<Pair> h2 = new Vector<Pair>(h1.capacity());
        Pair root = h1.get(0);
        root.setPosition(0);
        h2.add(root);
        heapSelect(h1, h2, k);

        System.out.println(h2.get(0).getKey());
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


    /**
     * Selects k-th element in a vector with O(n log n) time complexity
     * @param h1 first vector of Pairs. REQUIRED not empty
     * @param h2 second vector of Pairs. REQUIRED not empty
     * @param k index for the selected element
     */
    public static void heapSelect(Vector<Pair> h1, Vector<Pair> h2, int k){
        Pair left, right, root;
        boolean min = false;
        if(k <= h1.size()/2){
            for(int i = 0; i < k-1; i++) {
                root = h2.get(0);
                int rootPos = root.getPosition();
                int l = rootPos * 2 + 1;
                int r = rootPos * 2 + 2;
                extract(h2, true);
                if(l < h1.size()) {
                    left = h1.get(l);
                    left.setPosition(l);
                    insertMin(h2, left);
                }
                if(r < h1.size()){
                    right = h1.get(r);
                    right.setPosition(r);
                    insertMin(h2, right);
                }
            }
        }
        else {
            for(int i = h1.size()-1; i > k-1; i--) {
                root = h2.get(0);
                int rootPos = root.getPosition();
                int l = rootPos * 2 + 1;
                int r = rootPos * 2 + 2;
                extract(h2, false);
                if(l < h1.size()) {
                    left = h1.get(l);
                    left.setPosition(l);
                    insertMax(h2, left);
                }
                if(r < h1.size()){
                    right = h1.get(r);
                    right.setPosition(r);
                    insertMax(h2, right);
                }
            }
        }
    }

    /**
     * Given a vector of pairs, builds a minHeap
     * @param vector the vector of pairs
     * @param k the position of the searched element
     */
    public static void buildHeap(Vector<Pair> vector, int k) {
        for(int i = vector.size()/2 - 1; i >= 0; i--) {
            if(k <= vector.size()/2)
                minHeapify(vector, i);
            else
                maxHeapify(vector, i);
        }
    }

    /**
     * maxHeapify algorithm with O(log n) time complexity
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
     * minHeapify algorithm with O(log n) time complexity
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
     * Adds an element inside the vector making sure to keep the maxHeap valid
     * @param vector the minHeap vector
     * @param pair the new Pair that will be added to the minHeap
     */
    public static void insertMax(Vector<Pair> vector, Pair pair) {
        vector.add(pair);
        if(vector.size() > 1) {
            int i = vector.size() - 1;
            int parentPos = (i - 1) / 2;
            int parentKey = vector.get(parentPos).getKey();
            int nodeKey = vector.get(i).getKey();
            while(i > 0 && nodeKey > parentKey){
                Collections.swap(vector, i, parentPos);
                i = parentPos;
                parentPos = (i - 1) / 2;
                parentKey = vector.get(parentPos).getKey();
                nodeKey = vector.get(i).getKey();
            }
        }
    }

    /**
     * Adds an element inside the vector making sure to keep the minHeap valid
     * @param vector the minHeap vector
     * @param pair the new Pair that will be added to the minHeap
     */
    public static void insertMin(Vector<Pair> vector, Pair pair) {
        vector.add(pair);
        if(vector.size() > 1) {
            int i = vector.size() - 1;
            int parentPos = (i - 1) / 2;
            int parentKey = vector.get(parentPos).getKey();
            int nodeKey = vector.get(i).getKey();
            while(i > 0 && nodeKey < parentKey){
                Collections.swap(vector, i, parentPos);
                i = parentPos;
                parentPos = (i - 1) / 2;
                parentKey = vector.get(parentPos).getKey();
                nodeKey = vector.get(i).getKey();
            }
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
