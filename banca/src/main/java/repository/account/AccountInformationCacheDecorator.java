package repository.account;

import model.Account;
import repository.Cache;

import java.util.List;
import java.util.stream.Collectors;

public class AccountInformationCacheDecorator extends AccountInformationDecorator {

    private Cache<Account> cache;

    public AccountInformationCacheDecorator(AccountInformation accountRepository, Cache<Account> cache)
    {
        super( accountRepository);
        this.cache=cache;
    }

    @Override
    public List<Account> findAll() {
        if(cache.hasResult())
        {
            return cache.load();
        }

        List<Account> accounts = decoratedAccountRepository.findAll();
        cache.save(accounts);
        return accounts;
    }

    @Override
    public Account findById(Long id) {
        if(cache.hasResult())
        {
            List<Account> accounts = cache.load().parallelStream()
                        .filter(i -> i.getId().equals(id))
                        .collect(Collectors.toList());
            return accounts.get(0);
        }

        return decoratedAccountRepository.findById(id);
    }

    @Override
    public boolean save(Account account) {
        cache.invalidateCache();
        return decoratedAccountRepository.save(account);
    }


    /*@Override
    public boolean update(Account account) {
        cache.invalidateCache();

    }*/

    @Override
    public boolean delete(Account account) {
        cache.invalidateCache();
       return decoratedAccountRepository.delete(account);
    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedAccountRepository.removeAll();
    }
}
