package service.client;
import model.Client;
import model.builder.ClientBuilder;
import model.validation.ClientValidator;
import model.validation.Notification;
import repository.client.ClientRepository;


import java.util.List;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository aClientRepository)
    {
        clientRepository = aClientRepository;
    }




    @Override
    public Notification<Boolean> save(String name, String cnp, String idenNumber, String adress) {
        Client client = new ClientBuilder()
                .setName(name)
                .setPersonalNumericalCode(cnp)
                .setIdentificationNumber(idenNumber)
                .setAdress(adress)
                .build();
        ClientValidator clientValidator = new ClientValidator(client);
        boolean clientValid = clientValidator.validate(false);
        Notification<Boolean> userRegisterNotification = new Notification<>();

        if (!clientValid) {
            clientValidator.getErrors().forEach(userRegisterNotification::addError);
            userRegisterNotification.setResult(Boolean.FALSE);
        } else {

            userRegisterNotification.setResult(clientRepository.save(client));
        }
        return userRegisterNotification;

    }
    @Override
    public Notification<Boolean> modifyNameAdress(String cnp,String newName, String adress)   {
        Notification<Boolean> clientUpdateNotification = new Notification<>();
        Client client = new ClientBuilder()
                .setPersonalNumericalCode(cnp)
                .build();

        ClientValidator clientValidator = new ClientValidator(client);
        boolean clientValid = clientValidator.validate(true);
        if (!clientValid) {
            clientValidator.getErrors().forEach(clientUpdateNotification::addError);
            clientUpdateNotification.setResult(Boolean.FALSE);
        }
        else
        { clientUpdateNotification.setResult(Boolean.TRUE);
             clientRepository.modify(cnp,newName,adress);
            return clientUpdateNotification;
        }

        return clientUpdateNotification;
    }


    public ClientRepository getClientRepository() {
        return clientRepository;
    }
}
