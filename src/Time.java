/*import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Time {
    public static void main(String[] args) throws IOException {
        try {
            FileInputStream inputStream = new FileInputStream(new File("Time.xlsx"));
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            int i = 1;
            while(i < 101) {
                Row row = sheet.createRow(i++);
                Cell cell = row.createCell(0);
                cell.setCellValue(getResolution());
                cell = row.createCell(1);
                cell.setCellValue(getExTime());
            }

            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream("Time.xlsx");
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
    }

    public static long getResolution() {
        long start, end;
        start = System.nanoTime();
        do {
            end = System.nanoTime();
        } while(start == end);

        return end - start;
    }

    public static long getExTime() {
        long start, end;
        int count = 0;
        start = System.nanoTime();
        do {
            MedianSelect.main(new String[0]);
            end = System.nanoTime();
            count++;
        } while(end - start <= 20100);

        return (end - start)/count;
    }
}
*/