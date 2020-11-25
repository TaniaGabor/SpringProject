package service.client;



import model.Client;
import model.validation.Notification;
import repository.client.ClientRepository;

import java.util.List;

public interface ClientService {



    Notification<Boolean> save(String name, String cnp, String idenNumber, String adress);
    Notification<Boolean> modifyNameAdress(String cnp,String newName, String adress) ;
    ClientRepository getClientRepository();

}
