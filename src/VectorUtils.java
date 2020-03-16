public class VectorUtils {

    static int[] getInputVector(String inputLine){
        String els[] = inputLine.split("\\s+");
        int length = els.length;
        int[] output = new int[length];
            for (int i = 0; i < length; i++) {
                output[i] = Integer.parseInt(els[i]);
            }
        return output;
    }
}
