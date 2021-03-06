package fileReaderJava.webPageObjects.VehicleVerify;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import fileReaderJava.webPageObjects.Pages.*;

public class ChromeVehicleVerify implements vehicleVerify {

    private WebDriver driver;
    private static String WEBSITEURL = "https://www.gov.uk/get-vehicle-information-from-dvla";


    public ChromeVehicleVerify () {
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
    }

    /**
     * Enter reg degails and then ensure you get to the next page
     * @param registrationNumber
     * @return
     * @throws Exception
     */
    @Override
    public Page enterRegNo(String registrationNumber) throws Exception {
        WebDriverWait wait = doInitialActions(registrationNumber);

        WebElement newPageElement = this.driver.findElement(By.className("back"));
        wait.until(ExpectedConditions.visibilityOf(newPageElement));
        return new VehicleVerifyPageImp(driver);
    }

    @Override
    public Page enterInvalidFormatRegNo(String registrationNumber) throws Exception {
        WebDriverWait wait = doInitialActions(registrationNumber);

        WebElement errorElement = this.driver.findElement(By.id("Vrm-error"));
        wait.until(ExpectedConditions.visibilityOf(errorElement));
        return new RegNoEnterPageImp(driver);

    }

    @Override
    public String[] checkDetails(String registrationNumber) throws Exception {
        WebDriverWait wait = doInitialActions(registrationNumber);

        WebElement newPageElement =  this.driver.findElement(By.className("back"));
        wait.until(ExpectedConditions.visibilityOf(newPageElement));
        VehicleVerifyPage vehicleCheckPage = new VehicleVerifyPageImp(driver);
        return vehicleCheckPage.getVehicleInfo();
    }

    @Override
    public ErrPage incorrectRegNo(String registrationNumber) throws Exception {

        WebDriverWait wait = doInitialActions(registrationNumber);

        WebElement newPgElement = this.driver.findElement(By.xpath("//div/p/strong"));
        wait.until(ExpectedConditions.visibilityOf(newPgElement));
        WebElement newPageElement =  this.driver.findElement(By.className("heading-large"));
        wait.until(ExpectedConditions.visibilityOf(newPageElement));
        ErrPage errorPage = new ErrPageImp(driver);
        return errorPage;
    }

    @Override
    public void quit() {
        this.driver.quit();
    }

    private WebDriverWait doInitialActions(String registrationNumber) {
        this.driver.get(WEBSITEURL);
        //this.driver.findElement(By.className("pub-c-title__text"));
        this.driver.findElement(By.linkText("Start now")).click();
        WebDriverWait wait = new WebDriverWait(driver, 200);
        WebElement regPageTitleElement = this.driver.findElement(By.className("heading-large"));
        wait.until(ExpectedConditions.visibilityOf(regPageTitleElement));
        this.driver.findElement(By.id("Vrm")).sendKeys(registrationNumber);
        this.driver.findElement(By.name("Continue")).submit();
        return wait;
    }


}
