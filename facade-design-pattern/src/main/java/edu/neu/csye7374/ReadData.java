package edu.neu.csye7374;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ReadData Class - Reads data from a file line by line and stores it in a list of strings.
 * Uses BufferedReader for efficient reading and handles IO exceptions.
 */
public class ReadData {
	
	private final File file;

    public ReadData(String fileName) {
        this.file = new File(fileName);
    }

    /**
     * Reads data from the file line by line and stores it in a list.
     * 
     * @return List of strings, each representing a line from the file
     * @throws IOException if the file is not found or cannot be read
     */
    public List<String> readData() throws IOException {
    	String line = null;
        List<String> programData = new ArrayList<>();
        try (BufferedReader bReader = new BufferedReader(new FileReader(file))) {
            while ((line = bReader.readLine()) != null) {
                programData.add(line);
            }
        } catch (IOException e) {
            throw new IOException("File not found");
        }
        return programData;
    }
}
