package service.client;

import database.DBConnectionFactory;
import database.JDBConnectionWrapper;
import junit.framework.TestCase;
import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import model.validation.Notification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;
import repository.account.AccountRepositoryMySQL;
import repository.client.ClientRepositoryMySQL;
import service.account.AccountServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class ClientServiceImplTest {
    private static ClientServiceImpl service;

    @BeforeClass
    public static void setup() {
        JDBConnectionWrapper connectionWrapper = DBConnectionFactory.getConnectionWrapper(true);

        service = new ClientServiceImpl(new ClientRepositoryMySQL(connectionWrapper.getConnection()));
    }

    @Test
    public void testFindAll() {
        List<Client> all = service.findAll();
        Assert.assertTrue(all.isEmpty());
    }

    @Test
    public void testFindById() throws EntityNotFoundException {
        List<Client> all = service.findAll();
        Long current = all.get(all.size()-1).getId();

        Client client = new ClientBuilder().setName("Daniela").setAddress("strada Viitorului").setIdentityCardNumber(11111111).setPersonalNumericalCode("2990616125111").build();
        Notification<Boolean> res = service.save(client);

        Assert.assertNotNull(service.findById(current + 1));
    }

    @Test
    public void testSave() {
        Client client = new ClientBuilder().setName("Daniela").setAddress("strada Viitorului").setIdentityCardNumber(11111111).setPersonalNumericalCode("2990616125111").build();

        Notification<Boolean> res = service.save(client);
        Assert.assertTrue(res.getResult());
    }

    @Test
    public void testUpdate() {
        Client client = new ClientBuilder().setName("Daniela").setAddress("strada Viitorului").setIdentityCardNumber(11111111).setPersonalNumericalCode("2990616125111").build();
        client.setAddress("strada Rozelor");
        Notification<Boolean> res = service.save(client);
        Assert.assertTrue(res.getFormattedErrors().isEmpty());
    }

    @Test
    public void testRemove() {
        Client client = new ClientBuilder().setName("Daniela").setAddress("strada Viitorului").setIdentityCardNumber(11111111).setPersonalNumericalCode("2990616125111").build();
        service.update(client);
        boolean res = service.remove(client.getId());
        Assert.assertTrue(res);
    }

    @Test
    public void testRemoveAll() {
        Client client = new ClientBuilder().setName("Daniela").setAddress("strada Viitorului").setIdentityCardNumber(11111111).setPersonalNumericalCode("2990616125111").build();
        service.save(client);
        service.removeAll();
        List<Client> noClients = service.findAll();
        Assert.assertTrue(noClients.isEmpty());
    }
}