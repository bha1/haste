package haste;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSheetGenerator {
public void sheetBuilder() {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Datatypes in Java");
    Object[][] datatypes = {
            {"Datatype", "Type", "Size(in bytes)"},
            {"int", "Primitive", 2},
            {"float", "Primitive", 4},
            {"double", "Primitive", 8},
            {"char", "Primitive", 1},
            {"String", "Non-Primitive", "No fixed size"}
    };

    int rowNum = 0;
    System.out.println("Creating excel");

    for (Object[] datatype : datatypes) {
        Row row = sheet.createRow(rowNum++);
        int colNum = 0;
        for (Object field : datatype) {
            Cell cell = row.createCell(colNum++);
            if (field instanceof String) {
                cell.setCellValue((String) field);
            } else if (field instanceof Integer) {
                cell.setCellValue((Integer) field);
            }
        }
    }
    
    XSSFSheet sheet1 = workbook.createSheet("Java in Datatype");
    Object[][] datatypes1 = {
            {"Datatype", "Type", "Size(in bytes)"},
            {"int", "Primitive", 2},
            {"float", "Primitive", 4},
            {"double", "Primitive", 8},
            {"char", "Primitive", 1},
            {"String", "Non-Primitive", "No fixed size"}
    };

    int rowNum1 = 0;
    System.out.println("Creating excel");

    for (Object[] datatype : datatypes1) {
        Row row = sheet1.createRow(rowNum1++);
        int colNum = 0;
        for (Object field : datatype) {
            Cell cell = row.createCell(colNum++);
            if (field instanceof String) {
                cell.setCellValue((String) field);
            } else if (field instanceof Integer) {
                cell.setCellValue((Integer) field);
            }
        }
    }
    
    try {
        FileOutputStream outputStream = new FileOutputStream("/salil.xlsx");
        workbook.write(outputStream);
        workbook.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

    System.out.println("Done");
}
}
