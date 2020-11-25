package repository.account;

import model.Account;
import model.validation.Notification;
import repository.user.AuthenticationException;

import java.sql.SQLException;
import java.util.List;

public interface AccountRepository {
    List<Account> findAll();

    Account findByIdentityCardNumber(String identityCardNumber) throws SQLException;
    Boolean  deleteByIdentityCardNumber(String identityCardNumber) throws SQLException;

    boolean modify(String indetityCardNumber,String newIdentityCardNumber);
    boolean save(Account account); /*add*/
    boolean modifyMoney(String identityCardNumber,Double amountOfMoney);


    void removeAll();

}
