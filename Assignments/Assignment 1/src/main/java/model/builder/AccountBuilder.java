package model.builder;

import model.Account;

import java.time.LocalDate;
import java.util.Date;

public class AccountBuilder {

    private Account account;

    public AccountBuilder() {
        account = new Account();
    }

    public AccountBuilder setId(Long id) {
        account.setId(id);
        return this;
    }

    public AccountBuilder setIdentificationNumber(Integer identificationNumber) {
        account.setIdentificationNumber(identificationNumber);
        return this;
    }

    public AccountBuilder setType(String type) {
        account.setType(type);
        return this;
    }

    public AccountBuilder setAmountOfMoney(Integer amountOfMoney) {
        account.setAmountOfMoney(amountOfMoney);
        return this;
    }

    public AccountBuilder setCreationDate(LocalDate creationDate) {
        account.setCreationDate(creationDate);
        return this;
    }

    public Account build() {
        return account;
    }
}
