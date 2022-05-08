package Entities;

public abstract class Person {
    private int id;
    private String name;

    /**
     * Constructor
     * @param id
     * @param name
     */
    public Person(int id, String name){
        this.id = id;
        this.name = name;
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
     * Method sets name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
