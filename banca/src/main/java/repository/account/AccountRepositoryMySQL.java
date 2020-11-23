package repository.account;

import model.Account;
import model.builder.AccountBuilder;
import model.validation.Notification;
import repository.user.AuthenticationException;
import utility.JDBConnectionWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static database.Constants.Tables.ACCOUNT;
import static database.Constants.Tables.USER;

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
    public Account findById(Long id) {
        return null;
    }

    @Override
    public boolean save(Account account) {
        Connection connection = this.connection;
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO account values (null, ?, ?, ?,?)");
            insertStatement.setString(1, account.getIdentityCardNumber());
            insertStatement.setString(2, account.getType());
            insertStatement.setDate(3, new java.sql.Date(account.getDateofCreation().getTime()));
            insertStatement.setDouble(4, account.getAmountofMoney());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Notification<Long> findByIdentificationNumber(String identificationNumber) {
        Notification<Long> identityCardNumber = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String fetchUserSql = "Select * from `" + ACCOUNT + "` where `identityCardNumber`=\'" + identificationNumber + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next()) {
                Long id = userResultSet.getLong("id");
                identityCardNumber.setResult(id);
                return identityCardNumber;
            } else {
                identityCardNumber.addError("Invalid username");
                return identityCardNumber;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return identityCardNumber;
    }

   /* @Override
    public boolean update(Account account) {

        Connection connection = connectionWrapper.getConnection();
        try{ PreparedStatement updateStatement=connection.prepareStatement(
                "UPDATE account set (? ?) where identityCardNumber=?");
            updateStatement.setString(1,account.getType());
            updateStatement.setLong(2,account.getAmountofMoney());
            updateStatement.setString(3,account.getIdentityCardNumber());
            updateStatement.executeUpdate();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }


    }*/

    @Override
    public boolean delete(Account account) {
        Connection connection = this.connection;
        try{
            PreparedStatement deleteStatement=connection.prepareStatement("delete from account where identityCardNumber=? ");
            deleteStatement.setString(1,account.getIdentityCardNumber());
            deleteStatement.executeUpdate();
            return true;


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
                .setIdentityCardNumber(rs.getString("identityCardNumber"))
                .setType(rs.getString("type"))
                .setDateofCreation(new Date(rs.getDate("dateofCreation").getTime()))
                .setAmountofMoney(rs.getDouble("amountofMoney"))
                .build();
    }
}
