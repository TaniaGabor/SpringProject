package repository.client;

public abstract class ClientInformationDecorator implements ClientInformation {
    protected ClientInformation decoratedClientRepository;

    public ClientInformationDecorator(ClientInformation clientRepository) {
        this.decoratedClientRepository = clientRepository;
    }
}
