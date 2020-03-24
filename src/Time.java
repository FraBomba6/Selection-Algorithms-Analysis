import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

public class Time {
    public static void main(String[] args) {
        try {
            int targetSize = 10;
            String fileName = "Time_" + targetSize + ".xlsx";
            //Initializing a new excel file and sheet in which data will be registered
            FileInputStream inputStream = new FileInputStream(new File(fileName));
            Workbook workbook = WorkbookFactory.create(inputStream);
            int[] input = RandomTest.randomInput(targetSize);

            //Fills the excel sheet
            for (int i = 0; i < 5; i++) {
                int k = RandomTest.getK(targetSize);
                if(!(workbook.getSheetName(i).equals(String.valueOf(k)))) {
                    workbook.setSheetName(i, String.valueOf(k));
                }
                Sheet sheet = workbook.getSheetAt(i);
                for (int row_index = 1; row_index < 101; row_index++) {
                    //New line
                    Row row = sheet.createRow(row_index);
                    Cell cell = row.createCell(0);
                    //Executes heap select
                    cell.setCellValue(getExTimeHeapSelect(input, k));
                    cell = row.createCell(1);
                    //Executes Quick select
                    cell.setCellValue(getExTimeQuickSelect(input, k));
                    cell = row.createCell(2);
                    //Executes Median of Medians select
                    cell.setCellValue(getExTimeMedianSelect(input, k));

                    System.out.print("\rSheet "+k+" "+row_index+"%");
                }
                System.out.println();
            }
            //terminates after filling the sheet with 100 time execution's observations for each algorithm

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
     * Computates the resolution error from the executin machine
     *
     * @return the error as a long value
     */
    public static long getResolution() {
        long start, end;
        start = System.nanoTime();
        do {
            end = System.nanoTime();
        } while (start == end);

        return end - start;
    }

    /**
     * Computates execution time for minHeap selection algorithm
     *
     * @return the execution time as a long value
     */
    public static long getExTimeHeapSelect(int[] array, int k) {
        long start, end;
        int count = 0;
        Vector<Pair> h1 = new Vector<Pair>(10);
        for (int i : array)
            h1.add(new Pair(i, null));
        HeapSelect.buildHeap(h1);
        Vector<Pair> h2 = new Vector<Pair>(h1.capacity());
        start = System.nanoTime();
        do {
            HeapSelect.heapSelect(h1, h2, k);
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
            ;
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