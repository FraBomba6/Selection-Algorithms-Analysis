import java.util.Scanner;

public class MedianSelect {
    public static void main(String[] args) {
        System.out.print("Enter an array of integers: ");
        Scanner input = new Scanner(System.in);
        String inVector = input.nextLine();
        int[] array = getInputVector(inVector);
        System.out.print("Enter an integer: ");
        int k = input.nextInt();

        System.out.print(V[MedianOfMedians(V, 0, V.length - 1, false)] + "\n");
    }

    public static int MedianOfMedians(int[] V, int l, int r, boolean p) {
        int k = 3;
        int i = l;
        int last5 = (r - l) - (r - l) % 5 + l;
        while(i < r) {
            InsertionSort(V, i, i == last5 ? (i+=(r-l)%5 + 1) : (i+=5));
        }

        if(r-l < 5) {
            if(p) {
                swap(V, l, (r-l+1)/2 + l);
                return l;
            }
            else
                return k;
        }

        i = l + 2;
        int count = 0;
        while(i <= r ) {
            swap(V, i, l + count++);
            if (i < last5 - 3)
                i += 5;
            else
                i += 3 + ((r - l) % 5) / 2;
        }

        int pivot = MedianOfMedians(V, l, (r - l)/5 + l, true);
        pivot = partition(V, pivot, r);

        if(pivot == k) {
            return pivot;
        }
        else if (k < pivot) {
            return MedianOfMedians(V, l, pivot - 1, false);
        }
        else {
            return MedianOfMedians(V, pivot + 1, r, false);
        }
    }

    public static void InsertionSort(int[] V, int l, int r) {
        int i = l + 1;
        while(i < r) {
            int j = i;
            while (j > l && V[j - 1] > V[j]) {
                swap(V, j, j-1);
                j--;
            }
            i++;
        }
    }

    public static int partition(int[] V, int l, int r) {
        int pivot = V[l];
        int i = l + 1;

        for(int j = i; j <= r; j++) {
            if (V[j] < pivot) {
                swap(V, i++, j);
            }
        }
        swap(V, --i, l);

        return i;
    }

    public static void swap(int[] V, int i, int j) {
        int tmp = V[i];
        V[i] = V[j];
        V[j] = tmp;
    }
}
