package GUI;

import CustomExceptions.AccessDenied;
import CustomExceptions.IllegalNumberOfArgs;
import GUI.Commands.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Utils {

    /**
     * Method parses for command name in command
     * @param string
     * @return command
     */
    private static String getCommand(String string){
        int index = string.indexOf(" ");
        if(index == -1){
            return string;
        }
        return string.substring(0,index);
    }


    /**
     * Method decides the appropriate action for each command
     * @param input
     * @return result
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws IllegalNumberOfArgs
     * @throws AccessDenied
     */
    public static String processCommand(String input) throws SQLException, ClassNotFoundException, IOException, IllegalNumberOfArgs, AccessDenied {
        String command = getCommand(input);
        final String substring = input.substring(input.lastIndexOf(" ") + 1, input.length());
        switch (command) {
            case "ls" -> {
                if(input.lastIndexOf(" ") == -1) {
                    ListTables listTables = new ListTables();
                    return listTables.execute();
                }else{
                    if(input.contains("where")){
                       ListWhere listWhere = new ListWhere();
                       listWhere.setParameters(input.split(" ")[1],input.split(" ")[3],input.split(" ")[5]);
                       return listWhere.execute();

                    }
                    ListTable listTable = new ListTable();
                    return listTable.execute();
                }
            }
            case "cd" -> {
                ChangeDirectory changeDirectory = new ChangeDirectory();
                changeDirectory.setParameter(substring);
                return changeDirectory.execute();
            }
            case "exit" -> {
               Exit exit = new Exit();
               exit.execute();
            }
            case "import" ->{
                Import importCommand = new Import();
                importCommand.setParameter(substring);
                return importCommand.execute();
            }
            case "cp" ->{
                Insert insert = new Insert();
                List<String> parameters = Arrays.asList(input.split(" "));
                parameters = parameters.subList(1,parameters.size());
                insert.setParameters(parameters);
                return insert.execute();
            }
            case "report" -> {
                Report report = new Report();
                return report.execute();
            }
            case "save" ->{
                Save save = new Save();
                save.setParameter(substring);
                return save.execute();
            }
            case "thank" ->{
                return "Welcome!";
            }
            case "help" ->{
                String help = """
                        --COMMANDS--
                        ls                         : see all tables
                        ls *                       : select * from current table
                        cp [params]                : insert into current table
                        import [path]              : import data from .csv
                        report                     : create report
                        save [path]                : save db to location
                        thank you                  : express your appreciation
                        exit                       : end session
                        cd                         : enter table
                        ls [A] where [B] = [value] : select A from table where B=value
                        """;
                return help;
            }
        }
        return "ERROR: No such command! Type help to see what you can do!";
    }


}
