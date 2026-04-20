package com.rahim.syntopicalnotes.controllers.v1.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahim.syntopicalnotes.controllers.BaseController;
import com.rahim.syntopicalnotes.domains.dto.auth.LoginDto;
import com.rahim.syntopicalnotes.utils.ResponseFormatter;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController extends BaseController {

    public AuthController(ResponseFormatter responseFormatter) {
		super(responseFormatter);
	}

	@PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginData) {
        int successCode = 201;
        return this.responseFormatter.send(successCode, "test", loginData);
    }

}
