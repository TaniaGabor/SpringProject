import java.sql.Connection;
import java.sql.DriverManager;

public class DaoManager{
    public static Connection connection;
    public static  Connection getConnection(){


        String dbName="books";
        String userName="root";
        String password="parola123@";

        try {


            connection= DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return connection;
    }

}