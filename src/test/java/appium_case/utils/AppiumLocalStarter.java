package appium_case.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;

public class AppiumLocalStarter {

    private static final AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\malie\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
            .withIPAddress("127.0.0.1").usingPort(4723).build();

    public AppiumLocalStarter(){

    }

    public static void start() throws AppiumServerHasNotBeenStartedLocallyException {
        service.start();
    }

    public static void stop(){
        service.stop();
    }
}
