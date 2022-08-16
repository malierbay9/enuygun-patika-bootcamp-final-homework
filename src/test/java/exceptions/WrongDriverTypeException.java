package exceptions;

public class WrongDriverTypeException extends RuntimeException{

    @Override
    public String getMessage() {
        return "You made a mistake in the drivertype property in props.properties file";
    }
}

