import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

import java.lang.Math;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Time {

    /**
     * Main algorithm that computes all the execution times for any of the three algorithms with random input and
     * given k and fills an excel spreadsheet with all the obtained data
     */
    public static void main(String[] args) {
        try {
            int targetSize;
            long maxError = Resolution.getResolution()*101;
            String fileName = "Time.xlsx";
            //Initializing a new excel file and sheet in which data will be registered
            FileInputStream inputStream = new FileInputStream(new File(fileName));
            Workbook workbook = WorkbookFactory.create(inputStream);

            //For each iteration generates a targetsize for the array based on the exponential function
            for(int iter = 0; iter < 50; iter++){
                targetSize = (int)(Math.pow(1.25, iter)*10);

                //builds the random filled array based on the target size and sets 4 different values for k
                int[] kArray = {5, targetSize/2, (int)(Math.log(targetSize)/Math.log(2)), (targetSize - 5)};

                //Compute the execution time 50 times for every algorithm choosing a different k every time.
                for (int i = 0; i < 4; i++) {
                    int k = kArray[i];
                    Sheet sheet = workbook.getSheetAt(iter);
                    System.out.println("Sheet " + iter + "    Array length: " + sheet.getSheetName() + "    k: " + kArray[i]);
                    for (int row_index = 4; row_index < 54; row_index++) {
                        int[] input = RandomTest.randomInput(targetSize);
                        //New line
                        Row row = sheet.getRow(row_index);
                        Cell cell = row.createCell(5*i);
                        //Executes heap select
                        cell.setCellValue(getExTimeHeapSelect(input, k, maxError));
                        cell = row.createCell(5*i + 1);
                        //Executes Quick select
                        cell.setCellValue(getExTimeQuickSelect(input, k, maxError));
                        cell = row.createCell(5*i + 2);
                        //Executes Median of Medians select
                        cell.setCellValue(getExTimeMedianSelect(input, k, maxError));
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
     * Computes the execution time for minHeap selection algorithm
     *
     * @return the execution time as a long value
     */
    public static long getExTimeHeapSelect(int[] array, int k, long maxError) {
        long start, end;
        int count = 0;
        start = System.nanoTime();
        do {
            HeapSelect.heapSelect(array, k);
            end = System.nanoTime();
            count++;
        } while (end - start <= maxError);

        return (end - start) / count;
    }


    /**
     * Computes execution time for median of medians selection algorithm
     *
     * @return the execution time as a long value
     */
    public static long getExTimeMedianSelect(int[] array, int k, long maxError) {
        long start, end;
        int count = 0;
        start = System.nanoTime();
        do {
            MedianSelect_old.medianSelect(array, 0, array.length - 1, false, --k);
            end = System.nanoTime();
            count++;
        } while (end - start <= maxError);

        return (end - start) / count;
    }


    /**
     * Computes execution time for quick select algorithm
     *
     * @return the execution time as a long value
     */
    public static long getExTimeQuickSelect(int[] array, int k, long maxError) {
        long start, end;
        int count = 0;
        start = System.nanoTime();
        do {
            QuickSelect.quickSelect(array, 0, array.length - 1, k);
            end = System.nanoTime();
            count++;
        } while (end - start <= maxError);

        return (end - start) / count;
    }
}