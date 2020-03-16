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

    static int[] getInputVector(String inputLine){
        String els[] = inputLine.split("\\s+");
        int length = els.length;
        int[] output = new int[length];
        for (int i = 0; i < length; i++) {
            output[i] = Integer.parseInt(els[i]);
        }
        return output;
    }

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
