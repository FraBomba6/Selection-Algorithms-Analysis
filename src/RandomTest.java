import org.apache.commons.math3.random.MersenneTwister;

import java.security.SecureRandom;

public class RandomTest {
    public static void main(String[] args) {
        MersenneTwister mt = new MersenneTwister(generateSeed());
        int targetSize = 50;
        int[] array = new int[targetSize];
        int i = 0;
        while(i < targetSize) {
            array[i++] = mt.nextInt();
        }
        System.out.print("Done!");
    }

    static int[] generateSeed() {
        byte[] byteSeed = SecureRandom.getSeed(64);
        int[] intSeed = new int[byteSeed.length];
        for(int i = 0; i < 64; i++)
            intSeed[i] = byteSeed[i];
        return intSeed;
    }
}
