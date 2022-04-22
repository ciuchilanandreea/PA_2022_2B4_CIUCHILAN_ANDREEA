import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContinentsDao implements Dao<Continents> {

    private List<Continents> continents = new ArrayList<>();

    public ContinentsDao() {
        Connection conn=Singleton.getConn();
        Statement stmt = null;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM Continent"; //adds all continents from database to the list
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next( )){
                String id = rs.getString("id");
                String name = rs.getString("name");

                Continents newContinents= new Continents();
                newContinents.setId(id);
                newContinents.setName(name);
                continents.add(newContinents);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public Continents get(long id) {
        return continents.get((int) id);
    }

    @Override
    public List<Continents> getAll() {
        return continents;
    }

    @Override
    public void save(Continents continents) {
        this.continents.add(continents);
    }

    @Override
    public void update(Continents continents) {

    }

    @Override
    public void insert(String[] params) throws SQLException, ParseException {
        Continents continents = new Continents();
        continents.setId(Objects.requireNonNull(
                params[0], "ID cannot be null"));
        continents.setName(Objects.requireNonNull(
                params[1], "Name cannot be null"));

        this.continents.add(continents);
        Connection conn = Singleton.getConn();
        String sql = "INSERT INTO Continent VALUES(?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, continents.getId());
        pstmt.setString(2, continents.getName());
        pstmt.executeUpdate();
        pstmt.close();
    }

    @Override
    public void delete(String id, String name) throws SQLException {

        for (int i = 0, continentsSize = continents.size(); i < continentsSize; i++) {
            Continents m = continents.get(i);
            if (m.getId().equals(id) && m.getName().equals(name)) {
                continents.remove(m);
            }
        }
        Connection conn = Singleton.getConn();
        String sql = "DELETE FROM Continent WHERE id=? AND name=?";
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
        String sql = "DELETE FROM Continent";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            if(continents.size()!=0) {
                for (int i = 0; i< continents.size(); i++) {
                    continents.remove(continents.get(i));
                    i--;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }




    @Override
    public String toString() {
        return "continentsDao{" +
                "continents=" + continents +
                '}';
    }

}
