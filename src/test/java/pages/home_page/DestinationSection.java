package pages.home_page;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class DestinationSection {

    private By fromTextBox = By.id("OriginInput");
    private By firstResultOfFrom = By.id("react-autowhatever-OriginInput-section-0-item-0");
    private By toTextBox = By.id("DestinationInput");
    private By firstResultOfTo = By.id("react-autowhatever-DestinationInput-section-0-item-0");

}
