package com.example.fitness_tracker_api.config;

import com.example.fitness_tracker_api.service.DeveloperDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.HttpSecurityDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception
    {
       http.authorizeHttpRequests(auth->auth.requestMatchers(HttpMethod.POST,"/api/developers/signup").permitAll()
               .requestMatchers("/h2-console/**").permitAll().requestMatchers(HttpMethod.GET,"/api/developers/*").authenticated())
               .httpBasic(Customizer.withDefaults())
               .csrf(token->token.disable())
               .headers(headers->headers.frameOptions().disable())
               .sessionManagement(sessions->sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
       return http.build();

    }


    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(15);
    }


}
