package com.shchayuk.library.SpringBoot.controllers;

import com.shchayuk.library.SpringBoot.models.Client;
import com.shchayuk.library.SpringBoot.servicies.ClientService;
import com.shchayuk.library.SpringBoot.util.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final ClientValidator clientValidator;
    private final ClientService clientService;

    @Autowired
    public AuthController(ClientValidator clientValidator, ClientService clientService) {
        this.clientValidator = clientValidator;
        this.clientService = clientService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute ("client") Client client){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute ("client") @Valid Client client,
                                      BindingResult bindingResult){
        clientValidator.validate(client, bindingResult);

        if(bindingResult.hasErrors()){
            return "/auth/registration";
        }

        clientService.register(client);
        return "redirect:/auth/login";
    }
}
