package com.invillia.acme.security;

import com.invillia.acme.domain.UserSecurity;
import com.invillia.acme.usecases.FindUserSecurityUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private FindUserSecurityUseCase findUserSecurityUseCase;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserSecurity userSecurity = findUserSecurityUseCase.findUser(email);
		if (userSecurity == null) throw new UsernameNotFoundException(email);
		return JwtUserFactory.create(userSecurity);
	}

}
