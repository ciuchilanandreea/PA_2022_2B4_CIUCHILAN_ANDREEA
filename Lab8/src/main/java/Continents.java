public class Continents {
    private String id, name;
    public Continents(String id, String name) {
        this.id=id;
        this.name=name;
    }

    public Continents() {
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Continents{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

