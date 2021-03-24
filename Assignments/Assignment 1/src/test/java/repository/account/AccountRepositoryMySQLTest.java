package repository.account;

import database.DBConnectionFactory;
import database.JDBConnectionWrapper;
import junit.framework.TestCase;
import model.Account;
import model.builder.AccountBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;

public class AccountRepositoryMySQLTest {

    private static AccountRepositoryMySQL repo;

    @BeforeClass
    public static void setup() {
        JDBConnectionWrapper connectionWrapper = DBConnectionFactory.getConnectionWrapper(true);

        repo = new AccountRepositoryMySQL(connectionWrapper.getConnection());
    }

    @Test
    public void testFindAll() {
        List<Account> all = repo.findAll();
        Assert.assertTrue(all.isEmpty());
    }

    @Test
    public void testFindById() throws EntityNotFoundException {
        List<Account> all = repo.findAll();
        Long current = all.get(all.size()-1).getId();

        Account ac = new AccountBuilder().setType("Savings").setAmountOfMoney(200).setCreationDate(LocalDate.now()).setIdentificationNumber(11111111).build();
        repo.save(ac);

        Assert.assertNotNull(repo.findById(current + 1));
    }

    public void testFindByClientId() {
    }

    @Test
    public void testSave() {
        boolean res = repo.save(new AccountBuilder().setType("Savings").setAmountOfMoney(100).setCreationDate(LocalDate.now()).setIdentificationNumber(12345678).build());
        Assert.assertTrue(res);
    }

    @Test
    public void testRemove() {
        Account ac = new AccountBuilder().setType("Savings").setAmountOfMoney(300).setCreationDate(LocalDate.now()).setIdentificationNumber(13444788).build();
        repo.save(ac);
        boolean res = repo.remove(ac.getId());
        Assert.assertTrue(res);
    }

    @Test
    public void testUpdate() {
        Account ac1 = new AccountBuilder().setType("Savings").setAmountOfMoney(300).setCreationDate(LocalDate.now()).setIdentificationNumber(13444788).build();
        ac1.setType("Current");
        boolean res = repo.update(ac1);
        Assert.assertTrue(res);

    }

    @Test
    public void testTransfer() throws EntityNotFoundException {
        Account ac1 = new AccountBuilder().setType("Savings").setAmountOfMoney(300).setCreationDate(LocalDate.now()).setIdentificationNumber(13444788).build();
        Account ac2 = new AccountBuilder().setType("Savings").setAmountOfMoney(100).setCreationDate(LocalDate.now()).setIdentificationNumber(13444788).build();

        repo.save(ac1);
        repo.save(ac2);

        repo.transfer(ac1,ac2,10);

        boolean res;

        if(ac1.getAmountOfMoney() == 290)
            res = true;
        else
            res = false;

        Assert.assertTrue(res);

    }

    @Test
    public void testRemoveAll() {
        repo.save(new AccountBuilder().setType("Savings").setAmountOfMoney(100).setCreationDate(LocalDate.now()).setIdentificationNumber(12345678).build());
        repo.removeAll();
        List<Account> noAccounts = repo.findAll();
        Assert.assertTrue(noAccounts.isEmpty());
    }
}