package DatasetManager;

import Entities.Continent;
import Entities.Country;
import EntityManager.ContinentManager;
import EntityManager.CountryManager;

import java.util.ArrayList;
import java.util.List;
/**
 * Class parses data from CSV and creates Country objects
 */
public class CountryParser {
    private final List<String[]> data;
    private final List<Country> countries;
    private final CountryManager countryManager;
    private final ContinentManager continentManager;

    /**
     * Constructor
     * @param data
     */
    public CountryParser(List<String[]> data) {
        this.data = data;
        countries = new ArrayList<>();
        countryManager = new CountryManager();
        continentManager = new ContinentManager();
    }

    /**
     * Method creates Country object from row
     * @param row
     * @return Country
     */
    private Country getCountry(String[] row) {
        int id = Utils.getId(row[0]);
        String name = row[1];
        String country_continent = row[4];

        int code = (int)Double.parseDouble(row[6]);
        Country country = new Country(id,name,country_continent,code);
        Continent continent = new Continent(continentManager.getLastIndex(),row[5]);
        country.setContinent(continent);
        continentManager.addContinent(continent);
        countryManager.addCountry(country);
        return country;
    }

    /**
     * Method parses every row to create Country objects
     * @return List of country objects
     */
    public List<Country> parse(){
        for(String[] row : data){
            countries.add(getCountry(row));
        }
        return countries;
    }

    /**
     * Method returns countryManager
     * @return countryManager
     */
    public CountryManager getCountryManager(){
        return countryManager;
    }


}
