package com.rahim.syntopicalnotes.mappers.auth;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rahim.syntopicalnotes.domains.dto.auth.CreateUser;
import com.rahim.syntopicalnotes.domains.entity.Role;
import com.rahim.syntopicalnotes.domains.entity.User;
import com.rahim.syntopicalnotes.mappers.Mapper;
import com.rahim.syntopicalnotes.repositories.RoleRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CreateUserMapper implements Mapper<CreateUser, User> {
    
    private final RoleRepository roleRepository;



	@Override
	public List<CreateUser> mapFromMany(List<User> b) {
		return null;
	}

	public User mapTo(CreateUser c) {
        Role role = this.roleRepository.findById(c.getRole_id()).orElseThrow(
                () -> new EntityNotFoundException("Role with ID " + c.getRole_id() + " was not found in the database.") 
            );

        Date now = new Date();

        User user = new User();
        user.setEmail(c.getEmail());
        user.setName(c.getName());        
        user.setRole(role);        
        user.setPassword(c.getPassword());
        user.setCreated_at(now);
        user.setUpdated_at(now);

        return user;
    }

    public CreateUser mapFrom(User u) {
        CreateUser createUser = new CreateUser();
        createUser.setEmail(u.getEmail());
        createUser.setName(u.getName());
        createUser.setPassword(u.getPassword());
        createUser.setRole_id(u.getRole().getId());
        return createUser;
    }
}
