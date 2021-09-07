package com.alan.kardex.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.alan.kardex.config.ExeptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyAccessDeniedHandler implements AccessDeniedHandler {
	Logger logger = LoggerFactory.getLogger(MyAccessDeniedHandler.class);
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        objectMapper.writeValue(response.getWriter(), new ExeptionResponse("Unauthorized", HttpStatus.UNAUTHORIZED.value(), 
        		accessDeniedException.getMessage(), request.getServletPath(), null));
        logger.error("Intento de acceso no autorizado a "+request.getServletPath()+" desde la Ip: " + request.getRemoteAddr());
	}
}