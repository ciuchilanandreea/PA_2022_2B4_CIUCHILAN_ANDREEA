import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    T get(long id);
    List<T> getAll();
    void save(T t);
    void update(T t);
    void insert(String[] params) throws SQLException, ParseException;
    void delete(String id, String name) throws SQLException;
    void deleteAll();
}