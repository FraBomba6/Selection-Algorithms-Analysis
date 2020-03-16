
import java.util.Scanner;
import java.util.Vector;

public class VectorUtils {


    /**
     * Metodo per inserire i dati della linea di input in un vettore
     * @param inputLine la linea di input inserita. REQUIRE not null
     * @return il vettore contenente i valori di input
     */
    static Vector<Integer> getVector(String inputLine){
        Vector<Integer> v = new Vector<>();
        String els[] = inputLine.split("\\s+");
            for (int i = 0; i < els.length; i++) {
                try {
                    v.add(Integer.parseInt(els[i]));
                } catch (NumberFormatException nfe) {
                    //Se non è un numero intero lo salta
                }
            }
        return v;
    }


    /**
     * Metodo per stampare su schermo i valori contenuti in un vettore
     * @param v il vettore da stampare. REQUIRE not null
     */
    static void printVector(Vector v){
        for(int i=0; i<v.size(); i++){
            System.out.print(v.get(i) + " ");
        }
    }


    /**
     * Swaps two Integers in the Vector A
     * @param A the vector.
     * @param a the index of the first element. REQUIRED as valid index 0 <= a < A.size()
     * @param b the index of the second element. REQUIRED as valid index 0 <= b < A.size()
     */
    static void swap(Vector<Integer> A, Integer a, Integer b){
        Integer temp = A.get(a);
        A.setElementAt(A.get(b), a);
        A.setElementAt(temp,b);
    }
}
