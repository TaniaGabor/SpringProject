package repository.user;

import model.User;
import model.validation.Notification;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserRepository {
    List<User> findAll();

    Notification<User> findByUsernameAndPassword(String username, String password) throws AuthenticationException;
    Notification<Long> findByUsername(String username) throws AuthenticationException;
    Notification<Long> deleteByUsername(String username) throws AuthenticationException;

    Notification<Boolean> modifyPassword(String username,String password);
    boolean save(User user);
    public void setDateofAcces(User user) throws SQLException;
    public void setDateofLogOut(User user) throws SQLException;
    public Notification<String> getwellDate(String username) throws SQLException;
   // public boolean insertActivity(User user,String activity) throws SQLException;
    void removeAll();
}
