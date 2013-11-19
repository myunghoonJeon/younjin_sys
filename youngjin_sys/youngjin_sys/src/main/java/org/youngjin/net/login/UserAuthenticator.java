package org.youngjin.net.login;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Kten
 * 
 */
@Component
public class UserAuthenticator {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Resource
	private CustomJdbcUserDetailManager customJdbcUserDetailManager;

	/**
	 * @param username
	 *            Id
	 * @return
	 */
	public Authentication loginInUser(String username) {
		UserDetailsYoungjin newPrincipal = (UserDetailsYoungjin) customJdbcUserDetailManager
				.loadUserByUsername(username);
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				newPrincipal, null, newPrincipal.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return authentication;
	}
}
