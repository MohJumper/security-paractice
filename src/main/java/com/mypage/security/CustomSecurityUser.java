package com.mypage.security;

import org.springframework.security.core.userdetails.UserDetails;


import com.mypage.domain.User;

/*
 * This class work to help implementing spring security (UserDetails) into user. We can simply implement UserDetails in User but it will
 * create problem when we try to move away from spring boot security to another framework.
 * 
 * UserDetails provide the all these override method
 */
public class CustomSecurityUser extends User implements UserDetails{
	
	private static final long serialVersionUID = 5716627476395993850L;

	public CustomSecurityUser() {}
	
	public CustomSecurityUser(User user) {
		this.setAuthorities(user.getAuthorities());
		this.setId(user.getId());
		this.setPassword(user.getPassword());
		this.setUsername(user.getUsername());
	}

	// here we created class called Authority in domain package
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return null;
//	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
