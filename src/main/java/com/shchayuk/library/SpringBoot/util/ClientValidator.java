package com.shchayuk.library.SpringBoot.util;

import com.shchayuk.library.SpringBoot.models.Client;
import com.shchayuk.library.SpringBoot.servicies.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClientValidator implements Validator {

    private final ClientService clientService;

    @Autowired
    public ClientValidator(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;

        if(clientService.findByUsername(client.getUsername()).isPresent()){
                errors.rejectValue("username", "", "This name already exists");
        }

    }
}
