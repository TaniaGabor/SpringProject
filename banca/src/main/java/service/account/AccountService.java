package service.account;



import model.Account;
import model.validation.Notification;
import repository.account.AccountRepository;
import repository.user.UserRepository;

import java.sql.SQLException;
import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Notification<Account> find(String identityCardNumber) throws SQLException;
    Notification<Boolean> delete(String identityCardNumber) throws SQLException;
    Notification<Boolean> makeTransfer(Account firstAccount,Account secondAccount,Double amount) ;
    Notification<Boolean> modifyICN(String identityCardNumber,String newIdentityCardNumber) ;
    AccountRepository getAccountRepository();
    Notification<Boolean> save(String type,String cnp,Double money,String idenNumber);


}
