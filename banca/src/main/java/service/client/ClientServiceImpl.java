package service.client;
import model.Client;
import repository.client.ClientInformation;



import java.util.List;

public class ClientServiceImpl implements ClientService {

    private final ClientInformation clientRepository;

    public ClientServiceImpl(ClientInformation aClientRepository)
    {
        clientRepository = aClientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public boolean save(Client client) {
        return clientRepository.save(client);
    }


}
