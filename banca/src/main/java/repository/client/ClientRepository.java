package repository.client;

import model.Client;
import model.validation.Notification;

import java.util.List;

public interface ClientRepository {



    Notification<Client> findbyCNP(String CNP) throws ClientException;
    void modify(String cnp, String newName, String adress) ;
    boolean save(Client client);

    boolean delete(Client account);



}
