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
            //Initializing a new excel file and sheet in which data will be registered
            FileInputStream inputStream = new FileInputStream(new File("Time.xlsx"));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            //Fills the excel sheet
            for(int row_index=1; row_index<102; row_index++){
                //New line
                Row row = sheet.createRow(row_index);
                Cell cell = row.createCell(0);
                //Executes heap select
                cell.setCellValue(getExTimeHeapSelect());
                cell = row.createCell(1);
                //Executes Quick select
                cell.setCellValue(getExTimeQuickSelect());
                cell = row.createCell(2);
                //Executes Median of Medians select
                cell.setCellValue(getExTimeMedianSelect());
            }
            //terminates after filling the sheet with 100 time execution's observations for each algorithm

            inputStream.close();

            //saves the excel file created before
            FileOutputStream outputStream = new FileOutputStream("Time.xlsx");
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Computates the resolution error from the executin machine
     * @return the error as a long value
     */
    public static long getResolution() {
        long start, end;
        start = System.nanoTime();
        do {
            end = System.nanoTime();
        } while(start == end);

        return end - start;
    }

    /**
     * Computates execution time for minHeap selection algorithm
     * @return the execution time as a long value
     */
    public static long getExTimeHeapSelect() {
        long start, end;
        int count = 0;

        start = System.nanoTime();
        do {
            HeapSelect.main(new String[0]);
            end = System.nanoTime();
            count++;
        } while(end - start <= 20100);

        return (end - start)/count;
    }


    /**
     * Computates execution time for median of medians selection algorithm
     * @return the execution time as a long value
     */
    public static long getExTimeMedianSelect(int[] array, int k) {
        long start, end;
        int count = 0;
        start = System.nanoTime();
        do {
            MedianSelect.MedianOfMedians(array, 0, array.length - 1, false, k);;
            end = System.nanoTime();
            count++;
        } while(end - start <= 20100);

        return (end - start)/count;
    }


    /**
     * Computates execution time for quick select algorithm
     * @return the execution time as a long value
     */
    public static long getExTimeQuickSelect() {
        long start, end;
        int count = 0;
        start = System.nanoTime();
        do {
            QuickSelect.main(new String[0]);
            end = System.nanoTime();
            count++;
        } while(end - start <= 20100);

        return (end - start)/count;
    }
}