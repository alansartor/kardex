package com.alan.kardex.security;

import org.springframework.security.core.GrantedAuthority;

public enum RolEnum implements GrantedAuthority {
	ROLE_EMPLEADO,
	ROLE_CLIENTE;
	
	@Override
	public String getAuthority() {
		return name();
	}

	public String getWithoutPrefix() {
		return name().substring("ROLE_".length());
	}	
}
