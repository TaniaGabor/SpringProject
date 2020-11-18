package service.client;



import model.Client;

import java.util.List;

public interface ClientService {

    List<Client> findAll();

    Client findById(Long id);

    boolean save(Client client);


}
