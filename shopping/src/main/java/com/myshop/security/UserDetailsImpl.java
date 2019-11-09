package com.myshop.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String password;
	private String name;
	private String email;
	private String address;
	private Date regDate;
	private String authority;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(authority));
		return auth;
	}
	@Override
	public String getUsername() {
		return id;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public boolean isAccountNonExpired() {  // 계정이 만료되지 않았는 지 리턴한다. (true: 만료안됨)
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {  //  계정이 잠겨있지 않았는 지 리턴한다. (true: 잠기지 않음)
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {  // 비밀번호가 만료되지 않았는 지 리턴한다. (true: 만료안됨)
		return true;
	}
	@Override
	public boolean isEnabled() {  // 계정이 활성화(사용가능)인 지 리턴한다. (true: 활성화)
		return true;
	}
	
}
