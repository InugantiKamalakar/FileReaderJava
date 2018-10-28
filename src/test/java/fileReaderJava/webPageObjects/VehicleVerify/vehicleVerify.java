package fileReaderJava.webPageObjects.VehicleVerify;

import fileReaderJava.webPageObjects.Pages.*;
public interface vehicleVerify {

    public Page enterRegNo(String registrationNumber) throws Exception;

    /**
     * Enter invalid formatted registration number
     * @param registrationNumber
     * @return
     * @throws Exception
     */
    public Page enterInvalidFormatRegNo(String registrationNumber) throws Exception;

    /**
     * Enter valid registration number and return information on vehicle details page
     * @param registrationNumber
     * @return array of information on page
     */
    public String[] checkDetails(String registrationNumber) throws Exception;


    /**
     * Enter valid but incorrect registration number and return error page
     * @param registrationNumber
     * @return
     * @throws Exception
     */
    public ErrPage incorrectRegNo(String registrationNumber) throws Exception;

    /**
     * Close the browser
     */
    public void quit();






}
