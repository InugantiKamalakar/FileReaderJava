package fileReaderJava.javaFileReaderTests;

import fileReaderJava.creation.MimeType;

import fileReaderJava.details.FileDetails;
import fileReaderJava.details.VehicleDetails;

import fileReaderJava.services.FileReaderService;
import fileReaderJava.services.FileReaderServiceImpl;

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

public class javaFileReaderImpTest {

    @Rule
    public TemporaryFolder folderLevel1 = new TemporaryFolder();

    @Rule
    public TemporaryFolder folderLevel3 = new TemporaryFolder();
    private File file1;
    private File file2;
    private File file3;
    private File file4;
    private File file5;
    private File levelOneFolder;
    private File levelTwoFolder;
    @Rule
    public TemporaryFolder emptyFolder = new TemporaryFolder();

    private FileReaderService fileReaderService;

    private static String MIMETYPE_TXT = "text/plain";

    @Before
    public void setUp() throws Exception {

        file1 = folderLevel3.newFile("samplefile1.csv");
        levelOneFolder = folderLevel3.newFolder("levelOneFolder");
        levelTwoFolder = folderLevel3.newFolder("levelOneFolder","levelTwoFolder");
        file2 = folderLevel3.newFile("levelOneFolder/samplefile2.csv");
        file3 = folderLevel3.newFile("levelOneFolder/levelTwoFolder/samplefile3.csv");
        file4 = folderLevel3.newFile("levelOneFolder/levelTwoFolder/samplefile4.csv");
        file5 = folderLevel3.newFile("levelOneFolder/levelTwoFolder/samplefile5.xls");

        FileWriter fileWriter = new FileWriter(file1);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("EX62FWC,NISSAN,RED");
        printWriter.close();


        FileWriter fileWriter3 = new FileWriter(file3);
        PrintWriter printWriter3 = new PrintWriter(fileWriter3);
        printWriter3.print("EN17BYJ,AUDI,WHITE");
        printWriter3.close();

        FileWriter fileWriter5 = new FileWriter(file5);
        PrintWriter printWriter5 = new PrintWriter(fileWriter5);
        printWriter5.print("EX66FWT, NISSAN, RED");
        printWriter5.close();

        fileReaderService = new FileReaderServiceImpl();
    }


     @Test
    public void testEmptyDirectory() throws Exception {
        List<FileDetails> fileDetailsList = fileReaderService.getFileContents(emptyFolder.getRoot().getPath());
        assertEquals(0, fileDetailsList.size());
    }

    @Test
    public void testMimeTypeCSV () throws Exception {
        List<FileDetails> fileDetailsList = fileReaderService.getFileContents(folderLevel3.getRoot().getPath());
        FileDetails fileDetails = fileReaderService.getFileDetailsFromFile("samplefile1.csv");
        assertEquals(MimeType.CSV,fileDetails.getMimeType());
    }

      @Test
    public void testMimeTypeExcel() throws Exception {
        List<FileDetails> fileDetailsList = fileReaderService.getFileContents(folderLevel3.getRoot().getPath());
        FileDetails fileDetails = fileReaderService.getFileDetailsFromFile("samplefile5.xls");
        assertEquals(MimeType.EXCEL,fileDetails.getMimeType());
    }


    @Test
    public void testVehicleDetailsAddedCSV() throws Exception {
        List<FileDetails> fileDetailsList = fileReaderService.getFileContents(folderLevel3.getRoot().getPath());
        VehicleDetails vehicleDetails = fileReaderService.getVehicleDetailsFromFile("samplefile3.csv");
        assertEquals("EN17BYJ", vehicleDetails.getRegNo());
        assertEquals("AUDI", vehicleDetails.getMake());
        assertEquals("WHITE", vehicleDetails.getColour());
    }

    @Test
    public void testVehicleDetailsAddedXLS() throws Exception {
        List<FileDetails> fileDetailsList = fileReaderService.getFileContents(folderLevel3.getRoot().getPath());
        VehicleDetails vehicleDetails = fileReaderService.getVehicleDetailsFromFile("samplefile5.xls");
        assertEquals("EX66FWT", vehicleDetails.getRegNo());
        assertEquals("NISSAN", vehicleDetails.getMake());
        assertEquals("RED", vehicleDetails.getColour());
    }







}
