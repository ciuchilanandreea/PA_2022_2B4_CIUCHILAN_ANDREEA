package DAOs;

import Entities.City;

import java.util.List;
/**
 * CityDao is a Data Access Object interface, that contains methods for finding and adding cities
 */
public interface CityDao {
    /**
     * Method returns all cities
     * @return cities
     */
    List<City> getAllCities();

    /**
     * Method finds a city by it's id
     * @param id
     * @return city
     */
    City getCity(int id);
    /**
     * Method finds a city by it's name
     * @param name
     * @return city
     */
    City getCity(String name);

    /**
     * Method adds a city
     * @param city
     */
    void addCity(City city);

    /**
     * Method deletes a city
     * @param city
     */
    void deleteCity(City city);
}
