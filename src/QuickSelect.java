import java.util.Scanner;

public class QuickSelect {
    public static void main(String[] args) {
        System.out.print("Enter an array of integers: ");
        Scanner input = new Scanner(System.in);
        String inVector = input.nextLine();
        int[] array = getInputVector(inVector);
        System.out.print("Enter an integer: ");
        int k = input.nextInt();

        System.out.print(quickSelect(V, 0, V.length - 1, --k));
    }
    public static int quickSelect(int[] V, int l, int r, int k) {
        int mid = partition(V, l, r);
        if (k == mid) {
            return V[k];
        }
        else if(k < mid)
            return quickSelect(V, l, mid - 1, k);
        else
            return quickSelect(V, mid + 1, r, k);
    }

    public static int partition(int[] V, int l, int r) {
        int pivot = V[r];
        int i = l - 1;
        int tmp;

        for(int j = l; j < r; j++)
            if(V[j] < pivot) {
                tmp = V[++i];
                V[i] = V[j];
                V[j] = tmp;
            }

        tmp = V[++i];
        V[i] = V[r];
        V[r] = tmp;

        return i;
    }
}
