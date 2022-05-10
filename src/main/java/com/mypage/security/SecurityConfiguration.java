package com.mypage.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	// -------- these are used so password are not sorted as it is, but are encrypted so in case database is been hacked
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailsService userDetailsService;
	
//	@Bean(name = "passwordEncoder")
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// this for authentication 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		// we will be using userDetailsService NOT inMemory 
//		.inMemoryAuthentication()
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
//		.withUser("me@me.com")
//		// see unit test this is the encrypted values of a string
//		.password("$2a$10$HjBr/5aXFZyjP86v1Dy2Ke8ua4qkqxNRZ2Y3bddQl/dOR3A05r9wi")
//		.roles("USER", "ADMIN");
	}
	
	// authorization part 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http
			// ----------- why disable? 
				// learn more about csrf and why should not be disabled .... 
			.csrf().disable()
			// the coming in authorization endpoints
			.authorizeRequests()
			// the /** << refers to anything insde of it 
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				// for anything else they need 
				.anyRequest().hasAuthority("USER").and()
				.formLogin()
//			.formLogin()
				.loginPage("/login")
				// after login they forward to a pages ... in this case >> /dashboard
				.defaultSuccessUrl("/dashboard")
				.permitAll();
	}
}
