package service.account;

import model.Account;
import repository.account.AccountInformation;


import java.util.List;

public class AccountServiceImpl implements AccountService {

    private final AccountInformation accountRepository;

    public AccountServiceImpl(AccountInformation aAccountRepository)
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
    public boolean save(Account account) {
        return accountRepository.save(account);
    }


}
