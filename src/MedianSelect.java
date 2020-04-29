import java.util.Scanner;

public class MedianSelect {/*
    public static void main(String[] args) {
        System.out.print("Enter an array of integers: ");
        Scanner input = new Scanner(System.in);
        String inVector = input.nextLine();
        int[] array = getInputVector(inVector);
        System.out.print("Enter an integer: ");
        int k = input.nextInt();

        System.out.print(array[medianSelect(array, 0, array.length - 1, --k)] + "\n");
    }*/

    /**
     * Splits the input line in all the different values
     * @param inputLine required as a string of ints divided by one (or more) blank spaces
     * @return an int array containing all the ints from the input
     */
    static int[] getInputVector(String inputLine){
        String els[] = inputLine.split("\\s+");
        int length = els.length;
        int[] output = new int[length];
        for (int i = 0; i < length; i++) {
            output[i] = Integer.parseInt(els[i]);
        }
        return output;
    }


    /**
     * Computates the median of medians selection algorithm
     * @param array the array with input values. REQUIRED not empty, at least one element
     * @param l left index. REQUIRED 0<=l< array length
     * @param r right index. REQUIRED 0<=r< array length
     * @param k index for the algorithm, REQUIRED 0<=k< array length
     * @return
     */
    public static int medianSelect(int[] array, int l, int r, int k) {
        int first = l;
        int lastGroup = r - (r - l) % 5;
        while(first < r) {
            InsertionSort(array, first, first == lastGroup ? r : (first + 4));
            first+=5;
        }

        if(r-l < 5)
            return k;

        int median = l + 2;
        int count = 0;
        while(median <= r ) {
            swap(array, median, l + count++);
            if (median < lastGroup - 3)
                median += 5;
            else {
                median += 3 + (r - lastGroup) / 2;
                swap(array, median, l + count);
                break;
            }
        }

        medianOfMedians(array, l, l + count);

        int pivot = partition(array, l, r);
        if(pivot == k)
            return pivot;
        else if (k < pivot)
            return medianSelect(array, l, pivot - 1, k);
        else
            return medianSelect(array, pivot + 1, r, k);
    }

    public static void medianOfMedians(int[] array, int l, int r) {
        int i = l;
        int lastGroup = r - (r - l) % 5;
        while(i < r) {
            InsertionSort(array, i, i == lastGroup ? r : (i + 4));
            i+=5;
        }

        if(r - l >= 5) {
            i = l + 2;
            int count = 0;
            while(i <= r ) {
                swap(array, i, l + count++);
                if (i < lastGroup - 3)
                    i += 5;
                else {
                    i += 3 + (r - lastGroup) / 2;
                    swap(array, i, l + count);
                    break;
                }
            }
            medianOfMedians(array, l, l + count);
        }
        else
            swap(array, l, (r-l)/2 + l);
    }


    /**
     * Insertion sort algorithm. Sorts the array given as input
     * @param array the array that will be sorted.  REQUIRED not empty
     * @param l left index. REQUIRED 0<=l<array length
     * @param r right index. REQUIRED 0<=r<array length
     */
    public static void InsertionSort(int[] array, int l, int r) {
        int i = l + 1;
        while(i <= r) {
            int j = i;
            while (j > l && array[j - 1] > array[j]) {
                swap(array, j, j-1);
                j--;
            }
            i++;
        }
    }



    /**
     * Provides a partitioning index according to the given indexes
     * @param array the array that will be partitioned.  REQUIRED not empty
     * @param l left index. REQUIRED 0<=l<array length
     * @param r right index. REQUIRED 0<=r<array length
     * @return the index for partitioning the array
     */
    public static int partition(int[] array, int l, int r) {
        int pivot = array[l];
        int i = l + 1;

        for(int j = i; j <= r; j++)
            if (array[j] < pivot)
                swap(array, i++, j);
        swap(array, --i, l);

        return i;
    }


    /**
     * Swaps two elements of given index in an array
     * @param array the array that will be partitioned.  REQUIRED not empty
     * @param i index for the first element. REQUIRED 0<=i<array length
     * @param j index for the second element. REQUIRED 0<=j<array length
     */
    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
