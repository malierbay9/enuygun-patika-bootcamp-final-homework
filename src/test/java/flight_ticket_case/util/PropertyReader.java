package flight_ticket_case.util;

import flight_ticket_case.models.ConfigProperties;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.IOException;

public class PropertyReader {

    private static ConfigProperties properties ;
    private static Yaml yaml = new Yaml(new Constructor(ConfigProperties.class));
    private static FileInputStream inputStream;
    private static String userDirPath = System.getProperty("user.dir");


    public static ConfigProperties getProperties(){

        try {

            inputStream = new FileInputStream(userDirPath +"/src/test/resources/enuygun_config.yml");
            properties = yaml.load(inputStream);

        }
        catch (IOException e) {
            System.out.println(e.getMessage()+"---"+e.getCause());
        }

        return properties;
    }

}
