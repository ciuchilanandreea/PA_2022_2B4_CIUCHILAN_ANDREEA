package DAOs;

import Entities.Continent;

import java.util.List;

/**
 * ContinentDao is a Data Access Object interface, that contains methods for finding and adding continents
 */
public interface ContinentDao {
    /**
     * Method returns all continents
     * @return continents
     */
    List<Continent> getAllContinents();

    /**
     * Method finds a continent by it's id
     * @param id
     * @return continent
     */
    Continent getContinent(int id);
    /**
     * Method finds a continent by it's name
     * @param name
     * @return continent
     */
    Continent getContinent(String name);

    /**
     * Method adds a continent
     * @param continent
     */
    void addContinent(Continent continent);

    /**
     * Method deletes a continent
     * @param continent
     */
    void deleteContinent(Continent continent);

    int getLastIndex();
}
