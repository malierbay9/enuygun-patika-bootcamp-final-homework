package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static Properties properties = new Properties();
    private static FileInputStream inputStream;
    private static FileOutputStream outputStream;
    private static String userDirPath = System.getProperty("user.dir");



    public static Properties getProperties(){

        try {

            inputStream = new FileInputStream(userDirPath +"/src/test/resources/enuygun.properties");
            properties.load(inputStream);

        }
        catch (IOException e) {
            System.out.println(e.getMessage()+"---"+e.getCause());
        }

        return properties;
    }

    public static void setProperties(String propToChange,String newValueOfProp){

        try {
            outputStream = new FileOutputStream(userDirPath +"/src/test/resources/enuygun.properties");
            properties.setProperty(propToChange,newValueOfProp);
            properties.store(outputStream,null);
        } catch (IOException e) {
            System.out.println(e.getMessage()+"---"+e.getCause());
        }
    }

}
