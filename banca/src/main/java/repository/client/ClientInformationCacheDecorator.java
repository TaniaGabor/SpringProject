package repository.client;


import model.Client;
import repository.Cache;

import java.util.List;
import java.util.stream.Collectors;

public class ClientInformationCacheDecorator extends ClientInformationDecorator {

    private Cache<Client> cache;

    public ClientInformationCacheDecorator(ClientInformation clientRepository, Cache<Client> cache)
    {
        super(clientRepository);
        this.cache=cache;
    }

    @Override
    public List<Client> findAll() {
        if(cache.hasResult())
        {
            return cache.load();
        }

        List<Client> clients = decoratedClientRepository.findAll();
        cache.save(clients);
        return clients;
    }

    @Override
    public Client findById(Long id) {
        if(cache.hasResult())
        {
            List<Client> clients = cache.load().parallelStream()
                    .filter(i -> i.getId().equals(id))
                    .collect(Collectors.toList());
            return clients.get(0);
        }

        return decoratedClientRepository.findById(id);
    }

    @Override
    public boolean save(Client client) {
        cache.invalidateCache();
        return decoratedClientRepository.save(client);
    }


    /*@Override
    public boolean update(Account account) {
        cache.invalidateCache();

    }*/

    @Override
    public boolean delete(Client client) {
        cache.invalidateCache();
        return decoratedClientRepository.delete(client);
    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedClientRepository.removeAll();
    }
}
