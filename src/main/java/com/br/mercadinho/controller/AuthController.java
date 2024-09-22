package com.br.mercadinho.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.mercadinho.DTO.LoginRequestDTO;

import com.br.mercadinho.DTO.RegisterRequestDTO;
import com.br.mercadinho.DTO.ResponseDTO;
import com.br.mercadinho.entity.user.User;
import com.br.mercadinho.infra.security.TokenService;
import com.br.mercadinho.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final TokenService tokenService;
	
	@Autowired
    public AuthController(UserRepository repository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

	
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginRequestDTO body) {
		User user = this.repository.findByUsername(body.username()).orElseThrow(() -> new RuntimeException("User not found"));
		if(passwordEncoder.matches(body.password(),user.getPassword())) {
			String token = this.tokenService.generateToken(user);
			return ResponseEntity.ok(new ResponseDTO(user.getUsername(), token));
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
		Optional<User> user = this.repository.findByUsername(body.username());
		if(user.isEmpty()) {
			User newUser = new User();
			newUser.setPassword(passwordEncoder.encode(body.password()));
			newUser.setUsername(body.username());
			this.repository.save(newUser);
			String token = this.tokenService.generateToken(newUser); 
			return ResponseEntity.ok(new ResponseDTO(newUser.getUsername(), token));			
		}
		return ResponseEntity.badRequest().build();
	}
}
