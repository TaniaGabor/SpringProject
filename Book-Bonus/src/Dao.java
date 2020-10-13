import java.sql.SQLException;


public interface Dao{

   Book get(long id);

     String getAll() throws SQLException;


    void addItemT (Book b) throws SQLException;

    void removeT (Book b) throws SQLException;
}
