package com.compass.msbffshop.config.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.compass.msbffshop.feignclients.response.CustomerLogin;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticateFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;
	
	private static final int TOKEN_EXPIRATION = 600000;
	
	public static final String TOKEN_PASSWORD = "bfb27115-6601-4d91-8e87-c3938e3bbd92";
	
	public JWTAuthenticateFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		setFilterProcessesUrl("/v1/login");
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			CustomerLogin customer = new ObjectMapper().readValue(request.getInputStream(), CustomerLogin.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					customer.getEmail(), customer.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException("User authentication failed.");
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		CustomerDetailsData customerData = (CustomerDetailsData) authResult.getPrincipal();
		
		String token = JWT.create()
				.withSubject(customerData.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
				.sign(Algorithm.HMAC512(TOKEN_PASSWORD));
		response.getWriter().write(token);
		response.getWriter().flush();
				
	}

}
