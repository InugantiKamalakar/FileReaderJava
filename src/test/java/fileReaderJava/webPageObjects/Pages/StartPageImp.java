package fileReaderJava.webPageObjects.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StartPageImp implements StartPage {


    private final WebDriver driver;

    public StartPageImp(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    /*
     * Will match the string "Get vehicle information from DVLA"
     */
    public String getPageHeading () {
        return this.driver.findElement(By.className("pub-c-title__text")).getText();
    }


}
