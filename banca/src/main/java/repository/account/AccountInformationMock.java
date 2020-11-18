package repository.account;

import model.Account;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class AccountInformationMock implements AccountInformation {

    private List<Account> accounts;

    public AccountInformationMock() {
        accounts = new ArrayList<>();
    }

    public List<Account> findAll() {
        return accounts;
    }

    public Account findById(Long id) {

        List<Account> filteredAccounts = accounts.parallelStream()
                .filter(i -> i.getId().equals(id))
                .collect(Collectors.toList());
        if (filteredAccounts.size() > 0) {
            return filteredAccounts.get(0);
        }

        return null;
    }

    @Override
    public boolean save(Account account) {
        return accounts.add(account);
    }

  /*  @Override
    public boolean update(Account account) {
        ListIterator<Account> listIterator = accounts.listIterator();

        while(listIterator.hasNext()) {
             if( listIterator.next().equals(account))
             {
                 listIterator.set(account);
                 return true;
             }
        }
      return false;
    }
*/
    @Override
    public boolean delete(Account account) {
     return accounts.remove(account);
    }

    @Override
    public void removeAll() {
        accounts.clear();
    }
}
