package edu.neu.csye7374;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * WriteData Class - Writes a list of strings to a file, each on a new line.
 * Uses BufferedWriter for efficient writing and ensures proper resource management.
 */
public class WriteData {

	private final File file;

    public WriteData(String fileName) {
        this.file = new File(fileName);
    }

    /**
     * Writes a list of strings to the file, each on a new line.
     *
     * @param programData List of strings to be written to the file
     * @throws IOException if the file cannot be written
     */
    public void writeData(List<String> programData) throws IOException {
        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(file))) {
            for (String line : programData) {
            	bWriter.write(line + "\n");
            }
        }catch (IOException e){
            throw new IOException("File not found");
        }
    }
}
