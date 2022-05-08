package EntityManager;

import DAOs.CityDao;
import Entities.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Method manages City objects in current session
 */
public class CityManager implements CityDao {
    private final List<City> cities;

    /**
     * Constructor
     */
    public CityManager(){
        cities = new ArrayList<>();
    }

    /**
     * Method returns cities
     * @return cities
     */
    @Override
    public List<City> getAllCities() {
        return cities;
    }

    /**
     * Method finds city by id
     * @param id
     * @return city
     */
    @Override
    public City getCity(int id) {
        for(City city : cities){
            if(city.getId() == id){
                return city;
            }
        }
        return null;
    }

    /**
     * Method finds city by name
     * @param name
     * @return city
     */
    @Override
    public City getCity(String name) {
        for(City city : cities){
            if(city.getName().equals(name)){
                return city;
            }
        }
        return null;
    }

    /**
     * Method adds city
     * @param city
     */
    @Override
    public void addCity(City city) {
        if(getCity(city.getId())==null){
            cities.add(city);
        }
    }

    /**
     * Method deletes city
     * @param city
     */
    @Override
    public void deleteCity(City city) {
        if(getCity(city.getId())!=null){
            cities.remove(city);
        }

    }
}
