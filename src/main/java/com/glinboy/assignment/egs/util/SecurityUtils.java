package com.glinboy.assignment.egs.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.glinboy.assignment.egs.model.RoleName;
import com.glinboy.assignment.egs.security.UserPrincipal;

public final class SecurityUtils {

	private SecurityUtils() {
	}

	public static String getCurrentUserLogin() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		String username = null;
		if (authentication != null) {
			if (authentication.getPrincipal() instanceof UserDetails) {
				UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
				username = springSecurityUser.getUsername();
			} else if (authentication.getPrincipal() instanceof String) {
				username = (String) authentication.getPrincipal();
			}
		}
		return username;
	}
	
	public static UserPrincipal getCurrentUserPrincipal() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if(authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
			return (UserPrincipal) authentication.getPrincipal();
		}
		return null;
	}

	public static boolean isCurrentUserInRole(RoleName role) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (authentication != null) {
			if (authentication.getPrincipal() instanceof UserDetails) {
				UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
				return springSecurityUser.getAuthorities().contains(new SimpleGrantedAuthority(role.toString()));
			}
		}
		return false;
	}
}
