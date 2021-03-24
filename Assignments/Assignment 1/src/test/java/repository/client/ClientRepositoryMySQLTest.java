package repository.client;

import database.DBConnectionFactory;
import database.JDBConnectionWrapper;
import junit.framework.TestCase;
import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;
import repository.account.AccountRepositoryMySQL;

import java.time.LocalDate;
import java.util.List;

public class ClientRepositoryMySQLTest {

    private static ClientRepositoryMySQL repo;

    @BeforeClass
    public static void setup() {
        JDBConnectionWrapper connectionWrapper = DBConnectionFactory.getConnectionWrapper(true);

        repo = new ClientRepositoryMySQL(connectionWrapper.getConnection());
    }

    @Test
    public void testFindAll() {
        List<Client> all = repo.findAll();
        Assert.assertTrue(all.isEmpty());
    }

    @Test
    public void testFindById() throws EntityNotFoundException {
        List<Client> all = repo.findAll();
        Long current = all.get(all.size()-1).getId();

        Client client = new ClientBuilder().setName("Daniela").setAddress("strada Viitorului").setIdentityCardNumber(11111111).setPersonalNumericalCode("2990616ASDF08").build();
        repo.save(client);

        Assert.assertNotNull(repo.findById(current + 1));
    }

    @Test
    public void testSave() {
        boolean res = repo.save(new ClientBuilder().setName("Bianca").setAddress("strada Viitorului").setIdentityCardNumber(11111111).setPersonalNumericalCode("2990616ASDF08").build());
        Assert.assertTrue(res);
    }

    @Test
    public void testRemove() {
        Client client = new ClientBuilder().setName("Bianca").setAddress("strada Viitorului").setIdentityCardNumber(11111111).setPersonalNumericalCode("2990616ASDF08").build();
        repo.save(client);
        boolean res = repo.remove(client.getId());
        Assert.assertTrue(res);
    }

    @Test
    public void testUpdate() {
        Client client = new ClientBuilder().setName("Bianca").setAddress("strada Viitorului").setIdentityCardNumber(11111111).setPersonalNumericalCode("2990616ASDF08").build();
        client.setName("Vlad");
        boolean res = repo.update(client);
        Assert.assertTrue(res);
    }

    @Test
    public void testRemoveAll() {
        repo.save(new ClientBuilder().setName("Bianca1").setAddress("strada Viitorului").setIdentityCardNumber(11111111).setPersonalNumericalCode("2990616ASDF08").build());
        repo.removeAll();
        List<Client> noClients = repo.findAll();
        Assert.assertTrue(noClients.isEmpty());
    }
}