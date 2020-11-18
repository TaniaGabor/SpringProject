package repository.client;

import model.Client;
import model.builder.ClientBuilder;
import utility.JDBConnectionWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientInformationMySQL implements ClientInformation {

    private final JDBConnectionWrapper connectionWrapper;

    public ClientInformationMySQL(JDBConnectionWrapper jdbConnectionWrapper)
    {
        connectionWrapper = jdbConnectionWrapper;
    }

    @Override
    public List<Client> findAll() {
        Connection connection = connectionWrapper.getConnection();
        List<Client> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from client";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                accounts.add(getClientFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public Client findById(Long id) {
        return null;
    }

    @Override
    public boolean save(Client client) {
        Connection connection = connectionWrapper.getConnection();
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO client values (null, ?, ?, ?,?,?)");
            insertStatement.setString(1, client.getName());
            insertStatement.setString(2, client.getPersonalNumericalCode());
            insertStatement.setString(3, client.getPersonalNumericalCode());
            insertStatement.setString(4, client.getIdentificationNumber());
            insertStatement.setString(5, client.getAdress());
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
    public boolean delete(Client client) {
        Connection connection = connectionWrapper.getConnection();
        try{
            PreparedStatement deleteStatement=connection.prepareStatement("delete from client where personalNumericalCode=? ");
            deleteStatement.setString(1, client.getPersonalNumericalCode());
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
            String sql = "DELETE from client where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Client  getClientFromResultSet(ResultSet rs) throws SQLException {
        return new ClientBuilder()
                .setId(rs.getLong("id"))
                .setName(rs.getString("name"))
                .setPersonalNumericalCode(rs.getString("personalNumericalCode"))
                .setIdentificationNumber(rs.getString("identificationNumber"))
                .setAdress(rs.getString("adress"))
                .build();
    }
}
