package com.rahim.syntopicalnotes.controllers.v1.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahim.syntopicalnotes.controllers.BaseController;
import com.rahim.syntopicalnotes.domains.dto.auth.LoginDto;
import com.rahim.syntopicalnotes.domains.dto.auth.UserPrincipal;
import com.rahim.syntopicalnotes.domains.entity.User;
import com.rahim.syntopicalnotes.repositories.UserRepository;
import com.rahim.syntopicalnotes.services.auth.JWTService;
import com.rahim.syntopicalnotes.utils.ResponseFormatter;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController extends BaseController {
    private final AuthenticationManager authManager;
    private final JWTService jwtService;
    private final UserRepository userRepository;

    public AuthController(
            ResponseFormatter responseFormatter, 
            AuthenticationManager authManager,
            JWTService jwtService,
            UserRepository userRepository
        ) {
		super(responseFormatter);
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
	}

	@PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginData) {
        int successCode = 201;
        Authentication authentication = this.authManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginData.email(), loginData.password())
        );

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        String token = "";
        try {
            token = jwtService.generateJWTToken(principal.getId());
        }catch(Exception e) {
            System.err.println(e.getMessage());
            return this.responseFormatter.send(400, "Some thing went wrong", null);
        }

        Map<String, Object> response = new HashMap<>();

        response.put("access", token);

        return this.responseFormatter.send(successCode, "test", response);
    }

	@GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        int successCode = 200;
        return this.responseFormatter.send(successCode, "test", principal);
    }
}
