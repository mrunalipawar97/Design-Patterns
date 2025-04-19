package edu.neu.csye7374;

import java.util.List;

public interface FileFacadeAPI {

    void save(List<String> programData);

    List<String> programDataLoad();
}
