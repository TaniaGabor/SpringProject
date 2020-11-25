package repository.client;

import model.Client;
import model.builder.ClientBuilder;
import model.validation.Notification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.CLIENT;

public class ClientRepositoryMySQL implements ClientRepository {

    private final Connection connection;

    public ClientRepositoryMySQL(Connection connection)
    {
       this.connection=connection;
    }





    @Override
    public Notification<Client> findbyCNP(String Cnp) throws ClientException {
        Notification<Client> findName = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String fetchUserSql = "Select * from `" + CLIENT + "` where `personalNumericalCode`=\'" + Cnp + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next()) {
                Client client = getClientFromResultSet(userResultSet);

                findName.setResult(client);
                return findName;
            } else {
                findName.addError("Invalid CNP!");
                return findName;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ClientException();
        }
    }

    @Override
    public void modify(String cnp, String newName, String adress)  {
        Connection connection = this.connection;
        try {
            PreparedStatement statement = connection.prepareStatement("Update client set name=? , adress=?  where personalNumericalCode=?");
            statement.setString(1, newName);
            statement.setString(2, adress);
            statement.setString(3, cnp);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean save(Client client) {
        Connection connection = this.connection;
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO client values (null, ?, ?, ?, ?)");
            insertStatement.setString(1, client.getName());
            insertStatement.setString(2, client.getPersonalNumericalCode());
            insertStatement.setString(3, client.getIdentificationNumber());
            insertStatement.setString(4, client.getAdress());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public boolean delete(Client client) {
        Connection connection = this.connection;
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




    private Client  getClientFromResultSet(ResultSet rs) throws SQLException {
        return new ClientBuilder()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .setPersonalNumericalCode(rs.getString("personalNumericalCode"))
                .setIdentificationNumber(rs.getString("identificationNumber"))
                .setAdress(rs.getString("adress"))
                .build();
    }
}
