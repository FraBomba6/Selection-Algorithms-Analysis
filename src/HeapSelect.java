import java.util.Vector;
import java.util.Collections;

class Pair {
    Integer key;
    Integer position;

    Pair(Integer key, Integer position) {
        this.key = key;
        this.position = position;
    }

    public Integer  getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}

public class HeapSelect {
    public static void main(String[] args) {
        int[] array = {89, 86, 61, 33, 4, 83, 84, 31, 57, 19};
        int k = 5;

        Vector<Pair> h1 = new Vector<Pair>(10); //4 19 61 31 86 83 84 33 57 89
        for (int i: array)
            h1.add(new Pair(i, null));
        buildHeap(h1);
        Vector<Pair> h2 = new Vector<Pair>(h1.capacity());

        heapSelect(h1, h2, k);

        System.out.println(h2.get(0).getKey());
    }

    public static void heapSelect(Vector<Pair> h1, Vector<Pair> h2, int k){
        Pair root = h1.get(0);
        root.setPosition(0);
        insert(h2, root);
        for(int i = 0; i < k - 1; i++) {
            root = h2.get(0);
            int rootPos = root.getPosition();
            int l = rootPos * 2 + 1;
            int r = rootPos * 2 + 2;
            if(l < h1.size()) {
                Pair left = h1.get(l);
                left.setPosition(l);
                insert(h2, left);
            }
            if(r < h1.size()){
                Pair right = h1.get(r);
                right.setPosition(r);
                insert(h2, right);
            }
            extract(h2);
        }
    }

    public static void setPositions(Vector<Pair> V) {
        int pos = 0;
        for(Pair pair : V)
            pair.setPosition(pos++);
    }

    public static void buildHeap(Vector<Pair> V) {
        for(int i = V.size()/2 - 1; i >= 0; i--)
            minHeapify(V, V.size(), i);
    }

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

    public static void insert(Vector<Pair> V, Pair pair) {
        V.add(pair);
        buildHeap(V);
    }

    public static void extract(Vector<Pair> V) {
        Collections.swap(V, 0, V.size() - 1);
        V.remove(V.size() - 1);
        buildHeap(V);
    }
}
