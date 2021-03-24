package service.account;

import model.Account;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AccountService {
    List<Account> findAll();

    Account findById(Long id) throws EntityNotFoundException;

    Account findByClientId(Long clientId) throws EntityNotFoundException;

    Notification<Boolean> save(Long id, Integer identificationNumber, String type, Integer moneyAmount, LocalDate creationDate);

    Notification<Boolean> update(Long id, Integer identificationNumber, String type, Integer moneyAmount, LocalDate creationDate);

    void removeAll();

    boolean remove(Long id);
    void transfer(Account account1, Account account2, Integer amount) throws EntityNotFoundException;
}