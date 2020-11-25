package repository.account;

import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import model.validation.Notification;
import repository.user.AuthenticationException;
import utility.JDBConnectionWrapper;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static database.Constants.Tables.*;

public class AccountRepositoryMySQL implements AccountRepository {

    private final Connection connection;

    public AccountRepositoryMySQL(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public List<Account> findAll() {
        Connection connection = this.connection;
        List<Account> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public Account findByIdentityCardNumber(String identityCardNumber) throws SQLException {
        Statement statement = connection.createStatement();
        String fetchUserSql = "Select * from `" + ACCOUNT + "` where `identityCardNumber`=\'" + identityCardNumber + "\'";
        ResultSet userResultSet = statement.executeQuery(fetchUserSql);
        if (userResultSet.next()) {
            Account account = getAccountFromResultSet(userResultSet);
            return account;

        }
        return null;

    }



    @Override
    public boolean save(Account account) {
        Connection connection = this.connection;
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO account values (null, ?, ?, ?,?,?)");
            insertStatement.setString(1,account.getCnp());
            insertStatement.setString(2, account.getIdentityCardNumber());
            insertStatement.setString(3, account.getType());
            insertStatement.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));

            insertStatement.setDouble(5, account.getAmountofMoney());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;

        }
    }

    @Override
    public boolean modifyMoney(String identityCardNumber, Double amountOfMoney) {
        Connection connection = this.connection;
        try{
            PreparedStatement updateStatement=connection.prepareStatement("Update account set amountofMoney=? where identityCardNumber=?");
            updateStatement.setDouble(1,amountOfMoney);
            updateStatement.setString(2,identityCardNumber);
            int done=updateStatement.executeUpdate();
            return done != 0;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

   




    @Override
    public Boolean deleteByIdentityCardNumber(String identityCardNumber) {
        Connection connection = this.connection;
        try{
            PreparedStatement deleteStatement=connection.prepareStatement("delete from account where identityCardNumber=? ");
            deleteStatement.setString(1,identityCardNumber);
            deleteStatement.executeUpdate();
            return true;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modify(String identityCardNumber,String newIdentityCardNumber) {
        Connection connection = this.connection;
        try{
            PreparedStatement updateStatement=connection.prepareStatement("Update account set identityCardNumber=? where identityCardNumber=?");
            updateStatement.setString(1,newIdentityCardNumber);
            updateStatement.setString(2,identityCardNumber);
            int done=updateStatement.executeUpdate();
            return done != 0;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {
        Connection connection = this.connection;
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Account getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new AccountBuilder()
                .setId(rs.getLong("id"))
                .setCnp(rs.getString("cnpClient"))
                .setIdentityCardNumber(rs.getString("identityCardNumber"))
                .setType(rs.getString("type"))
                .setDateofCreation(new Date(rs.getDate("dateofCreation").getTime()))
                .setAmountofMoney(rs.getDouble("amountofMoney"))
                .build();
    }
}
