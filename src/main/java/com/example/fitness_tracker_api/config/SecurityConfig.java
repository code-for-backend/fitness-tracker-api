package com.example.fitness_tracker_api.config;

import com.example.fitness_tracker_api.custom_filter.ApiKeyAuthenticationFilter;
import com.example.fitness_tracker_api.custom_filter.ApiKeyAuthenticationManager;
import com.example.fitness_tracker_api.custom_filter.ApiKeyAuthenticationProvider;
import com.example.fitness_tracker_api.repository.ApplicationRepository;
import com.example.fitness_tracker_api.repository.DeveloperRepository;
import com.example.fitness_tracker_api.service.DeveloperDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.HttpSecurityDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http,AuthenticationManager authenticationManager) throws Exception
    {
       http.authorizeHttpRequests(auth->auth.requestMatchers(HttpMethod.POST,"/api/developers/signup").permitAll()
               .requestMatchers("/h2-console/**").permitAll().requestMatchers(HttpMethod.GET,"/api/developers/*").authenticated()
               .requestMatchers(HttpMethod.POST,"/api/application/register").authenticated()
               .requestMatchers("/api/tracker").authenticated())
               .addFilterBefore(new ApiKeyAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
               .httpBasic(Customizer.withDefaults())
               .csrf(token->token.disable())
               .headers(headers->headers.frameOptions().disable())
               .sessionManagement(sessions->sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
       return http.build();

    }


    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider apiKeyAuthenticationProvider,AuthenticationProvider daoAuthenticationProvider)
    {
        return new ProviderManager(Arrays.asList(apiKeyAuthenticationProvider,daoAuthenticationProvider));
        //since we are using two authentication mechanisms,hence the need of Provider manager
    }

    @Bean
    public AuthenticationProvider apiKeyAuthenticationProvider(ApplicationRepository applicationRepository)
    {
        return new ApiKeyAuthenticationProvider(applicationRepository);
    }

@Bean
    public UserDetailsService userDetailsService(DeveloperRepository developerRepository)
    {
        return new DeveloperDetailsService(developerRepository);

    }
@Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder)
    {
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }



}
