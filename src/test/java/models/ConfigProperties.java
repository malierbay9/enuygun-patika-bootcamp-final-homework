package models;

import lombok.Data;

import java.util.List;

@Data
public class ConfigProperties {
    private int implicitlyWait;
    private int pageLoadTimeout;
    private String drivertype;
    private List<String> arguments;
}
