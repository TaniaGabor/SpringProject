package model;

public class Client {
    private Integer id;
    private String name;
    private String personalNumericalCode;
    private String identificationNumber;
    private String adress;

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
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "Client" +
                "id=" + id +
                ", name=" + name + '\'' +
                ", personalNumericalCode='" + personalNumericalCode + '\'' +
                ", identificationNumber='" + identificationNumber + '\'' +
                ", adress='" + adress + '\'' ;
    }
}

