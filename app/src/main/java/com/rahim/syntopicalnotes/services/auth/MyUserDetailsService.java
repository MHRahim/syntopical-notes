package com.rahim.syntopicalnotes.services.auth;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.rahim.syntopicalnotes.domains.dto.auth.UserPrincipal;
import com.rahim.syntopicalnotes.domains.entity.User;
import com.rahim.syntopicalnotes.repositories.UserRepository;

public class MyUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;

	public MyUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Long id = Long.valueOf(username);

        Optional<User> userOptional = this.userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User with an id of " + username + " is not found");
        }

        User user = userOptional.get();
        UserPrincipal userDetails = new UserPrincipal();
        userDetails.setUsername(String.valueOf(user.getId()));

		return userDetails;
	}

    
    
}
