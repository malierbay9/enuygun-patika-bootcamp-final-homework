package appium_case.utils;


import org.testng.annotations.DataProvider;

public class DP {

    @DataProvider(name = "userData")
    public static Object[][] getData(){
        String excelPath = System.getProperty("user.dir")+"/src/test/resources/user_data.xlsx";
        String sheetName = "Sayfa1";
        ExcelDataReader excelDataReader = new ExcelDataReader(excelPath, sheetName);

        Object data[][] = excelDataReader.getData();

        return data;
    }

}
