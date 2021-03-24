package model.builder;

import model.Account;
import model.Client;
import model.Role;
import model.User;
import view.DTOs.AccountDTO;
import view.DTOs.ClientDTO;

import java.util.List;

public class ClientBuilder {

    private Client client;

    public ClientBuilder() {
        client = new Client();
    }

    public Client buildfromDTO(ClientDTO c) {

        Client client = new ClientBuilder()
                .setName(c.getName())
                .setAddress(c.getAddress())
                .setIdentityCardNumber(c.getIdentityCardNumber())
                .setPersonalNumericalCode(c.getPersonalNumber())
                .build();

        return client;
    }

    public ClientBuilder setId(Long id) {
        client.setId(id);
        return this;
    }

    public ClientBuilder setName(String name) {
        client.setName(name);
        return this;
    }

    public ClientBuilder setIdentityCardNumber(Integer identityCardNumber) {
        client.setIdentityCardNumber(identityCardNumber);
        return this;
    }

    public ClientBuilder setAddress(String address) {
        client.setAddress(address);
        return this;
    }

    public ClientBuilder setPersonalNumericalCode(String personalNumericalCode) {
        client.setPersonalNumericalCode(personalNumericalCode);
        return this;
    }

    public Client build() {
        return client;
    }
}
