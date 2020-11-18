package service.account;



import model.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(Long id);

    boolean save(Account account);


}
