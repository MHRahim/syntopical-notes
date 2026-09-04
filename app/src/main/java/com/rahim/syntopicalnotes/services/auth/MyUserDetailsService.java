package com.rahim.syntopicalnotes.services.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rahim.syntopicalnotes.domains.dto.auth.UserPrincipal;
import com.rahim.syntopicalnotes.domains.entity.User;
import com.rahim.syntopicalnotes.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;

	public MyUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = this.userRepository.findByEmail(email);


        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User with an email of " + email + " is not found");
        }

        User user = userOptional.get();

        System.out.println("Email fetched from database: " + user.getEmail());

        String authority = "ROLE_" + user.getRole().getName().toUpperCase();

        UserPrincipal userDetails = new UserPrincipal(
            user.getId(),
            user.getEmail(),
            List.of(new SimpleGrantedAuthority(authority))
        );

		return userDetails;
	}

	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        Optional<User> userOptional = this.userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User with an id of " + id + " is not found");
        }

        User user = userOptional.get();

        String authority = "ROLE_" + user.getRole().getName().toUpperCase();

        UserPrincipal userDetails = new UserPrincipal(
            user.getId(),
            user.getEmail(),
            List.of(new SimpleGrantedAuthority(authority))
        );

		return userDetails;
	}
    
}
