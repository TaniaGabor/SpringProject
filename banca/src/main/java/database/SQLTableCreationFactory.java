package database;
import static database.Constants.Tables.*;


public class SQLTableCreationFactory {

    public String getCreateSQLForTable(String table) {
        switch (table) {
            case ACCOUNT:
                return "CREATE TABLE IF NOT EXISTS account (" +
                        "  id int(11) NOT NULL AUTO_INCREMENT," +
                        "  cnpClient varchar(500) NOT NULL,"+
                        "  identityCardNumber varchar(500)  unique NOT NULL," +
                        "  type varchar(500) NOT NULL," +
                        "  dateofCreation datetime DEFAULT NULL," +
                        "  amountofMoney double(24, 12) NOT NULL,"+
                        "  PRIMARY KEY (id),"+
                        "  UNIQUE KEY id_UNIQUE (id)," +
                        "FOREIGN KEY (cnpClient)"+
                        "REFERENCES client(personalNumericalCode)"+
                        "ON DELETE CASCADE "+
                        "ON UPDATE CASCADE"+
                        ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
           /* case ACTIVITY:
                return "CREATE TABLE IF NOT EXISTS activity (" +
                        "  id int(11) NOT NULL AUTO_INCREMENT," +
                        "  usernameEmployee VARCHAR(200) unique NOT NULL,"+
                        "  activity VARCHAR(200) NOT NULL," +
                        "  PRIMARY KEY (id),"+
                        "  UNIQUE KEY id_UNIQUE (id)," +
                        "  UNIQUE KEY username_UNIQUE (usernameEmployee)," +
                        "  UNIQUE INDEX usernameEmployee_UNIQUE (usernameEmployee ASC))," +
                        "FOREIGN KEY (usernameEmployee)"+
                        "REFERENCES user(username)"+
                        "ON DELETE CASCADE "+
                        "ON UPDATE CASCADE"+
                        ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";*/
            case CLIENT:
                return "CREATE TABLE IF NOT EXISTS client(" +
                        "  id int(11) NOT NULL AUTO_INCREMENT," +
                        "  name  varchar(500) NOT NULL," +
                        "  personalNumericalCode  varchar(500)  unique NOT NULL," +
                        "  identificationNumber   varchar(500) unique NOT NULL," +
                        "  adress varchar(600) NOT NULL," +
                        "  PRIMARY KEY (id),"+
                        "  UNIQUE KEY id_UNIQUE (id)" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";

            case USER:
                return "CREATE TABLE IF NOT EXISTS user (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  username VARCHAR(200)  unique NOT NULL," +
                        "  password VARCHAR(64) NOT NULL," +
                        "  dateofAccess datetime DEFAULT NULL,"+
                        "  dateofLogOut datetime DEFAULT NULL,"+
                        "  PRIMARY KEY (id)," +
                        "   UNIQUE KEY (username),"+
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  UNIQUE INDEX username_UNIQUE (username ASC));";

            case ROLE:
                return "  CREATE TABLE IF NOT EXISTS role (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  role VARCHAR(100) NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  UNIQUE INDEX role_UNIQUE (role ASC));";

            case RIGHT:
                return "  CREATE TABLE IF NOT EXISTS `right` (" +
                        "  `id` INT NOT NULL AUTO_INCREMENT," +
                        "  `right` VARCHAR(100) NOT NULL," +
                        "  PRIMARY KEY (`id`)," +
                        "  UNIQUE INDEX `id_UNIQUE` (`id` ASC)," +
                        "  UNIQUE INDEX `right_UNIQUE` (`right` ASC));";

            case ROLE_RIGHT:
                return "  CREATE TABLE IF NOT EXISTS role_right (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  role_id INT NOT NULL," +
                        "  right_id INT NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  INDEX role_id_idx (role_id ASC)," +
                        "  INDEX right_id_idx (right_id ASC)," +
                        "  CONSTRAINT role_id" +
                        "    FOREIGN KEY (role_id)" +
                        "    REFERENCES role (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT right_id" +
                        "    FOREIGN KEY (right_id)" +
                        "    REFERENCES `right` (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";

            case USER_ROLE:
                return "\tCREATE TABLE IF NOT EXISTS user_role (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  user_id INT NOT NULL," +
                        "  role_id INT NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  INDEX user_id_idx (user_id ASC)," +
                        "  INDEX role_id_idx (role_id ASC)," +
                        "  CONSTRAINT user_fkid" +
                        "    FOREIGN KEY (user_id)" +
                        "    REFERENCES user (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT role_fkid" +
                        "    FOREIGN KEY (role_id)" +
                        "    REFERENCES role (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";

            default:
                return "";

        }
    }

}