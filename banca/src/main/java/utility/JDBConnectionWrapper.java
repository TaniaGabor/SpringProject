package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static database.Constants.Tables.ACTIVITY;

public class JDBConnectionWrapper {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/";

    private static final String USER = "root";
    private static final String PASS = "parola123@";
    private static final int TIMEOUT = 5;

    private Connection connection;

    public JDBConnectionWrapper(String schema) {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL + schema, USER, PASS);
            createTables();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTables() throws SQLException {
        Statement statement = connection.createStatement();

        String sql_client = "CREATE TABLE IF NOT EXISTS client(" +
                "  id int(11) NOT NULL AUTO_INCREMENT," +
                "  name  varchar(500) NOT NULL," +
                "  personalNumericalCode  varchar(500)  unique NOT NULL," +
                "  identificationNumber   varchar(500) unique NOT NULL," +
                "  adress varchar(600) NOT NULL," +
                "  PRIMARY KEY (id),"+
                "  UNIQUE KEY id_UNIQUE (id)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql_client);
        String sql_account = "CREATE TABLE IF NOT EXISTS account (" +
                "  id int(11) NOT NULL AUTO_INCREMENT," +
                " cnpClient varchar(500) NOT NULL,"+
                "  identityCardNumber varchar(500) NOT NULL," +
                "  type varchar(500) NOT NULL," +
                "  dateofCreation datetime DEFAULT NULL," +
                "  amountofMoney double(24, 12) NOT NULL,"+
                "  PRIMARY KEY (id),"+
                "  UNIQUE KEY id_UNIQUE (id)," +
                "  UNIQUE INDEX identityCardNumber_UNIQUE (identityCardNumber), "  +
                "FOREIGN KEY (cnpClient)"+
                "REFERENCES client(personalNumericalCode)"+
                "ON DELETE CASCADE " +
                "ON UPDATE CASCADE "+
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql_account);
       /* String sql_activity= "CREATE TABLE IF NOT EXISTS activity (" +
                "  id int(11) NOT NULL AUTO_INCREMENT," +
                "  usernameEmployee VARCHAR(200)  NOT NULL,"+
                "  activity VARCHAR(200) NOT NULL," +
                "  PRIMARY KEY (id),"+
                "  UNIQUE KEY id_UNIQUE (id)," +
                "  UNIQUE KEY username_UNIQUE (usernameEmployee)," +
                "  UNIQUE INDEX usernameEmployee_UNIQUE (usernameEmployee ASC), " +
                "FOREIGN KEY (usernameEmployee)"+
                "REFERENCES user(username)"+
                "ON DELETE CASCADE "+
                "ON UPDATE CASCADE"+
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql_activity);*/
    }

    public boolean testConnection() throws SQLException {
        return connection.isValid(TIMEOUT);
    }

    public Connection getConnection() {
        return connection;
    }
}
