package com.shchayuk.library.SpringBoot.repositories;

import com.shchayuk.library.SpringBoot.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository <Client, Integer> {
    Optional<Client> findByUsername(String name);
}
