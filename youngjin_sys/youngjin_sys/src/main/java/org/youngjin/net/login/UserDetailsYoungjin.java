package org.youngjin.net.login;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Kten
 * 
 */
public interface UserDetailsYoungjin extends UserDetails {

	/**
	 * @return
	 */
	String getName();

	/**
	 * Authoirty
	 * 
	 * @return
	 */
	Integer getAuth();
	
	/**
	 * Area
	 * @return
	 */
	Integer getArea();
	
	String getAreaStr();
}