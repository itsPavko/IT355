package org.example;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class StudentReader {

    public void readStudents() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/main/java/files/students.xlsx");
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            String firstName = row.getCell(0).getStringCellValue();
            String lastName = row.getCell(1).getStringCellValue();
            int indexNumber = row.getCell(2).getRowIndex();

            System.out.println(firstName + " " + lastName + ", " + indexNumber);
        }
    }
}
