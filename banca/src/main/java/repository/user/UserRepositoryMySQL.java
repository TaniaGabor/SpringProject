package repository.user;
import database.Util;
import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import model.validation.UserValidator;
import repository.security.RightsRolesRepository;

import java.security.MessageDigest;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static database.Constants.Tables.USER;

public class UserRepositoryMySQL implements UserRepository {

    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;


    public UserRepositoryMySQL(Connection connection, RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) throws AuthenticationException {
        Notification<User> findByUsernameAndPasswordNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String fetchUserSql = "Select * from `" + USER + "` where `username`=\'" + username + "\' and `password`=\'" + password + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next()) {
                User user = new UserBuilder()
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                        .build();
                findByUsernameAndPasswordNotification.setResult(user);
                return findByUsernameAndPasswordNotification;
            } else {
                findByUsernameAndPasswordNotification.addError("Invalid email or password!");
                return findByUsernameAndPasswordNotification;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AuthenticationException();
        }
    }

    @Override
    public Notification<Long> findByUsername(String username) throws AuthenticationException {
        Notification<Long> findByUsername = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String fetchUserSql = "Select * from `" + USER + "` where `username`=\'" + username + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next()) {
                Long id = userResultSet.getLong("id");
                findByUsername.setResult(id);
                return findByUsername;
            } else {
                findByUsername.addError("Invalid username");
                return findByUsername;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AuthenticationException();
        }
    }

    @Override
    public Notification<Long> deleteByUsername(String username) throws AuthenticationException {
        Notification<Long> findByUsername = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String fetchUserSql = "Delete from `" + USER + "` where `username`=\'" + username + "\'";
            int result = statement.executeUpdate(fetchUserSql);
            if (result != 0) {
                findByUsername.setResult((long) 1);
                return findByUsername;
            } else {
                findByUsername.addError("Could not find username!");
                return findByUsername;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AuthenticationException();
        }

    }

    @Override
    public boolean save(User user) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?,?,?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.setDate(3,null);
            insertUserStatement.setDate(4,null);
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long userId = rs.getLong(1);
            user.setId(userId);

            rightsRolesRepository.addRolesToUser(user, user.getRoles());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
            return false;
        }

    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Notification<Boolean>  modifyPassword(String username, String password) {
        Notification<Boolean> userRegisterNotification = new Notification<>();
        String newPass=null;
        try {
            User user = new UserBuilder()
                    .setUsername(username)
                    .setPassword(password)
                    .build();

            UserValidator userValidator = new UserValidator(user);
            boolean userValid = userValidator.validate();


            if (!userValid) {
                userValidator.getErrors().forEach(userRegisterNotification::addError);
                userRegisterNotification.setResult(Boolean.FALSE);
            } else {
                newPass = Util.encodePassword(password);
                user.setPassword(newPass);
                userRegisterNotification.setResult(Boolean.TRUE);
                PreparedStatement statement = connection.prepareStatement("Update user set password=? where username=?");
                statement.setString(1, newPass);
                statement.setString(2, username);
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return userRegisterNotification;


    }

   public void setDateofAcces(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("Update user set dateofAccess=? where username=?");
        statement.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
        statement.setString(2, user.getUsername());
        statement.executeUpdate();

    }
    public void setDateofLogOut(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("Update user set dateofLogOut=? where username=?");
        statement.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
        statement.setString(2, user.getUsername());
        statement.executeUpdate();

    }
    public Notification<String> getwellDate(String username) throws SQLException {

        String dates;
        Notification<String> ok=new Notification<String>();
        Statement statement = connection.createStatement();
        String fetchUserSql = "Select * from `" + USER + "` where `username`=\'" + username + "\'";
        ResultSet userResultSet = statement.executeQuery(fetchUserSql);
        if (userResultSet.next()) {
            ok.setResult(userResultSet.getDate("dateofAccess").toString()+" " +userResultSet.getDate("dateofLogOut").toString());
            return ok;
        }
        ok.setResult(null);
        return ok;



    }
   /* public boolean insertActivity(User user,String activity) throws SQLException {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO activity values (null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, activity);
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long userId = rs.getLong(1);
            user.setId(userId);

            rightsRolesRepository.addRolesToUser(user, user.getRoles());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
            return false;
        }*/





    }




