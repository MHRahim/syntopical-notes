package com.rahim.syntopicalnotes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rahim.syntopicalnotes.filters.JwtAuthenticationFilter;

public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;


    public SecurityConfiguration(final JwtAuthenticationFilter jwtAuthFilter) {
		this.jwtAuthFilter = jwtAuthFilter;
	}


	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
            .csrf(csrf -> csrf.disable()) 

            .authorizeHttpRequests(a ->
                    a.requestMatchers("/api/auth/login").permitAll()
                    .anyRequest().authenticated()
            )

            .sessionManagement(session -> 
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }




	public JwtAuthenticationFilter getJwtAuthFilter() {
		return jwtAuthFilter;
	} 
}
