package repository.account;

import model.Account;

import java.util.List;

public interface AccountInformation {
    List<Account> findAll();

    Account findById(Long id);

    boolean save(Account account); /*add*/

   /* boolean update (Account account);*/

    boolean delete(Account account);

    void removeAll();

}
