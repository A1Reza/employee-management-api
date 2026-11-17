package com.employees.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/employees",
                                "/dashboard",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/h2-console/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers
                        .frameOptions(frame -> frame.disable())
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

/*
How does Spring Security prevent CSRF(Cross-Site Request Forgery)?
Spring Security uses a mechanism called CSRF Token.

The server generates a Token and provides it to the client.

The client must also send this Token when sending sensitive requests such as POST, PUT, and DELETE.

For example:
POST /employees
Content-Type: application/json
X-CSRF-TOKEN: abc123xyz

The server checks the received Token and rejects the request if it is not valid.
So why did we disable CSRF?
In our project, we are creating a REST API and want to test it with Postman.
.csrf(csrf -> csrf.disable())

With this command, CSRF protection is disabled.

As a result, we do not need to send CSRF Token for POST and DELETE.

But one important security note:

Disabling CSRF just because it's a REST API isn't always a good idea.
If authentication is done with Session and Cookies, we may still be vulnerable to CSRF.
For APIs that use a Bearer Token in the Authorization header and the browser doesn't automatically send it,
it usually makes sense to disable CSRF.
*/
