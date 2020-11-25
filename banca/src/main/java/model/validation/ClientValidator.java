package model.validation;

import model.Client;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientValidator {
    private static final String CNP_VALIDATION_REGEX = "^[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d$";


    public List<String> getErrors() {
        return errors;
    }

    private final List<String> errors;

    private final Client client;


    public ClientValidator(Client client) {
        this.client = client;
        errors = new ArrayList<>();
    }

    public boolean validate(boolean validateCnpOnly) {
        validateCnp(client.getPersonalNumericalCode());
        if (!validateCnpOnly) {
            validateIdentificationNumber(client.getIdentificationNumber());
        }
        return errors.isEmpty();
    }

    private void validateIdentificationNumber(String s)
    {
        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (!Character.isDigit(c)) {
                    errors.add("Invalid Identification Number!");
                    return;

                }
            }
        }


    }

    private void validateCnp(String username) {
        if (!Pattern.compile(CNP_VALIDATION_REGEX).matcher(username).matches()) {
            errors.add("Invalid CNP!");
        }
    }







}
