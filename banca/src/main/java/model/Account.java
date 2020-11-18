package model;

import java.util.Date;

public class Account {

    private Long id;
    private String identityCardNumber;
    private String type;
    private Date dateofCreation;
    private Long amountofMoney;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Account)) {
            return false;
        }
        return ((Account) obj).getIdentityCardNumber().equals(this.identityCardNumber);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateofCreation() {
        return dateofCreation;
    }

    public void setDateofCreation(Date dateofCreation) {
        this.dateofCreation = dateofCreation;
    }

    public Long getAmountofMoney() {
        return amountofMoney;
    }

    public void setAmountofMoney(Long amountofMoney) {
        this.amountofMoney = amountofMoney;
    }
}
