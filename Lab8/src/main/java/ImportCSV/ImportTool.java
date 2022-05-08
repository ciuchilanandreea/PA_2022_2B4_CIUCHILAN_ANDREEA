package ImportCSV;

import Connection.ConnectionProvider;
import DatasetManager.EntityType;
import Entities.City;
import Entities.Country;
import DatasetManager.CsvTool;
import DatasetManager.CityParser;
import DatasetManager.CountryParser;


import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

/**
 * Class manages a tool for importing data from csv and inserting into data base
 */
public class ImportTool {
    private final String path;
    private final ConnectionProvider connectionProvider;
    private int entries = 0;
    private int MAX_ENTRIES = 2000;
    private static EntityType type;
    private static CityParser cityParser;
    private static CountryParser countryParser;
    public static String outputPath = "web\\report.html";

    /**
     * Constructor
     * @param path
     * @throws ClassNotFoundException
     */
    public ImportTool(String path) throws ClassNotFoundException {
        this.path = path;
        connectionProvider = ConnectionProvider.connect();
    }

    /**
     * Method sets max entries
     * @param maxEntries
     */
    public void setMaxEntries(int maxEntries){
        MAX_ENTRIES = maxEntries;
    }

    /**
     * Method imports from csv and inserts into db
     * @throws SQLException
     */
    public void importFromCsv() throws SQLException {
        CsvTool csvParser = new CsvTool(this.path);
        csvParser.setMaxEntries(MAX_ENTRIES);
        List<String[]> data = csvParser.getData();
        if (path.contains("countries")) {
            type = EntityType.countries;
            countryParser = new CountryParser(data);
            List<Country> countries = countryParser.parse();
            for (Country country : countries) {
                if(entries == MAX_ENTRIES) {
                    break;
                }
                connectionProvider.insert("countries", country);
                connectionProvider.insert("continent",country, country.getContinent());
                entries++;
            }
        }else if(path.contains("cities")){
            type = EntityType.cities;
          cityParser = new CityParser(data);
           List<City> cities = cityParser.parse();
           for(City city : cities){
               if(entries == MAX_ENTRIES){
                   break;
               }
               connectionProvider.insert("cities",city);
               entries++;
           }
        }
    }

    /**
     * Method creates HTML report using Velocity Marker
     * @throws IOException
     */
    public static void createReport() throws IOException {
        String template;
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        VelocityContext vc = new VelocityContext();

        if(type == EntityType.cities){
            template = "web\\general-template.vm";
            vc.put("name","Cities");
            vc.put("objs", cityParser.getCityManager().getAllCities());
        }else{
            template = "web\\country-template.vm";
            vc.put("name","Countries");
            vc.put("objs", countryParser.getCountryManager().getAllCountries());
        }



        Writer writer = new FileWriter(new File(outputPath));
        Velocity.mergeTemplate(template,"UTF-8",vc,writer);
        writer.flush();
        writer.close();
        System.out.println("Generated HTML report at "+ outputPath);
    }
}
