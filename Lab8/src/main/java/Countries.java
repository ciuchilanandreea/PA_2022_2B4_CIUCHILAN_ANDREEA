public class Countries {
    private String id, name,continent;
    private int code;
    public Countries(String id, String name,  String continent, int code) {
        this.id=id;
        this.name= name;
        this.code= code;
        this.continent=continent;
    }

    public Countries() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setCode(int code) {
        this.code= code;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getContinent() {
        return continent;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Countrie{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", code=" + code +
                '}';
    }
}

