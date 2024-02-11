package com.adrmanagement.web.infrastructure.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MemberDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private MemberInfo member;

    public MemberDetails(MemberInfo member) {
        this.member = member;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+member.getPermission().name()));
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getAccount();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public MemberInfo getMember() {
		return member;
	}

	public void setMember(MemberInfo member) {
		this.member = member;
	}

}
