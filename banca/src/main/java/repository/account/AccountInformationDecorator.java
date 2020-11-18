package repository.account;

public abstract class AccountInformationDecorator implements AccountInformation {
    protected AccountInformation decoratedAccountRepository;

    public AccountInformationDecorator(AccountInformation accountRepository) {
        this.decoratedAccountRepository = accountRepository;
    }
}
