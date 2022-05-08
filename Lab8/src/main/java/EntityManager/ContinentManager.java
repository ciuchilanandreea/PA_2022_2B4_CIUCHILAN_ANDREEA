package EntityManager;

import DAOs.ContinentDao;
import Entities.Continent;

import java.util.ArrayList;
import java.util.List;

/**
 * Class ContinentManager implements ContinentDao interface
 */
public class ContinentManager implements ContinentDao {
    List<Continent> continents;
    int lastIndex = 1;

    /**
     * Constructor
     */
    public ContinentManager() {
        continents = new ArrayList<>();
    }

    /**
     * Method returns all continents
     * @return continents
     */
    @Override
    public List<Continent> getAllContinents() {
        return continents;
    }

    /**
     * Method finds continent by id
     * @param id
     * @return continent
     */
    @Override
    public Continent getContinent(int id) {
        for (Continent continent : continents) {
            if (id == continent.getId()) {
                return continent;
            }
        }
        return null;
    }

    /**
     * Method finds continent by name
     * @param name
     * @return continent
     */
    @Override
    public Continent getContinent(String name){
        for(Continent continent : continents){
            if(name.equals(continent.getName())){
                return continent;
            }
        }
        return null;
    }

    public int getLastIndex(){
        return lastIndex;
    }

    /**
     * Method adds continent
     * @param continent
     */
    @Override
    public void addContinent(Continent continent) {
        if (getContinent(continent.getId()) == null) {
            continents.add(continent);
            lastIndex++;
        }
    }

    /**
     * Method deletes continent
     * @param continent
     */
    @Override
    public void deleteContinent(Continent continent) {
        if(getContinent(continent.getId())!=null){
            continents.remove(continent);
        }
    }
}
