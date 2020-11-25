package test.java;

import model.Client;
import model.builder.ClientBuilder;
import model.validation.Notification;
import org.junit.*;
import org.junit.Test;
import repository.client.ClientException;
import repository.client.ClientRepositoryMock;
import service.client.ClientService;
import service.client.ClientServiceImpl;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ClientServiceImplTest {

    private static ClientService clientService;

    @BeforeClass
    public static void init()
    {
        clientService = new ClientServiceImpl(new ClientRepositoryMock());
        System.out.println("Before class.");
    }


     @Test
     public void save()
     {
         boolean ans=true;
         boolean val=clientService.getClientRepository().save(new ClientBuilder()
                 .setId(1)
                 .setPersonalNumericalCode("6000312056047")
                 .setIdentificationNumber("123").setName("Tania Gabor")
                 .setAdress("Satu Mare")
                 .build());
         assertEquals(ans,val);
     }
     @Before
             public void add () {
                 clientService.getClientRepository().save(new ClientBuilder()
                 .setId(1)
                 .setPersonalNumericalCode("6000312056047")
                 .setIdentificationNumber("123").setName("Tania Gabor")
                 .setAdress("Satu Mare")
                 .build());
     }

    @Test
    public void modify() throws ClientException {
        Client client=new ClientBuilder()
                .setId(1)
                .setPersonalNumericalCode("6000312056047")
                .setIdentificationNumber("123").setName("Tania Gabor")
                .setAdress("Satu Mare")
                .build();

        clientService.getClientRepository().modify("6000312056047","Angela Gabor","Cluj Napoca");
        assertEquals("Angela Gabor",clientService.getClientRepository().findbyCNP("6000312056047").getResult().getName());


    }
    @Before
    public void add1 () {
        clientService.getClientRepository().save(new ClientBuilder()
                .setId(1)
                .setPersonalNumericalCode("6000312056047")
                .setIdentificationNumber("123").setName("Tania Gabor")
                .setAdress("Satu Mare")
                .build());
    }
    @Test
      public void findbyCNP() throws ClientException {
        Notification<Client> client=clientService.getClientRepository().findbyCNP("6000312056047");
        assertEquals(client.getResult(),new ClientBuilder()
                .setId(1)
                .setPersonalNumericalCode("6000312056047")
                .setIdentificationNumber("123").setName("Tania Gabor")
                .setAdress("Satu Mare")
                .build());
    }


    @AfterClass
    public static void cleanUp()
    {
        System.out.println("After class");
    }
}
