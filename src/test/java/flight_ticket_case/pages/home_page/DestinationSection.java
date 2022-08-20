package flight_ticket_case.pages.home_page;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class DestinationSection {

    protected static By fromTextBox = By.id("OriginInput");
    protected static By firstResultOfFrom = By.id("react-autowhatever-OriginInput-section-0-item-0");
    protected static By toTextBox = By.id("DestinationInput");
    protected static By firstResultOfTo = By.id("react-autowhatever-DestinationInput-section-0-item-0");
    protected static By fromSuggestionTab = By.id("react-autowhatever-OriginInput");
    protected static By toSuggestionTab = By.id("react-autowhatever-DestinationInput");

}
