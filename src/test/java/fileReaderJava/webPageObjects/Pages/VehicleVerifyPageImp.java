package fileReaderJava.webPageObjects.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VehicleVerifyPageImp implements VehicleVerifyPage{

    private final WebDriver driver;

    public VehicleVerifyPageImp(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    /*
     * String returns "Is this the vehicle you are looking for?"
     */
    public String getPageHeading () {
        return this.driver.findElement(By.className("heading-large")).getText();
    }

    @Override
    public String[] getVehicleInfo() {
        String registrationNumber = this.driver.findElement(By.id("Vrm")).getAttribute("value");
        String make = this.driver.findElement(By.id("Make")).getAttribute("value");
        String colour = this.driver.findElement(By.id("Colour")).getAttribute("value");
        return new String[] {registrationNumber, make, colour};
    }


}
