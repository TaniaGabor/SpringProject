package test;

import model.Account;

import org.junit.*;
import org.junit.jupiter.api.Test;
import repository.account.AccountInformationMock;
import service.account.AccountService;
import service.account.AccountServiceImpl;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AccountServiceImplTest {

    private static AccountService accountService;

    @BeforeClass
    public static void init()
    {
        accountService = new AccountServiceImpl(new AccountInformationMock());
        System.out.println("Before class.");
    }


    @Before
    public void setup()
    {
        System.out.println("Before.");

    }


    @After
    public void clean()
    {
        System.out.println("After");
    }

    @AfterClass
    public static void cleanUp()
    {
        System.out.println("After class");
    }

    @Test
    public void findAll()
    {
        assertEquals(0, accountService.findAll().size());
        System.out.println("find all");

    }

    @Test
    public void findById()
    {
        Long id = 1L;
        assertNull(accountService.findById(id));
        System.out.println("find by id");

    }

    @Test
    public void save(){
        assertTrue(accountService.save(new Account()));
        System.out.println("save");
    }

}
