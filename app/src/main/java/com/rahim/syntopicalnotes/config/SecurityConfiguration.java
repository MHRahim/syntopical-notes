package com.rahim.syntopicalnotes.config;

import java.lang.reflect.Constructor;
import java.security.AuthProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rahim.syntopicalnotes.filters.JwtAuthenticationFilter;
import com.rahim.syntopicalnotes.services.auth.MyUserDetailsService;


@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration {
    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


	@Bean
    @Order(1)
    public SecurityFilterChain publicFilterChain(HttpSecurity http) {
        return common(http)
            .securityMatcher("/api/v1/auth/login")
            .authorizeHttpRequests(a ->
                    a.anyRequest().permitAll()
            )
        .build(); 
    }

    @Bean
    @Order(2)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) {

        return common(http)
            .securityMatcher("/api/v1/**")
            .authorizeHttpRequests(a ->
                    a.anyRequest().authenticated())
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build(); 

    }

    @Bean
    public AuthenticationManager authenticationManager(MyUserDetailsService service,PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider authManager = new DaoAuthenticationProvider(service);
        authManager.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authManager);
    }

    private HttpSecurity common(HttpSecurity http) {
        return http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    }


	// @Bean
	//    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthFilter) {
	//        http
	//            .csrf(csrf -> csrf.disable()) 
	//
	//            .authorizeHttpRequests(a ->
	//                    a.requestMatchers("/api/v1/auth/login").permitAll()
	//                    .anyRequest().authenticated()
	//            )
	//
	//            .sessionManagement(session -> 
	//                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	//            )
	//            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
	//
	//
	//        return http.build();
	//    }




	// 	return jwtAuthFilter;
    // public JwtAuthenticationFilter getJwtAuthFilter() {
	// } 
}
