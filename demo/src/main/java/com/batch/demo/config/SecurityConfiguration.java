package com.batch.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests((authz) -> authz
            .requestMatchers(HttpMethod.GET,"/supplier/**")
                .permitAll()
                );
        
        http.authorizeHttpRequests((authz) -> authz
                .requestMatchers("/**").permitAll().anyRequest().authenticated()   
        );
        return http.build();
    }

    /* NOTES
    * Spring Security uses multiple filters to evaluate requests -> SecurityFilterChain

    @Bean   // Empty Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.build();
    }

    @Bean   // All requests authenticated
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        return http.build();
    }

    @Bean   // All requests authenticated with csrf disabled
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        return http.build();
    }

    @Bean  // Disabling security for a path
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests((authz) -> authz
        .requestMatchers(HttpMethod.GET, "/supplier/**").permitAll().anyRequest().authenticated()   );
        return http.build();
    }

    @Bean  // Disabling security for multiple paths
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests((authz) -> authz
        .requestMatchers(HttpMethod.GET, "/cat/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/catNative/**").permitAll().anyRequest().authenticated()   );
        return http.build();
    }
    */


}
