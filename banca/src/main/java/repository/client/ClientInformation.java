package repository.client;

import model.Client;

import java.util.List;

public interface ClientInformation {
    List<Client> findAll();

   Client findById(Long id);

    boolean save(Client client); /*add*/

    /* boolean update (Account account);*/

    boolean delete(Client account);

    void removeAll();

}
