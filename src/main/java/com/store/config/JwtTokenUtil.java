package com.store.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6161860573216428876L;

	public static final long JWT_TOKEN_VALIDITY = 1 * 60 * 60;

	@Value("${jwt.secret}")
	private String secretKey;

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());

	}

	private String doGenerateToken(Map<String, Object> claims, String username) {
		return Jwts.builder().setClaims(claims).setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}

	public String getUsernameFromToken(String jwtToken) {
		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody();
		return getClaimFromToken(claims, Claims::getSubject);
	}

	public boolean isvalidateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	public Boolean isTokenExpired(String token) {
		final Date expirationDateFromToken = getExpirationDateFromToken(token);
		return expirationDateFromToken.before(new Date());
	}

	private Date getExpirationDateFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		return getClaimFromToken(claims, Claims::getExpiration);

	}

	private <T> T getClaimFromToken(Claims claim, Function<Claims, T> claimResolver) {
		return claimResolver.apply(claim);
	}

}
