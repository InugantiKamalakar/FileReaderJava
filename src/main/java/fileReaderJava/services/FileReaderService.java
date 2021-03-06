package fileReaderJava.services;

import fileReaderJava.details.FileDetails;
import fileReaderJava.details.VehicleDetails;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

/**
 - @author Kamalakar Inuganti
 - Interface to read files in a directory and return information from
 - the files
 */
public interface FileReaderService {

    /***
     - Stores the details of a file from a particular path in a list of fileDetails
     - only files which have mimetypes for csv and excel are currently stored
     - @param path
     - @return List of fileDetails
     */
    public List<FileDetails> getFileContents(String path);

    /**
     - Get the file details of a named file
     - @param filename
     - @return fileDetails of a file
     */
    public FileDetails getFileDetailsFromFile(String filename);

    /**
     - Get the vehicle details stored in a file
     - @param filename
     - @return vehicle details in a file
     */
    public VehicleDetails getVehicleDetailsFromFile(String filename);

}
