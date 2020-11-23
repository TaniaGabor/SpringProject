package service.client;



import model.Client;
import model.validation.Notification;

import java.util.List;

public interface ClientService {

    List<Client> findAll();

    Client findById(Long id);

    Notification<Boolean> save(String name, String cnp,String idenNumber,String adress);


}
