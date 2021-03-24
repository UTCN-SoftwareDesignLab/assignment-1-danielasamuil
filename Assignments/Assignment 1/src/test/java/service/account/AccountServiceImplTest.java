package service.account;

import database.DBConnectionFactory;
import database.JDBConnectionWrapper;
import junit.framework.TestCase;
import model.Account;
import model.builder.AccountBuilder;
import model.validation.Notification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;
import repository.account.AccountRepositoryMySQL;

import java.time.LocalDate;
import java.util.List;

public class AccountServiceImplTest {
    private static AccountServiceImpl service;

    @BeforeClass
    public static void setup() {
        JDBConnectionWrapper connectionWrapper = DBConnectionFactory.getConnectionWrapper(true);

        service = new AccountServiceImpl(new AccountRepositoryMySQL(connectionWrapper.getConnection()));
    }

    @Test
    public void testFindAll() {
        List<Account> all = service.findAll();
        Assert.assertTrue(all.isEmpty());
    }

    @Test
    public void testFindById() throws EntityNotFoundException {
        List<Account> all = service.findAll();
        Long current = all.get(all.size()-1).getId();

        Account ac = new AccountBuilder().setType("Savings").setAmountOfMoney(200).setCreationDate(LocalDate.now()).setIdentificationNumber(11111111).build();
        service.save(ac);
        Assert.assertNotNull(service.findById(current + 1));
    }

    public void testFindByClientId() {
    }

    @Test
    public void testSave() {
        Account ac = new AccountBuilder().setType("Savings").setAmountOfMoney(100).setCreationDate(LocalDate.now()).setIdentificationNumber(12345678).build();
        Notification<Boolean> res = service.save(ac);
        Assert.assertTrue(res.getFormattedErrors().isEmpty());
    }

    @Test
    public void testUpdate() {
        Account ac = new AccountBuilder().setType("Savings").setAmountOfMoney(300).setCreationDate(LocalDate.now()).setIdentificationNumber(13444788).build();
        ac.setType("Current");
        Notification<Boolean> res = service.update(ac);
        Assert.assertTrue(res.getFormattedErrors().isEmpty());
    }

    @Test
    public void testRemove() {
        Account ac = new AccountBuilder().setType("Savings").setAmountOfMoney(300).setCreationDate(LocalDate.now()).setIdentificationNumber(13444788).build();
        service.update(ac);
        boolean res = service.remove(ac.getId());
        Assert.assertTrue(res);
    }

    @Test
    public void testTransfer() throws EntityNotFoundException {
        Account ac1 = new AccountBuilder().setType("Savings").setAmountOfMoney(300).setCreationDate(LocalDate.now()).setIdentificationNumber(13444788).build();
        Account ac2 = new AccountBuilder().setType("Savings").setAmountOfMoney(100).setCreationDate(LocalDate.now()).setIdentificationNumber(13444788).build();

        service.save(ac1);
        service.save(ac2);

        service.transfer(ac1,ac2,10);

        boolean res;

        if(ac1.getAmountOfMoney() == 290)
            res = true;
        else
            res = false;

        Assert.assertTrue(res);
    }

    @Test
    public void testRemoveAll() {
        Account ac = new AccountBuilder().setType("Savings").setAmountOfMoney(100).setCreationDate(LocalDate.now()).setIdentificationNumber(12345678).build();
        service.save(ac);
        service.removeAll();
        List<Account> noAccounts = service.findAll();
        Assert.assertTrue(noAccounts.isEmpty());
    }
}