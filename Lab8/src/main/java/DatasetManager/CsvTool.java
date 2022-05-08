package DatasetManager;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class reads and parses CSV
 */
public class CsvTool {
    private final String path;
    private final List<String[]> data;
    private int MAX_ENTRIES = 100;

    /**
     * Constructor
     * @param path
     */
    public CsvTool(String path) {
        this.path = path;
        this.data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            List<String[]> tmp = reader.readAll();
            tmp.forEach(row -> data.add(row));
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        data.remove(0);
    }

    /**
     * Method sets maximum of entries
     * @param entries
     */
    public void setMaxEntries(int entries){
        this.MAX_ENTRIES = entries;
    }

    /**
     * Method returns parsed data
     * @return data
     */
    public List<String[]> getData(){
        return data.subList(0,MAX_ENTRIES);
    }

}
