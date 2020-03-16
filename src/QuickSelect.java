import java.util.Scanner;

public class QuickSelect {
    public static void main(String[] args) {
        System.out.print("Enter an array of integers: ");
        Scanner input = new Scanner(System.in);
        String inVector = input.nextLine();
        int[] array = getInputVector(inVector);
        System.out.print("Enter an integer: ");
        int k = input.nextInt();

        System.out.print(quickSelect(array, 0, array.length - 1, --k));
    }


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
     * Finds k-th element in the array in 0(n^2) time complexity
     * @param array the array in which the element will be searched. REQUIRED not empty
     * @param l the left index. REQUIRED 0<=l<array length
     * @param r the right index. REQUIRED 0<=r<array length
     * @param k the index for the element that will be retured. REQUIRED 0<=k<array length
     * @return the k-th value in the array
     */
    public static int quickSelect(int[] array, int l, int r, int k) {
        int mid = partition(array, l, r);
        if (k == mid) {
            return array[k];
        }
        else if(k < mid)
            return quickSelect(array, l, mid - 1, k);
        else
            return quickSelect(array, mid + 1, r, k);
    }

    /**
     * Provides a partitioning index according to the given indexes
     * @param array the array that will be partitioned.  REQUIRED not empty
     * @param l left index. REQUIRED 0<=l<array length
     * @param r right index. REQUIRED 0<=r<array length
     * @return the index for partitioning the array
     */
    public static int partition(int[] array, int l, int r) {
        int pivot = array[r];
        int i = l - 1;
        int tmp;

        for(int j = l; j < r; j++)
            if(array[j] < pivot) {
                tmp = array[++i];
                array[i] = array[j];
                array[j] = tmp;
            }

        tmp = array[++i];
        array[i] = array[r];
        array[r] = tmp;

        return i;
    }
}
