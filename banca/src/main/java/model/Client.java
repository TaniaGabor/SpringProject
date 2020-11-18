package model;

public class Client {
    private Long id;
    private String name;
    private String personalNumericalCode;
    private String identificationNumber;
    private String adress;
    private Long amountofMoney;
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Account)) {
            return false;
        }
        return ((Client) obj).getPersonalNumericalCode().equals(this.personalNumericalCode);

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonalNumericalCode() {
        return personalNumericalCode;
    }

    public void setPersonalNumericalCode(String personalNumericalCode) {
        this.personalNumericalCode = personalNumericalCode;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Long getAmountofMoney() {
        return amountofMoney;
    }

    public void setAmountofMoney(Long amountofMoney) {
        this.amountofMoney = amountofMoney;
    }
}
