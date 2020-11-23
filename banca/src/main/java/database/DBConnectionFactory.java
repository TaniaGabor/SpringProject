package database;

import utility.JDBConnectionWrapper;

public class DBConnectionFactory {

    public JDBConnectionWrapper getConnectionWrapper(boolean test)
    {
        if(test){
            return new JDBConnectionWrapper("test_bank");
        }else{
            return new JDBConnectionWrapper("bank");
        }
    }
}
