package DAOs;

import Entities.Country;

import java.util.List;

/**
 * Class CountryDao is a Data Access Model class and contains methods for finding and adding countries.
 */
public interface CountryDao {
     /**
      * Method returns all countries
      * @return countries
      */
     List<Country> getAllCountries();

     /**
      * Method finds country by it's id
      * @param id
      * @return country
      */
     Country getCountry(int id);

     /**
      * Method finds country by it's name
      * @param name
      * @return country
      */
     Country getCountry(String name);

     /**
      * Method adds country
      * @param country
      */
     void addCountry(Country country);

     /**
      * Method deletes country
      * @param country
      */
     void deleteCountry(Country country);
}
