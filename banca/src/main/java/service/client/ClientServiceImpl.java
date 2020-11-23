package service.client;
import database.Util;
import model.Client;
import model.Role;
import model.User;
import model.builder.ClientBuilder;
import model.builder.UserBuilder;
import model.validation.ClientValidator;
import model.validation.Notification;
import model.validation.UserValidator;
import repository.client.ClientRepository;


import java.util.Collections;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository aClientRepository)
    {
        clientRepository = aClientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Notification<Boolean> save(String name, String cnp,String idenNumber,String adress) {
        Client client = new ClientBuilder()
                .setName(name)
                .setPersonalNumericalCode(cnp)
                .setIdentificationNumber(idenNumber)
                .setAdress(adress)
                .build();
        ClientValidator clientValidator = new ClientValidator(client);
        boolean clientValid = clientValidator.validate();
        Notification<Boolean> userRegisterNotification = new Notification<>();

        if (!clientValid) {
            clientValidator.getErrors().forEach(userRegisterNotification::addError);
            userRegisterNotification.setResult(Boolean.FALSE);
        } else {

            userRegisterNotification.setResult(clientRepository.save(client));
        }
        return userRegisterNotification;

    }


}
