package Connection;

import Entities.City;
import Entities.Director;
import Entities.Continent;
import Entities.Country;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class manages a connection to the DB using Hikari CP
 */
public class ConnectionPool {
    private static ConnectionPool connection;
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;
    private static Connection conn;
    public File destination;
    static {
        try {
            connection = new ConnectionPool();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Constructor: creates an unique connection to the database
     *
     * @throws ClassNotFoundException
     */
    private ConnectionPool() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try {
            initializeDB();
            config.setJdbcUrl("jdbc:sqlite:database\\countries-database.db");
            dataSource = new HikariDataSource(config);
            conn = dataSource.getConnection();
            System.out.println("CONNECTION : " + conn);
            System.out.println("Connection to the database has been established.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method initializes database(creates an empty one)
     * @throws IOException
     */
    private void initializeDB() throws IOException {
        File source = new File("database\\template.db");
        destination = new File("database\\countries-database.db");
        Files.copy(source.toPath(),destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Initialized empty db");
    }
    /**
     * Method returns the current connection
     *
     * @return current connection
     * @throws ClassNotFoundException
     */
    public static ConnectionPool connect() throws ClassNotFoundException {
        if (connection == null) {
            connection = new ConnectionPool();
        }
        return connection;
    }

    /**
     * Method inserts in db
     * @param tableName
     * @param object
     * @throws SQLException
     */
    public void insert(String tableName, Object object) throws SQLException {
        String query;
        PreparedStatement stmt = null;
        if (object instanceof Country) {
            query = "INSERT INTO " + tableName + " VALUES (?,?,?,?,?);";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, ((Country) object).getId());
            stmt.setString(2, ((Country) object).getName());
            stmt.setString(3, ((Country) object).getCountryContinent());
            stmt.setInt(4, ((Country) object).getCode());
        } else if (object instanceof Continent) {
            query = "INSERT INTO " + tableName + " VALUES (?,?);";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, ((Continent) object).getId());
            stmt.setString(2, ((Continent) object).getName());
        } else if (object instanceof City) {
            query = "INSERT INTO " + tableName + " VALUES (?,?);";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, ((City) object).getId());
            stmt.setString(2, ((City) object).getName());
        } else if (object instanceof Director) {
            query = "INSERT INTO " + tableName + " VALUES (?,?);";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, ((Director) object).getId());
            stmt.setString(2, ((Director) object).getName());
        }

        try {
            System.out.println("Executing insert...");
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Method inserts in associative table continents
     * @param tableName
     * @param country
     * @param continent
     * @throws SQLException
     */
    public void insert(String tableName, Country country, Continent continent) throws SQLException {
        String query = "INSERT INTO Continents VALUES (?,?);";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, country.getId());
        stmt.setInt(2, continent.getId());
        try {
            System.out.println("Executing insert...");
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Method performs select operation on db
     * @param tableName
     * @return result
     * @throws SQLException
     */
    public List<String> select(String tableName) throws SQLException {
        String query = "SELECT * FROM " + tableName + ";";
        System.out.println("Executing query : " + query);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List<String> result = new ArrayList<>();
        switch (tableName) {
            case "countries":
                while (rs.next()) {
                    result.add(rs.getString("id") + "," + rs.getString("name") + "," + rs.getString("continent") + "," + "," + rs.getString("code"));
                }
                break;
            case "continents":
            case "cities":
            case "directors":
                while (rs.next()) {
                    result.add(rs.getString("id") + "," + rs.getString("name"));
                }
                break;
        }
        return result;
    }


    /**
     * Method returns all tables in db
     * @return tables
     * @throws SQLException
     */
    public List<String> seeAllTables() throws SQLException {
        ResultSet rs = conn.getMetaData().getTables(null,null,null,null);
        List<String> tables = new ArrayList<>();
        while(rs.next()){
            tables.add(rs.getString("TABLE_NAME"));
        }
        return tables;
    }

    /**
     * Method performs select * from table where [clause].
     * @param tableName
     * @param what
     * @param field
     * @param value
     * @return result
     * @throws SQLException
     */
    public List<String> selectWhere(String tableName, String what, String field, String value) throws SQLException {
        String query = "SELECT " + what + " FROM " + tableName + " WHERE " + field + " = " + "'" + value + "'" + ";";
        System.out.println("Executing query : " + query);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List<String> result = new ArrayList<>();
        while(rs.next()){
            result.add(rs.getString(what));
        }
        return result;
    }

}