package com.filmreserve.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity prmHttp) throws Exception
    {
        prmHttp.authorizeHttpRequests(request -> {
            request.anyRequest()
                   .authenticated();
        })
        .httpBasic(Customizer.withDefaults())
        .csrf(csrf -> csrf.disable());
        prmHttp.cors(cors -> cors.configure(prmHttp));
        return prmHttp.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder prmPasswordEncoder) throws Exception {
        // ensure the passwords are encoded properly
        User.UserBuilder varUser = User.builder();
        UserDetails varUserDetails = varUser.username("filmreserve")
                                            .password(prmPasswordEncoder.encode("123"))
                                            .roles()
                                            .build();
        return new InMemoryUserDetailsManager(varUserDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
