package br.senai.sp.informatica.cadastro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.informatica.cadastro.model.valueobject.LoginRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/signin")
	public ResponseEntity<?> autentication(@RequestBody @Valid LoginRequest login){
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						login.getUsername(),   login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
				return ResponseEntity.ok().build();
	}
	
	
}
