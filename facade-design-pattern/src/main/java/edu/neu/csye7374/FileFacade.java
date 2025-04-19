package edu.neu.csye7374;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileFacade implements FileFacadeAPI{

    private final ReadData readData;
    private final WriteData writeData;

    public FileFacade(String fileName) {
        this.readData = new ReadData(fileName);
        this.writeData = new WriteData(fileName);
    }

    @Override
    public void save(List<String> programData)  {
        try {
            writeData.writeData(programData);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public List<String> programDataLoad() {
        try {
            return readData.readData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return new ArrayList<>();
    }
}
