import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Pair {
    Integer position;
    Integer key;

    /**
     * Constructor for Pair object
     * @param key the value for the pair
     */
    Pair(Integer key) {
        this.key = key;
        this.position = null;
    }
}

public class HeapSelect {
    public static void main(String[] args) {
        System.out.print("Enter an array of integers: ");
        Scanner input = new Scanner(System.in);
        String inVector =  input.nextLine();
        int[] array = getInputVector(inVector);
        int k = input.nextInt();
        System.out.println(HeapSelect(array, k - 1));
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
     * Builds auxiliary data structures and Heap Selection algorithm with min or max Heap depending on input
     * @param array the input array, REQUIRED as a valid input
     * @param k the index (from 1 to array size) REQUIRED as a valid index
     */
    public static int HeapSelect(int[] array, int k) {
        int h1_size = array.length;
        boolean isMinHeap = false;
        if(k < h1_size / 2)
            isMinHeap = true;
        else
            k = h1_size - 1 - k;

        ArrayList<Pair> h1 = new ArrayList<>(h1_size);
        for(int element : array)
            h1.add(new Pair(element));
        build_heap(h1, h1_size, isMinHeap);
        for(int i = 0; i < h1_size; i++)
            h1.get(i).position = i;

        ArrayList<Pair> h2 = new ArrayList<>(k+1);
        h2.add(h1.get(0));

        return select(h1, h2, k, isMinHeap);
    }


    /**
     * Selects the k-th element using heap 1 and heap 2
     * @param h1 the first heap
     * @param h2 the second heap (auxiliary one)
     * @param k the index. REQUIRED as a valid index
     * @param isMinHeap true if the heap is a minHeap, false if is a maxHeap
     * @return the key of the root node from the auxiliary heap
     */
    private static int select(ArrayList<Pair> h1, ArrayList<Pair> h2, int k, boolean isMinHeap) {
        for(int i = 0; i < k; i++){
            int root_position = h2.get(0).position;
            int left = root_position * 2 + 1;
            int right = root_position * 2 + 2;

            extract(h2, isMinHeap);

            if(left < h1.size())
                insert(h2, h1.get(left), isMinHeap);
            if(right < h1.size())
                insert(h2, h1.get(right), isMinHeap);
        }
        return h2.get(0).key;
    }


    /**
     * Inserts a new element in the heap
     * @param heap the heap in which the new element will be added
     * @param node the pair node that will be added
     * @param isMinHeap true if the heap is a minHeap, false if is a maxHeap
     */
    private static void insert(ArrayList<Pair> heap, Pair node, boolean isMinHeap) {
        heap.add(node);
        if(heap.size() > 1) {
            int current_node = heap.size() - 1;
            int parent_node = (current_node - 1) / 2;
            if (isMinHeap)
                while (current_node > 0 && heap.get(current_node).key < heap.get(parent_node).key) {
                    Collections.swap(heap, parent_node, current_node);
                    current_node = parent_node;
                    parent_node = (current_node - 1) / 2 ;
                }
            else
                while (current_node > 0 && heap.get(current_node).key > heap.get(parent_node).key) {
                    Collections.swap(heap, parent_node, current_node);
                    current_node = parent_node;
                    parent_node = (current_node - 1) / 2;
                }
        }
    }

    /**
     * Extracts root node from the heap and ensures that the heap mantains its properties
     * @param h2 the heap. REQUIRED with at least one node (the root one)
     * @param isMinHeap true if the heap is a minHeap, false if is a maxHeap
     */
    private static void extract(ArrayList<Pair> h2, boolean isMinHeap) {
        int index_lastObj = h2.size() - 1;
        Collections.swap(h2, 0, index_lastObj);
        h2.remove(index_lastObj);
        if(isMinHeap)
            min_heapify(h2, 0, h2.size());
        else
            max_heapify(h2, 0, h2.size());
    }


    /**
     * Builds the heap performing minHeapify or maxHeapify according to the heap properties
     * @param h1 the heap
     * @param h1_size the size of the heap
     * @param isMinHeap true if the heap is a minHeap, false if is a maxHeap
     */
    private static void build_heap(ArrayList<Pair> h1, int h1_size, boolean isMinHeap) {
        for(int i = (h1_size / 2) - 1; i >= 0; i--) {
            if(isMinHeap)
                min_heapify(h1, i, h1_size);
            else
                max_heapify(h1, i, h1_size);
        }
    }


    /**
     * MinHeapify algorithm
     * @param h1 the heap
     * @param i index for the smallest element in the heap. REQUIRED as a valid index
     * @param h1_size the size of the heap. REQUIRED as a valid size
     */
    private static void min_heapify(ArrayList<Pair> h1, int i, int h1_size) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if(left < h1_size && h1.get(left).key < h1.get(smallest).key)
            smallest = left;
        if(right < h1_size && h1.get(right).key < h1.get(smallest).key)
            smallest = right;
        if(smallest != i) {
            Collections.swap(h1, smallest, i);
            min_heapify(h1, smallest, h1_size);
        }
    }


    /**
     * MaxHeapify algorithm
     * @param h1 the heap
     * @param i index for the smallest element in the heap. REQUIRED as a valid index
     * @param h1_size the size of the heap. REQUIRED as a valid size
     */
    private static void max_heapify(ArrayList<Pair> h1, int i, int h1_size) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if(left < h1_size && h1.get(left).key > h1.get(largest).key)
            largest = left;
        if(right < h1_size && h1.get(right).key > h1.get(largest).key)
            largest = right;
        if(largest != i) {
            Collections.swap(h1, largest, i);
            max_heapify(h1, largest, h1_size);
        }
    }
}
