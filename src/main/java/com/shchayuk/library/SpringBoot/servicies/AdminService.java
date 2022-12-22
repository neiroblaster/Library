package com.shchayuk.library.SpringBoot.servicies;

import com.shchayuk.library.SpringBoot.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service
public class AdminService {

    private final ClientService clientService;

    @Autowired
    public AdminService(ClientService clientService) {
        this.clientService = clientService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Client> doAdminStuff(){
        return clientService.findAll();

    }

}
