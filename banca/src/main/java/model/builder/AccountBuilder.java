package model.builder;

import model.Account;


import java.util.Date;

public class AccountBuilder {
    private Account account;

    public AccountBuilder() {
        account = new Account();
    }

    public AccountBuilder setId(Long id) {
        account.setId(id);
        return this;
    }

    public AccountBuilder setIdentityCardNumber(String identityCardNumber) {
        account.setIdentityCardNumber(identityCardNumber);
        return this;
    }

    public AccountBuilder setType(String type) {
        account.setType(type);
        return this;
    }
    public AccountBuilder setCnp(String cnp) {
        account.setCnp(cnp);
        return this;
    }

    public AccountBuilder setDateofCreation(Date dateofCreation) {
        account.setDateofCreation(dateofCreation);
        return this;
    }
    public AccountBuilder setAmountofMoney(Double amountofMoney) {
        account.setAmountofMoney(amountofMoney);
        return this;
    }

    public Account build() {
        return account;
    }

}
