package Entities;

/**
 * Class Country models a continent
 */
public class Country {
    private int id;
    private String name;
    private String countrycontinent;
    private int code;
    private Continent continent;

    /**
     * Constructor
     * @param id
     * @param name
     * @param countrycontinent
     * @param code
     */
    public Country(int id, String name, String countrycontinent, int code) {
        this.id = id;
        this.name = name;
        this.countrycontinent = countrycontinent;
        this.code = code;
    }

    /**
     * Method returns id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Method sets id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method returns name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method sets the name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method returns release continent
     * @return release continent
     */
    public String getCountryContinent() {
        return countrycontinent;
    }

    /**
     * Method sets the release continent
     * @param countrycontinent
     */
    public void setCountrycontinent(String countrycontinent) {
        this.countrycontinent = countrycontinent;
    }


    /**
     * Method returns code
     * @return code
     */
    public int getCode() {
        return code;
    }

    /**
     * Method sets the code
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }


    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }



}
