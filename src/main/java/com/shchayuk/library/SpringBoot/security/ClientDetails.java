package com.shchayuk.library.SpringBoot.security;

import com.shchayuk.library.SpringBoot.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class ClientDetails implements UserDetails {

    private final Client client;

    @Autowired
    public ClientDetails(Client client) {
        this.client = client;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // здесь можно передавать также коллекцию действий, например:
        // SHOW_ACCOUNT, WITHDRAW_MONEY, SEND_MONEY, ...
        return Collections.singletonList(new SimpleGrantedAuthority(client.getRole()));
    }

    @Override
    public String getPassword() {
        return this.client.getPassword();
    }

    @Override
    public String getUsername() {
        return this.client.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    // Нужно, чтобы получать данные аутентифицированного пользователя
    public Client getClient(){
        return this.client;
    }
}
