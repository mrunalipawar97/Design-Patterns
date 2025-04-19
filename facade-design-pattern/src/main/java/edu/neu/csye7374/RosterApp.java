package edu.neu.csye7374;

import java.util.ArrayList;
import java.util.List;

public class RosterApp {
    private final static String fileName = "studentRoster.txt";

    public static void demo(){
        List<String> programData = new ArrayList<>();
        programData.add(new Student("Sri", "Vijay", "002345678").toString());
        programData.add(new Student("Yesha", "Joshi", "002345897").toString());
        programData.add(new Student("Daniel", "Peters", "002345367").toString());

        FileFacadeAPI fileFacade = new FileFacade(fileName);

        System.out.println("Saving data to file");
        fileFacade.save(programData);

        System.out.println("Loading data from file");
        List<String> loadedData = fileFacade.programDataLoad();

        System.out.println("Printing loaded data");
        loadedData.forEach(str -> {
            String[] studentData = str.split(",");
            System.out.println("First Name: " + studentData[0] + " || Last Name: " + studentData[1] + " || Student ID: " + studentData[2]);
        });
        Integer.valueOf("1");

    }
}
