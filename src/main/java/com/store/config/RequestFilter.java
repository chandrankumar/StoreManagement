package com.store.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.store.service.AuthenticationService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class RequestFilter extends OncePerRequestFilter {

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	AuthenticationService authService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String jwtToken, username = null;
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			jwtToken = header.substring(7);
			try {
				// Retrieve the username from JWT Token
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}

			if (username != null) {

				UserDetails userDetails = authService.loadUserByUsername(username);

				if (jwtTokenUtil.isvalidateToken(jwtToken, userDetails)) {

					String role = userDetails.getAuthorities().stream().findFirst().toString();
					System.out.println("Role " + role);
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, Arrays.asList(new SimpleGrantedAuthority(role)));
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		}
		filterChain.doFilter(request, response);
	}

}
