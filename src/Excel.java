import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;

public class Excel {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            String fileName = "Time.xlsx";
            //Initializing a new excel file and sheet in which data will be registered
            FileInputStream inputStream = new FileInputStream(new File(fileName));
            Workbook workbook = WorkbookFactory.create(inputStream);

            //Fills the excel sheet
            for (int i = 1; i < 50; i++) {
                double x = 1.58 * i;
                Sheet sheet = workbook.cloneSheet(0);
                workbook.setSheetName(i, String.valueOf((int)(Math.pow(1.22, x/1.8)*10)));
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
}
