package com.example.fitness_tracker_api.custom_filter;

import com.example.fitness_tracker_api.models.Application;
import com.example.fitness_tracker_api.repository.ApplicationRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ApiKeyAuthenticationProvider implements AuthenticationProvider {
    private final ApplicationRepository applicationRepository;

    public ApiKeyAuthenticationProvider(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String apiKey=(String)authentication.getCredentials();
        Application application=applicationRepository.findApplicationByApiKey(apiKey)
                .orElseThrow(()->new BadCredentialsException("Invalid API Key!")); //Failed auth invalid Api key
        //Successful authentication
        ApiKeyAuthenticationToken authResponseToken=new ApiKeyAuthenticationToken(apiKey,application);
            return authResponseToken;





    }
/*
Its imp otherwise Basic Authentication might use our auth provider!
 */
    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthenticationToken.class.equals(authentication);
    }
}
