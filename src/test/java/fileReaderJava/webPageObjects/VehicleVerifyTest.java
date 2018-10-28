package fileReaderJava.webPageObjects;

import fileReaderJava.webPageObjects.VehicleVerify.ChromeVehicleVerify;

import fileReaderJava.webPageObjects.Pages.ErrPage;
import fileReaderJava.webPageObjects.Pages.Page;
import fileReaderJava.webPageObjects.VehicleVerify.ChromeVehicleVerify;
import fileReaderJava.webPageObjects.VehicleVerify.vehicleVerify;
import fileReaderJava.webPageObjects.Pages.RegNoEnterPage;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class VehicleVerifyTest {

    private vehicleVerify VehicleVerify;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\kamalakar.inuganti\\Chromedriver\\chromedriver.exe");
        VehicleVerify = new ChromeVehicleVerify();
    }


    @Test
    public void testEnquirePage() throws Exception{
        Page page = VehicleVerify.enterRegNo("EN17BYJ");
        assertEquals(page.getPageHeading(), Page.VehicleCheckPage_HEADING);
        VehicleVerify.quit();
    }

    @Test
    public void testVehicleCheckPage () throws Exception {

        String[] vehicleInfo = VehicleVerify.checkDetails("EN17BYJ");
        assertEquals(vehicleInfo[0], "EN17BYJ");
        assertEquals(vehicleInfo[1], "AUDI");
        assertEquals(vehicleInfo[2], "WHITE");
        VehicleVerify.quit();
    }

    @Test
    public void testErrorPage() throws Exception {

        Page page = VehicleVerify.incorrectRegNo("D234BPR");
        ErrPage errorPage = (ErrPage) page;
        assertEquals(errorPage.getPageHeading(), Page.ErrorPage_HEADING);
        assertEquals(errorPage.getErrorContactInfoString(), Page.ErrorPage_CONTACT_INFO);
        VehicleVerify.quit();
    }

    @Test
    public void testInvalidRegNoNumber() throws Exception{

        Page page = VehicleVerify.enterInvalidFormatRegNo("9999999");
        assertEquals(page.getPageHeading(), Page.RegEnterPage_HEADING);
        RegNoEnterPage regEnterPage = (RegNoEnterPage)page;
        assertEquals(regEnterPage.getErrorStringHeading(),Page.RegEnterPage_ERROR_HEADING);
        assertEquals(regEnterPage.getErrorString(), Page.RegEnterPage_ERROR_STRING);
        VehicleVerify.quit();
    }

    @Test
    public void testInvalidRegNoLetters() throws Exception{

        Page page = VehicleVerify.enterInvalidFormatRegNo("ASDFLKJ");
        assertEquals(page.getPageHeading(), Page.RegEnterPage_HEADING);
        RegNoEnterPage regEnterPage = (RegNoEnterPage)page;
        assertEquals(regEnterPage.getErrorStringHeading(),Page.RegEnterPage_ERROR_HEADING);
        assertEquals(regEnterPage.getErrorString(), Page.RegEnterPage_ERROR_STRING);
        VehicleVerify.quit();
    }
}
