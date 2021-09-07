package com.alan.kardex.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    public AuthorizationFilter(AuthenticationManager authManager, MyBasicAuthenticationEntryPoint myBasicAuthenticationEntryPoint) {
        super(authManager, myBasicAuthenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstants.HEADER_NAME);
        if(header == null) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = null;
		try {
	        authentication = authenticate(request);
		} catch (JwtException e) {
			getAuthenticationEntryPoint().commence(request, response, new BadCredentialsException(e.getMessage()));
			return;
		}
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_NAME).replace(SecurityConstants.HEADER_NAME_PREFIX, "");
        if(token != null) {
        	Jws<Claims> claims = Jwts.parserBuilder()
            	.setSigningKey(Keys.hmacShaKeyFor(SecurityConstants.KEY.getBytes()))
            	.build()
            	.parseClaimsJws(token);
            if(claims != null) {
            	String username = claims.getBody().getSubject();
            	if(username == null) {
                	throw new JwtException("Incorrect Payload on JWT token");
                }
                String rol = (String) claims.getBody().get(SecurityConstants.PAYLOAD_ROL);
                if(rol == null) {
                	throw new JwtException("Incorrect Payload on JWT token");
                }
                Date expirtionDate = claims.getBody().getExpiration();
                if(expirtionDate.before(new Date())) {
                	throw new JwtException("JWT token is expired");
                }
                return new UsernamePasswordAuthenticationToken(username, null, Arrays.asList(RolEnum.valueOf(rol)));
            }else{
            	throw new JwtException("JWT Payload is empty");
            }
        }
        throw new JwtException("JWT token not found");
    }
}