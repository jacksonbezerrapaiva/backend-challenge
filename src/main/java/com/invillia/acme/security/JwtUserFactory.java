package com.invillia.acme.security;

import com.invillia.acme.domain.RoleSecurity;
import com.invillia.acme.domain.UserSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;


public final class JwtUserFactory {

	public static JwtUser create(UserSecurity user) {
		return new JwtUser(user.getId(), user.getUsername(), user.getEmail(),
				user.getPassword(), mapToGrantedAuthorities(user.getRoleSecurityList()), user.getEnabled(),
				user.getLastPasswordResetDate());
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleSecurity> roleSecurityList) {
		List<GrantedAuthority> gl = new ArrayList<GrantedAuthority>();
		if (roleSecurityList != null && !roleSecurityList.isEmpty()) {
			for (RoleSecurity sc : roleSecurityList) {
				GrantedAuthority ga = new SimpleGrantedAuthority(sc.getRole());
				gl.add(ga);
			}
		}
		return gl;
	}
}
