package com.example.fitness_tracker_api.custom_filter;

import com.example.fitness_tracker_api.models.Application;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/*

For a authRequest token,spring security will check for token.isAuthenticated() which should be
 false,before calling authenticationManager.authenticate()
 Its a immutable token so we return a new fully populated token on successful auth.


 */

public class ApiKeyAuthenticationToken implements Authentication {
    private final String apiKey;
    private Application application;
    private boolean authenticated=false;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    // Optional constructor for authenticated state
    public ApiKeyAuthenticationToken(String apiKey,Application application) {
        this.apiKey = apiKey;
        this.application = application;
        this.authenticated = true;
    }

    /*
    For authRequest
     */
    public ApiKeyAuthenticationToken(String apiKey) {
        this.apiKey = apiKey;
    }
/*
    This is usually the password
     */
    @Override
    public Object getCredentials() {
        return apiKey;
    }


    @Override
    public Object getDetails() {
        return null;
    }

    /*
    Mostly userDetails object
     */
    @Override
    public Object getPrincipal() {
        return application;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated=authenticated;
    }


    @Override
    public String getName() {
        return application==null?"anon":application.getName();

    }
}
