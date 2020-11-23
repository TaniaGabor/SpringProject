package repository.account;

import model.Account;
import model.validation.Notification;
import repository.user.AuthenticationException;

import java.util.List;

public interface AccountRepository {
    List<Account> findAll();

    Account findById(Long id);

    boolean save(Account account); /*add*/
    Notification<Long> findByIdentificationNumber(String username) ;
   /* boolean update (Account account);*/

    boolean delete(Account account);

    void removeAll();

}
