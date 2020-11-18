package repository.account;

import model.Account;
import model.builder.AccountBuilder;
import utility.JDBConnectionWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountInformationMySQL implements AccountInformation {

    private final JDBConnectionWrapper connectionWrapper;

    public AccountInformationMySQL(JDBConnectionWrapper jdbConnectionWrapper)
    {
        connectionWrapper = jdbConnectionWrapper;
    }

    @Override
    public List<Account> findAll() {
        Connection connection = connectionWrapper.getConnection();
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
        Connection connection = connectionWrapper.getConnection();
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO account values (null, ?, ?, ?,?)");
            insertStatement.setString(1, account.getIdentityCardNumber());
            insertStatement.setString(2, account.getType());
            insertStatement.setDate(3, new java.sql.Date(account.getDateofCreation().getTime()));
            insertStatement.setLong(4, account.getAmountofMoney());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
        Connection connection = connectionWrapper.getConnection();
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
        Connection connection = connectionWrapper.getConnection();
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
                .setAmountofMoney(rs.getLong("amountofMoney"))
                .build();
    }
}
