package EntityManager;

import DAOs.CountryDao;
import Entities.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Class CountryManager implements the CountryDao interface
 */
public class CountryManager implements CountryDao {
    List<Country> countries;

    /**
     * Constructor
     */
    public CountryManager() {
        countries = new ArrayList<>();
    }


    /**
     * Method returns all countries
     * @return countries
     */
    @Override
    public List<Country> getAllCountries() {
        return countries;
    }

    /**
     * Method find countries by name
     * @param name
     * @return country
     */
    @Override
    public Country getCountry(String name){
        for(Country country : countries){
            if(name.equals(country.getName())){
                return country;
            }
        }
        return null;
    }

    /**
     * Method find countries by id
     * @param id
     * @return country
     */
    @Override
    public Country getCountry(int id) {
        for (Country country : countries) {
            if (id == country.getId()) {
                return country;
            }
        }
        return null;
    }

    /**
     * Method adds a country
     * @param country
     */
    @Override
    public void addCountry(Country country) {
        if (getCountry(country.getId()) == null) {
            countries.add(country);
        }
    }

    /**
     * Method deletes a country
     * @param country
     */
    @Override
    public void deleteCountry(Country country) {
        if(getCountry(country.getId())!=null){
            countries.remove(country);
        }
    }
}
