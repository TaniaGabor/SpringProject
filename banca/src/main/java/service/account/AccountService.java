package service.account;



import model.Account;
import repository.account.AccountRepository;
import repository.user.UserRepository;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(Long id);
    AccountRepository getAccountRepository();
    boolean save(Account account);


}
