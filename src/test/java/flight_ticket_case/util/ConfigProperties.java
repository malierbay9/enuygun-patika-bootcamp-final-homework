package flight_ticket_case.util;

import lombok.Data;
import java.util.List;

//PropertyReader classında .yml dosyasından okuduğumuz propertyleri çevirdiğimiz sınıf.
//Getter metotlarıyla istediğimiz property ye ulaşabiliriz.

@Data
public class ConfigProperties {
    private int implicitlyWait;
    private int pageLoadTimeout;
    private String drivertype;
    private List<String> arguments;
}
