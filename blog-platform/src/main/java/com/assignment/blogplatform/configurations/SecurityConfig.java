package com.assignment.blogplatform.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_name, password, active from users where user_name=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_name, role_name from roles where user_name=?");

        return jdbcUserDetailsManager;

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()

                        // For " /api/logged-users "
                        .requestMatchers(HttpMethod.POST, "/api/logged-users/change-password").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/logged-users/add-roles").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/logged-users/remove-user").hasRole("ADMIN")

                        // For " /api/blogpost "
                        .requestMatchers("/api/blogpost/**").hasAnyRole("USER", "ADMIN")
        );

        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}