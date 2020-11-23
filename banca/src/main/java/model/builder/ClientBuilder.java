package model.builder;

import model.Client;


import java.util.Date;

public class ClientBuilder {
    private Client client;

    public ClientBuilder() {
        client = new Client();
    }

    public ClientBuilder setId(Long id) {
        client.setId(id);
        return this;
    }

    public ClientBuilder setName (String name ) {
        client.setName(name);
        return this;
    }


    public ClientBuilder setPersonalNumericalCode(String personalNumericalCode) {
        client.setPersonalNumericalCode(personalNumericalCode);
        return this;
    }

    public ClientBuilder setIdentificationNumber(String identificationNumber) {
        client.setIdentificationNumber(identificationNumber);
        return this;
    }
    public ClientBuilder setAdress(String adress) {
        client.setAdress(adress);
        return this;
    }

    public Client build() {
        return client;
    }

}
