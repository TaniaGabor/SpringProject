package service.account;

import model.Account;
import repository.account.AccountRepository;


import java.util.List;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository aAccountRepository)
    {
        accountRepository = aAccountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    @Override
    public boolean save(Account account) {
        return accountRepository.save(account);
    }


}
