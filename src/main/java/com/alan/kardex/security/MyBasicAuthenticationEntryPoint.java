package com.alan.kardex.security;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import com.alan.kardex.config.ExeptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
	Logger logger = LoggerFactory.getLogger(MyBasicAuthenticationEntryPoint.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		response.addHeader("WWW-Authenticate", "Basic realm=\"" + WebSecurityConfig.REALM + "\"");
		response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), new ExeptionResponse("Login Fail", HttpStatus.FORBIDDEN.value(), 
        		authException.getMessage(), "/login", null));
        logger.error("Intento de autentificacion fallido de la Ip: " + request.getRemoteAddr());
	}
	
	@Override
    public void afterPropertiesSet() {
		setRealmName(WebSecurityConfig.REALM);
	}
}