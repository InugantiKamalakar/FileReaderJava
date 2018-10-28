package fileReaderJava.cucumberTests;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fileReaderJava.details.VehicleDetails;
import fileReaderJava.details.FileDetails;
import fileReaderJava.creation.MimeType;
import fileReaderJava.webPageObjects.VehicleVerify.ChromeVehicleVerify;
import fileReaderJava.webPageObjects.Pages.ErrPage;
import fileReaderJava.webPageObjects.Pages.Page;
import fileReaderJava.webPageObjects.VehicleVerify.vehicleVerify;
import fileReaderJava.webPageObjects.Pages.RegNoEnterPage;
import fileReaderJava.services.FileReaderService;
import fileReaderJava.services.FileReaderServiceImpl;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class JavaServiceRunSteps {

    private FileReaderService fileReaderService;
    private List<FileDetails> fileDetailsList;
    private vehicleVerify VehicleVerify;
    private Page page;
    private String[] vehicleInfo;
    private String fileName;
    private VehicleDetails vehicleDetails;

    private static String DIRECTORY = "C:\\Home\\Kamalakar\\Documents\\Java";

    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\kamalakar.inuganti\\Chromedriver\\chromedriver.exe");
    }

    @Given("^the service has (-?\\d+) files to read in directory (.*)$")
    public void testServiceReadsDirectory (int number, String path) throws Exception {
        fileReaderService = new FileReaderServiceImpl();
        fileDetailsList = fileReaderService.getFileContents(DIRECTORY + path);
    }

    @Given("^the service reads files from directory testfiles$")
    public void testServiceReadsDirectoryTestfiles1() throws Exception {
        fileReaderService = new FileReaderServiceImpl();
        fileDetailsList = fileReaderService.getFileContents(DIRECTORY + "testfiles");
    }

    @When("^the service is called$")
    public void testServiceIsEvoked () throws Exception {
        VehicleVerify = new ChromeVehicleVerify();
    }

    @And("^the registration number (.*) from (.*) is entered into the website$")
    public void testCorrectNumberEntered (String plate, String file) throws Exception {
        vehicleInfo = VehicleVerify.checkDetails(plate);
    }

    @And("^registration numbers from each file with (.*) is entered into the website inturn$")
    public void testRegistrationNumbersFromEachFile (String name) throws Exception {
        this.fileName = name;
    }
    @Then("^the website details will match the service with make of AUDI with a colour of WHITE$")
    public void testWebsiteDetails () throws Exception {
        assertEquals(vehicleInfo[1], "AUDI");
        assertEquals(vehicleInfo[2], "WHITE");
    }

}
