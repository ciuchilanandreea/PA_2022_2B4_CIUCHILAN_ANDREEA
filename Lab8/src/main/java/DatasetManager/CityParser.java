package DatasetManager;
import Entities.City;
import EntityManager.CityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class parses data from CSV and creates City objects
 */
public class CityParser {
    private final List<String[]> data;
    private final List<City> cities;
    private final CityManager cityManager;

    /**
     * Constructor
     * @param data
     */
    public CityParser(List<String[]> data) {
        this.data = data;
        cities = new ArrayList<>();
        cityManager = new CityManager();
    }

    /**
     * Method creates City object from row
     * @param row
     * @return City
     */
    private City getCity(String[] row) {
        int id = DatasetManager.Utils.getId(row[0]);
        String name = row[1];
        City city = new City(id, name);
        cityManager.addCity(city);
        return city;
    }

    /**
     * Method parses every row to create City objects
     * @return List of city objects
     */
    public List<City> parse(){
        for(String[] row : data){
            cities.add(getCity(row));
        }
        return cities;
    }

    /**
     * Method returns cityManager
     * @return cityManager
     */
    public CityManager getCityManager(){
        return cityManager;
    }
}
