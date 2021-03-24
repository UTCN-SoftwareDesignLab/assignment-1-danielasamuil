package service.client;

import model.Account;
import model.Client;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;

public interface ClientService {

    List<Client> findAll();

    Client findById(Long id) throws EntityNotFoundException;

    Notification<Boolean> save(Long id, String name, Integer cardNumber, String address, String personalNumCode, List<Account> accounts);

    void removeAll();

    boolean remove(Long id);

    Notification<Boolean> update(Long id, String name, Integer cardNumber, String address, String personalNumCode, List<Account> accounts);

}
