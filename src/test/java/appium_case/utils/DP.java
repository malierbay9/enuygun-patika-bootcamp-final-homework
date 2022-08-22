package appium_case.utils;


import org.testng.annotations.DataProvider;

//DataProvider sınıfı

public class DP {

    //Bu metotla excelden okuduğumuz dataları testlerimiz kullanabileceğiz.
    @DataProvider(name = "userData")
    public static Object[][] getData(){
        String excelPath = System.getProperty("user.dir")+"/src/test/resources/appium_user_data.xlsx";
        String sheetName = "Sayfa1";
        ExcelDataReader excelDataReader = new ExcelDataReader(excelPath, sheetName);

        Object data[][] = excelDataReader.getData();

        return data;
    }

}
