package service.account;

import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import model.validation.AccountValidator;
import model.validation.ClientValidator;
import model.validation.Notification;
import repository.account.AccountRepository;


import java.sql.SQLException;
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
    public Notification<Account> find(String identityCardNumber) throws SQLException {
        Notification<Account> accountNotification = new Notification<>();
        Account account = new AccountBuilder()
                .setIdentityCardNumber(identityCardNumber)
                .build();

        AccountValidator accountValidator = new AccountValidator(account);
        boolean accountValid = accountValidator.validate(true);
        if (!accountValid) {
            accountValidator.getErrors().forEach(accountNotification::addError);
            accountNotification.setResult(null);
        }
        else
        { accountNotification.setResult( accountRepository.findByIdentityCardNumber(identityCardNumber));
            return accountNotification;
        }

        return accountNotification;
    }

    @Override
    public Notification<Boolean> delete(String identityCardNumber) throws SQLException {

        Notification<Boolean> accountNotification = new Notification<>();
        Account account = new AccountBuilder()
                .setIdentityCardNumber(identityCardNumber)
                .build();

        AccountValidator accountValidator = new AccountValidator(account);
        boolean accountValid = accountValidator.validate(true);
        if (!accountValid) {
            accountValidator.getErrors().forEach(accountNotification::addError);
            accountNotification.setResult(null);
        }
        else
        { accountNotification.setResult( accountRepository.deleteByIdentityCardNumber(identityCardNumber));
            return accountNotification;
        }

        return accountNotification;
    }

    @Override
    public Notification<Boolean> makeTransfer(Account firstAccount, Account secondAccount,Double amount) {
        Notification<Boolean> accountNotification = new Notification<>();
        if(firstAccount.getAmountofMoney()-amount<0)
        {
            accountNotification.setResult(false);
        }
        else{
            firstAccount.setAmountofMoney(firstAccount.getAmountofMoney()-amount);
            secondAccount.setAmountofMoney(secondAccount.getAmountofMoney()+amount);
            accountRepository.modifyMoney(firstAccount.getIdentityCardNumber(),firstAccount.getAmountofMoney());
            accountRepository.modifyMoney(secondAccount.getIdentityCardNumber(), secondAccount.getAmountofMoney());
            accountNotification.setResult(true);


        }
        return accountNotification;


    }

    @Override
    public Notification<Boolean> modifyICN(String identityCardNumber,String newIdentityCardNumber) {
        Account account = new AccountBuilder()
                .setIdentityCardNumber(identityCardNumber)
                .build();
        AccountValidator accountValidator = new AccountValidator(account);

        boolean accountValid = accountValidator.validate(true);
        Notification<Boolean> userRegisterNotification = new Notification<>();

        if (!accountValid) {
            accountValidator.getErrors().forEach(userRegisterNotification::addError);
            userRegisterNotification.setResult(Boolean.FALSE);
        } else {

            userRegisterNotification.setResult(accountRepository.modify(identityCardNumber,newIdentityCardNumber));
        }
        return userRegisterNotification;
    }




    @Override
    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    @Override
    public Notification<Boolean> save(String type,String cnp,Double money,String idenNumber) {
        Account account = new AccountBuilder()
                .setType(type)
                .setCnp(cnp)
                .setIdentityCardNumber(idenNumber)
                .setAmountofMoney(money)
                .build();
        AccountValidator accountValidator = new AccountValidator(account);
        boolean accountValid = accountValidator.validate(false);
        Notification<Boolean> userRegisterNotification = new Notification<>();

        if (!accountValid) {
            accountValidator.getErrors().forEach(userRegisterNotification::addError);
            userRegisterNotification.setResult(Boolean.FALSE);
        } else {

            userRegisterNotification.setResult(accountRepository.save(account));
        }
        return userRegisterNotification;
    }




}

