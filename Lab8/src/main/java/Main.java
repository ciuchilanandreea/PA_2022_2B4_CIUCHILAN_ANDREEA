import java.sql.*;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {
        Singleton.getInstance();

        CountriesDao CountriesList=new CountriesDao();
        CountriesList.insert(new String[]{"4","Franta","Europa","383"});
        CountriesList.deleteAll();
        CountriesList.insert(new String[]{"1","Indonezia","Asia","764"});
        CountriesList.insert(new String[]{"2","Canada","America de Nord","763"});
        CountriesList.insert(new String[]{"3","Grecia","Europa","556"});
        CountriesList.insert(new String[]{"4","Grecia1","Europa","556"});

        ContinentsDao ContinentsList=new ContinentsDao();
        ContinentsList.deleteAll();
        ContinentsList.insert(new String[]{"1","Europa"});
        ContinentsList.insert(new String[]{"2","Asia"});
        ContinentsList.insert(new String[]{"3","America de Nord"});
        System.out.println(ContinentsList.toString());


        Singleton.closeConn();

    }

}
