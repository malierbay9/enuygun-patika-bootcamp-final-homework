package appium_case.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//Bu sınıf android driver için gerekli capabilityleri json dosyasından okuyup DesiredCapabilities nesnesi olarak elde etmemizi sağlar.
public class CapabilitySettings {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final DesiredCapabilities capabilities = new DesiredCapabilities();
    private static Map<String,String> capsAsMap;

    public CapabilitySettings(){

    }

    //json dosyasından okunan capabilityleri önce map e sonra onu DesiredCapabilities nesnesine çeviririz.
    public static DesiredCapabilities getDesiredCapsFromJson(String jsonPath){
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(jsonPath));
            capsAsMap = mapper.readValue(reader, HashMap.class);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

        for(String capName : capsAsMap.keySet()){
            capabilities.setCapability(capName,capsAsMap.get(capName));
        }

        return capabilities;
    }

}
