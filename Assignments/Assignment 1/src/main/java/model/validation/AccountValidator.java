package model.validation;

import model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountValidator {

    private static final Integer MIN_IDENTIFICATION_NUMBER = 10000000;
    private static final Integer MAX_IDENTIFICATION_NUMBER = 99999999;

    private final Account account;
    private final List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public AccountValidator(Account account) {
        this.account = account;
        errors = new ArrayList<>();
    }

    public boolean validate() {
        validateIdentificationNumber(account.getIdentificationNumber());
        validateType(account.getType());
        return errors.isEmpty();
    }

    private void validateIdentificationNumber(Integer identificationNumber) {
        if (identificationNumber < MIN_IDENTIFICATION_NUMBER || identificationNumber > MAX_IDENTIFICATION_NUMBER)
            errors.add("The identification number should have 8 digits. Try again.");
    }

    private void validateType(String type) {
        if (!type.equals("Savings") && !type.equals("Current") && !type.equals("Reccurring Deposit") && !type.equals("Fixed Deposit"))
            errors.add("The type inserted is not a legit one. Try one of these: Savings, Current, Reccurring Deposit, Fixed Deposit");
    }

}
