package repository.client;

import model.Client;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientInformationMock implements ClientRepository {

    private List<Client> clients;

    public ClientInformationMock() {
        clients = new ArrayList<>();
    }

    public List<Client> findAll() {
        return clients;
    }

    public Client findById(Long id) {

        List<Client> filteredClients = clients.parallelStream()
                .filter(i -> i.getId().equals(id))
                .collect(Collectors.toList());
        if (filteredClients.size() > 0) {
            return filteredClients.get(0);
        }

        return null;
    }

    @Override
    public boolean save(Client client) {
        return clients.add(client);
    }

    /*  @Override
      public boolean update(Account account) {
          ListIterator<Account> listIterator = accounts.listIterator();

          while(listIterator.hasNext()) {
               if( listIterator.next().equals(account))
               {
                   listIterator.set(account);
                   return true;
               }
          }
        return false;
      }
  */
    @Override
    public boolean delete(Client client) {
        return clients.remove(client);
    }

    @Override
    public void removeAll() {
        clients.clear();
    }
}
