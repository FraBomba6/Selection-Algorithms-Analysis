import org.apache.commons.math3.random.MersenneTwister;

public class RandomTest {
    public static void main(String[] args) {
        MersenneTwister mt = new MersenneTwister();
        int[] array = new int[1000];
        int i = 0;
        while(i < 1000) {
            array[i++] = mt.nextInt();
        }
        System.out.print("Done!");
    }
}
