package repository.client;

import database.DBConnectionFactory;
import model.Client;
import model.builder.ClientBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientRepositoryMySqlTest {
    private static ClientRepository clientRepository;

    @BeforeClass
    public static void setupClass() {
        clientRepository = new ClientRepositoryCacheDecorator(
                new ClientRepositoryMySQL(
                        new DBConnectionFactory().getConnectionWrapper(true).getConnection()
                ),
                new Cache<>()
        );
    }
    @Test
    public void save() throws Exception {
        assertTrue(clientRepository.save(new ClientBuilder()
                .setId(1)
                .setPersonalNumericalCode("6000312056045")
                .setIdentificationNumber("1289").setName("Tania Gabor")
                .setAdress("Satu Mare")
                .build()));
    }

@Test
public void delete() throws Exception {
    assertEquals(clientRepository.delete(new ClientBuilder()
            .setId(1)
            .setPersonalNumericalCode("6000312056047")
            .setIdentificationNumber("1289").setName("Tania Gabor")
            .setAdress("Satu Mare")
            .build()),false);
}







}
