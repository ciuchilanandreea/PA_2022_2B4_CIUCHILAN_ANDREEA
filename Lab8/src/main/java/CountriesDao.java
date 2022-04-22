import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CountriesDao implements Dao<Countries> {

    private List<Countries> countries = new ArrayList<>();

    public CountriesDao() {
        Connection conn=Singleton.getConn();
        Statement stmt = null;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM Countries"; //adds all countries from database to the list
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next( )){
                String id = rs.getString("id");
                String name = rs.getString("name");
                String continent = rs.getString("continent");
                int code=rs.getInt("code");

                Countries newCountries= new Countries();
                newCountries.setId(id);
                newCountries.setName(name);
                newCountries.setContinent(continent);
                newCountries.setCode(code);
                countries.add(newCountries);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public Countries get(long id) {
        return countries.get((int) id);
    }

    @Override
    public List<Countries> getAll() {
        return countries;
    }

    @Override
    public void save(Countries countries) {
        this.countries.add(countries);
    }

    @Override
    public void update(Countries countries) {

    }

    @Override
    public void insert(String[] params) throws SQLException, ParseException {
        Countries countries = new Countries();
        countries.setId(Objects.requireNonNull(
                params[0], "ID cannot be null"));
        countries.setName(Objects.requireNonNull(
                params[1], "Name cannot be null"));
        countries.setContinent(Objects.requireNonNull(
                params[2], "Continent cannot be null"));
        countries.setCode(Integer.parseInt(params[3]));

        this.countries.add(countries);
        Connection conn = Singleton.getConn();
        String sql = "INSERT INTO Countries VALUES(?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, countries.getId());
        pstmt.setString(2, countries.getName());
        pstmt.setString(3, countries.getContinent());
        pstmt.setInt(4, countries.getCode());
        pstmt.executeUpdate();
        pstmt.close();
    }

    @Override
    public void delete(String id, String name) throws SQLException {

        for (int i = 0, countriesSize = countries.size(); i < countriesSize; i++) {
            Countries m = countries.get(i);
            if (m.getId().equals(id) && m.getName().equals(name)) {
                countries.remove(m);
            }
        }
            Connection conn = Singleton.getConn();
            String sql = "DELETE FROM Countries WHERE id=? AND name=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            try {
                pstmt.setString(1, id);
                pstmt.setString(2, name);
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


    }
    @Override
    public void deleteAll(){
        Connection conn = Singleton.getConn();
        Statement stmt = null;
        String sql = "DELETE FROM Countries";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            if(countries.size()!=0) {
                for (int i = 0; i< countries.size(); i++) {
                    countries.remove(countries.get(i));
                    i--;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    @Override
    public String toString() {
            return "CountriesDao{" +
                    "countries=" + countries +
                    '}';
    }

}
