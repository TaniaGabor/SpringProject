package repository.client;

import model.Client;
import model.validation.Notification;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientRepositoryMock implements ClientRepository {

    private List<Client> clients;

    public ClientRepositoryMock() {
        clients = new ArrayList<>();
    }



    @Override
    public Notification<Client> findbyCNP(String CNP) throws ClientException {
        Notification<Client> notif= new Notification<>();
        List<Client> filteredClients = clients.parallelStream()
                .filter(i -> i.getPersonalNumericalCode().equals(CNP))
                .collect(Collectors.toList());
        if (filteredClients.size() > 0) {
             notif.setResult(filteredClients.get(0));
            return notif;
        }
        return null;


    }



    @Override
    public void modify(String cnp, String newName, String adress) {
        List<Client> filteredClients = clients.parallelStream()
                .filter(i -> i.getPersonalNumericalCode().equals(cnp))
                .collect(Collectors.toList());
        if (filteredClients.size() > 0) {
            filteredClients.get(0).setAdress(adress);
            filteredClients.get(0).setName(newName);

        }

    }


    @Override
    public boolean save(Client client) {
        return clients.add(client);
    }


    @Override
    public boolean delete(Client client) {
        return clients.remove(client);
    }

}
