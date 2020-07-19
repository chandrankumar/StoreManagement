package com.store.controller;

import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.config.JwtTokenUtil;
import com.store.entities.JwtRequest;
import com.store.entities.JwtResponse;
import com.store.entities.StoreManagementException;
import com.store.entities.Users;
import com.store.service.AuthenticationService;

@CrossOrigin
@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

	@Autowired
	AuthenticationService authService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	JwtResponse response;
	
	private static Logger log = Logger.getLogger(AuthenticationController.class.getName());  

	@PostMapping("/register/")
	public JwtResponse registerUser(@RequestBody Users user) throws Exception {

		log.info("Getting inside registerUser "+user.toString());
		String responseMsg = authService.registerUser(user);
		response.setJwttoken(null);
		response.setMessage(responseMsg);
		log.info("User register response: "+responseMsg);
		return response;
	}

	@PostMapping("/login")
	public JwtResponse authenticateUser(@RequestBody JwtRequest user) throws Exception {

		log.info("Getting inside authenticateUser "+user.toString());
		Boolean authenticate = authenticate(user.getUsername(), user.getPassword());
		if (authenticate) {
			UserDetails userDetails = authService.loadUserByUsername(user.getUsername());
			String generateToken = jwtTokenUtil.generateToken(userDetails);
			System.out.println("Token: " + generateToken);
			response.setJwttoken(generateToken);
			response.setRoles(
					userDetails.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toList()));
			response.setMessage("Authenticated successfully");
			log.info("Authenticated successfully"+user.toString());
		} else {
			log.error("Authenticated fails due to Not a valid user"+user.toString());
			response.setJwttoken(null);
			response.setMessage("Not a valid user");
		}

		return response;

	}

	private Boolean authenticate(String username, String password) throws Exception {
		try {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					username, password);
			Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authenticate);
			System.out.println("Security: " + SecurityContextHolder.getContext().getAuthentication());
			return true;

		} catch (InternalAuthenticationServiceException iase) {
			log.error("Authenticated fails due to UNAUTHORIZED");
			throw new StoreManagementException("INVALID_CREDENTIALS",HttpStatus.UNAUTHORIZED);
		}

		catch (DisabledException e) {
			log.error("Authenticated fails due to BAD_REQUEST");
			throw new StoreManagementException("USER_DISABLED",HttpStatus.BAD_REQUEST);
		} catch (BadCredentialsException e) {
			log.error("Authenticated fails due to UNAUTHORIZED");
			throw new StoreManagementException("INVALID_CREDENTIALS",HttpStatus.UNAUTHORIZED);
		}
	}
}
