package model.validation;

import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ClientValidator {

    private static final String PERSONAL_NUMBER_VALIDATION_REGEX = "^[0-9]+$";
    private static final Integer MIN_IDENTITY_CARD_NUMBER = 10000000;
    private static final Integer MAX_IDENTITY_CARD_NUMBER = 99999999;

    private final Client client;
    private final List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public ClientValidator(Client client) {
        this.client = client;
        errors = new ArrayList<>();
    }

    public boolean validate() {
        validateIdentityCardNumber(client.getIdentityCardNumber());
        validatePersonalNumericalCode(client.getPersonalNumericalCode());
        return errors.isEmpty();
    }

    private void validateIdentityCardNumber(Integer identityCardNumber) {
        if (identityCardNumber < MIN_IDENTITY_CARD_NUMBER || identityCardNumber > MAX_IDENTITY_CARD_NUMBER)
            errors.add("The identity card should have 8 digits. Try again.");
    }

    private void validatePersonalNumericalCode(String personalNumericalCode) {

        if (!Pattern.compile(PERSONAL_NUMBER_VALIDATION_REGEX).matcher(personalNumericalCode).matches()) {
            errors.add("Invalid personal numerical code, must contain only numbers");
        }
    }

}
