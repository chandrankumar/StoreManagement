package com.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private RequestFilter requestFilter;
	
	@Autowired
	UserDetailsService authService;
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
		}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().
		authorizeRequests().antMatchers("/authenticate/**").permitAll().and()
		.authorizeRequests().antMatchers("/store/**").authenticated().and()
		.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterAfter(requestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
}
