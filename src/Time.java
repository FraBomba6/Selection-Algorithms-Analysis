import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import java.lang.Math;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Time {
    public static void main(String[] args) {
        try {
            int targetSize;
            double x;
            String fileName = "Time.xlsx";
            //Initializing a new excel file and sheet in which data will be registered
            FileInputStream inputStream = new FileInputStream(new File(fileName));
            Workbook workbook = WorkbookFactory.create(inputStream);

            //For each iteration generates a targetsize for the array based on the exponential function
            for(int iter = 0; iter < 50; iter++){
                x = 1.58 * (double)iter;
                targetSize = (int)(Math.pow(1.25, x/1.8)*10);

                //builds the random filled array based on the target size and sets 4 different values for k
                int[] kArray = {4, (targetSize-1)/2, (int)(Math.log(targetSize)), (targetSize - 5)};

                //Compute the execution time 50 times for every algorithm choosing a different k every time.
                for (int i = 0; i < 4; i++) {
                    int[] input = RandomTest.randomInput(targetSize);
                    System.out.println("Sheet " + iter + "    k " + i);
                    int k = kArray[i];
                    Sheet sheet = workbook.getSheetAt(iter);
                    for (int row_index = 4; row_index < 54; row_index++) {
                        //New line
                        Row row = sheet.getRow(row_index);
                        Cell cell = row.createCell(5*i);
                        //Executes heap select
                        cell.setCellValue(getExTimeHeapSelect(input, k));
                        cell = row.createCell(5*i + 1);
                        //Executes Quick select
                        cell.setCellValue(getExTimeQuickSelect(input, k));
                        cell = row.createCell(5*i + 2);
                        //Executes Median of Medians select
                        cell.setCellValue(getExTimeMedianSelect(input, k));
                    }
                }
            }

            inputStream.close();

            //saves the excel file created before
            FileOutputStream outputStream = new FileOutputStream(fileName);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Computates execution time for minHeap selection algorithm
     *
     * @return the execution time as a long value
     */
    public static long getExTimeHeapSelect(int[] array, int k) {
        long start, end;
        int count = 0;
        start = System.nanoTime();
        do {
            HeapSelect.heapSelect(array, k);
            end = System.nanoTime();
            count++;
        } while (end - start <= 10100);

        return (end - start) / count;
    }


    /**
     * Computates execution time for median of medians selection algorithm
     *
     * @return the execution time as a long value
     */
    public static long getExTimeMedianSelect(int[] array, int k) {
        long start, end;
        int count = 0;
        start = System.nanoTime();
        do {
            MedianSelect.MedianOfMedians(array, 0, array.length - 1, false, k);
            end = System.nanoTime();
            count++;
        } while (end - start <= 10100);

        return (end - start) / count;
    }


    /**
     * Computates execution time for quick select algorithm
     *
     * @return the execution time as a long value
     */
    public static long getExTimeQuickSelect(int[] array, int k) {
        long start, end;
        int count = 0;
        start = System.nanoTime();
        do {
            QuickSelect.quickSelect(array, 0, array.length - 1, k);
            end = System.nanoTime();
            count++;
        } while (end - start <= 10100);

        return (end - start) / count;
    }
}