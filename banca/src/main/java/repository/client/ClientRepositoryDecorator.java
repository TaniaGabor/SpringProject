package repository.client;

import model.Client;
import model.validation.Notification;

import java.util.List;

public  abstract class ClientRepositoryDecorator implements ClientRepository{
    protected ClientRepository decoratedRepository;

    public ClientRepositoryDecorator(ClientRepository clientRepository) {
        this.decoratedRepository = clientRepository;
    }




    @Override
    public Notification<Client> findbyCNP(String CNP) throws ClientException {
        return null;
    }

    @Override
    public void modify(String cnp, String newName, String adress) {

    }

    @Override
    public boolean save(Client client) {
        return false;
    }

    @Override
    public boolean delete(Client account) {
        return false;
    }
}
