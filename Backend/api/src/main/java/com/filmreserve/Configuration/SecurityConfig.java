package com.filmreserve.Configuration;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${api.version}")
    private String atrVersion;

    @Value("${basic_authentication.username}")
    private String atrUsername;

    @Value("${basic_authentication.password}")
    private String atrPassword;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity prmHttp) throws Exception
    {
        prmHttp.authorizeHttpRequests(request -> {
            request.requestMatchers("/api/" + atrVersion + "/**")
                   .authenticated();
            request.requestMatchers("/api/payment/purchase-back")
                   .permitAll();
            request.requestMatchers("/api/payment/membership-back")
                   .permitAll();
            request.requestMatchers("/api/posters/**")
                   .permitAll();
            request.requestMatchers("/api/avatars/**")
                   .permitAll();
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
        UserDetails varUserDetails = varUser.username(atrUsername)
                                            .password(prmPasswordEncoder.encode(atrPassword))
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
