package com.gpa.backendrest.app.security;

import org.springframework.stereotype.Component;

import com.gpa.backendrest.app.constants.Constants;
import com.gpa.backendrest.app.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {

	public String generate(JwtUser jwtUser) {
		Claims claims = Jwts.claims().setSubject(jwtUser.getUserName());
		claims.put(Constants.USER_ID, String.valueOf(jwtUser.getId()));

		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, Constants.YOUR_SECRET).compact();
	}
}
