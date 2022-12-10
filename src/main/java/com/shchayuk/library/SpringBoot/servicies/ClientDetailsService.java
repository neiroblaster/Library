package com.shchayuk.library.SpringBoot.servicies;

import com.shchayuk.library.SpringBoot.models.Client;
import com.shchayuk.library.SpringBoot.repositories.ClientRepository;
import com.shchayuk.library.SpringBoot.security.ClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientDetailsService implements UserDetailsService {

    private final ClientRepository userRepository;

    @Autowired
    public ClientDetailsService(ClientRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> client = userRepository.findByUsername(username);

        if (client.isEmpty())
            throw new UsernameNotFoundException("User is not found!");

        return new ClientDetails(client.get());
    }
}
