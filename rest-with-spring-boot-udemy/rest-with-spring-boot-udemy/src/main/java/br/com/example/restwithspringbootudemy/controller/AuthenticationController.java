package br.com.example.restwithspringbootudemy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.restwithspringbootudemy.configuration.security.TokenService;
import br.com.example.restwithspringbootudemy.dto.LoginDto;
import br.com.example.restwithspringbootudemy.dto.TokenDto;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginDto form ){
		UsernamePasswordAuthenticationToken login = form.convert();
		try {
			Authentication authentication = authManager.authenticate(login);
			String token = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new TokenDto(token,"Bearer"));		
		}catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();	
		}
		
	}

}
