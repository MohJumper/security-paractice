package com.mypage.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	// -------- these are used so password are not sorted as it is, but are encrypted so in case database is been hacked
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// this for authentication 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		// we will be using userDetailsService NOT inMemory 
		.inMemoryAuthentication()
		.withUser("me@me.com")
		.password("$2a$10$R14D3OIH6037vCNu4Qt5Eu8RsQLLdUae0qQ0rm9PvpOWUYm5QuJse")
		.roles("USER", "ADMIN");
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
				.antMatchers("/admin/**").hasAnyRole("ADMIN")
				// for anything else they need 
				.anyRequest().hasAnyRole("USER").and()
				.formLogin()
//			.formLogin()
				.loginPage("/login")
				// after login they forward to a pages ... in this case >> /dashboard
				.defaultSuccessUrl("/dashboard")
				.permitAll();
	}
}
