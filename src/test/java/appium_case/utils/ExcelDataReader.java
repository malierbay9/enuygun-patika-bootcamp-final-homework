package appium_case.utils;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

//Excel dosyasından data okumayı sağlayan metotları içeren sınıftır.
public class ExcelDataReader {

    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;

    public ExcelDataReader(String excelPath,String sheetName) {

        try {
            workbook = new XSSFWorkbook(excelPath);
            sheet = workbook.getSheet(sheetName);
        }
        catch (IOException e){
            System.out.println(e.getMessage()+"---"+e.getCause());
        }
    }

    public static int getRowCount() {

        return sheet.getPhysicalNumberOfRows();

    }

    public static int getColCount() {

        return sheet.getRow(0).getPhysicalNumberOfCells();

    }

    public static Object getCellDataString(int row,int col) {
        if(sheet.getRow(row).getCell(col).getCellType().name().equalsIgnoreCase("string")){
            return sheet.getRow(row).getCell(col).getStringCellValue();
        }
        else
            return (int)sheet.getRow(row).getCell(col).getNumericCellValue();
    }

    //Bu metodu DataProvider metodunda kullanacağız.
    public Object[][] getData(){

        int rowCount = getRowCount();
        int colCount = getColCount();

        Object data[][] = new Object[rowCount-1][colCount];

        for(int i = 1; i<rowCount;i++){

            for (int j = 0; j<colCount; j++){

                data[i-1][j] = getCellDataString(i,j);

            }

        }

        return data;
    }

}
