package com.example.fitness_tracker_api.custom_filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {
    private final RequestMatcher matcher=new OrRequestMatcher(new AntPathRequestMatcher("/api/tracker","GET"),
            new AntPathRequestMatcher("/api/tracker","POST"));//check API key only for these two request
    private final AuthenticationManager authenticationManager;
    private final AuthenticationEntryPoint authenticationEntryPoint=(((request, response, authException) ->{
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + authException.getMessage() + "\"}");


    }));

    public ApiKeyAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(matcher.matches(request))
        {
            try{
                String apiKey=extractApiKey(request);
                ApiKeyAuthenticationToken authRequestToken=new ApiKeyAuthenticationToken(apiKey);
                Authentication authResponseSuccessToken=authenticationManager.authenticate(authRequestToken);
                //on successful auth
                SecurityContextHolder.getContext().setAuthentication(authResponseSuccessToken);


            }
            catch(AuthenticationException e) //handles both BadCredentialsException and AuthenticationException
            {
                SecurityContextHolder.clearContext();
                authenticationEntryPoint.commence(request,response,e);
                return; //sends response->Spent considerable amount of time here pops the filter off the stack one by one



            }


        }
        filterChain.doFilter(request,response);


    }



    private String extractApiKey(HttpServletRequest request)
    {
        String apiKey=request.getHeader("X-API-Key");
        if((apiKey==null)||apiKey.isBlank())
            throw new BadCredentialsException("Missing X-API-Key header");
        return apiKey;

    }

}
