import java.sql.*;

public class Singleton {
    private static Singleton single_instance = null;
    private static Connection conn=null;
    private Singleton() throws SQLException, ClassNotFoundException
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        try{
            conn = DriverManager.getConnection(
                    url, "STUDENT", "STUDENT");
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.println("Cannot connect to DB: " + e);
        }
}


    public static Singleton getInstance() throws SQLException, ClassNotFoundException {
        if (single_instance == null)
            single_instance = new Singleton();

        return single_instance;
    }

    public static Connection getConn() {
        return conn;
    }
    public static void closeConn(){
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            System.out.println("Connection closed.");
        }
    }
}
