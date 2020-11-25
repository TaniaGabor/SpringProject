package repository.client;

import model.Client;
import repository.Cache;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryDecorator;

import java.util.List;



public class ClientRepositoryCacheDecorator extends ClientRepositoryDecorator {

    private Cache<Client> cache;

    public ClientRepositoryCacheDecorator(ClientRepository clientRepository, Cache<Client> cache) {
        super(clientRepository);
        this.cache = cache;
    }




    @Override
    public boolean save(Client book) {
        cache.invalidateCache();
        return decoratedRepository.save(book);
    }


}