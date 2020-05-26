package br.com.example.restwithspringbootudemy.configuration.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.example.restwithspringbootudemy.model.User;
import br.com.example.restwithspringbootudemy.service.UserService;

public class TokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private UserService userService;

	public TokenFilter(TokenService tokenService, UserService userService) {
		this.tokenService = tokenService;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
		String token = retrieveToken(request);
		boolean isValid = tokenService.isValidToken(token);
		if(isValid) {
			doClientAuthentication(token);
		}
		filterChain.doFilter(request, response);
	}

	private void doClientAuthentication(String token) {
		Long userId = tokenService.getUserId(token);
		User user = userService.findById(userId);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String retrieveToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(StringUtils.isNotEmpty(token)&& token.startsWith("Bearer ")) {
			return token.substring(7,token.length());
		}
		return null;
	}

}
