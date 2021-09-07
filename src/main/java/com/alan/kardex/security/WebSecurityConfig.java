package com.alan.kardex.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.alan.kardex.security.service.ApplicationUserDetailsService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	public static String REALM="KARDEX_REALM";
	
    private ApplicationUserDetailsService userDetailsService;

    public WebSecurityConfig(ApplicationUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.antMatchers(HttpMethod.GET, "/items", "/stocks/**").hasAnyRole(RolEnum.ROLE_CLIENTE.getWithoutPrefix(), RolEnum.ROLE_EMPLEADO.getWithoutPrefix())
			.antMatchers(HttpMethod.POST, "/entrada").hasRole(RolEnum.ROLE_EMPLEADO.getWithoutPrefix())
			.antMatchers(HttpMethod.POST, "/salida").hasRole(RolEnum.ROLE_CLIENTE.getWithoutPrefix())
			.anyRequest().authenticated().and()
            .addFilter(new AuthenticationFilter(authenticationManager()))
            .addFilter(new AuthorizationFilter(authenticationManager(), myBasicAuthenticationEntryPoint()))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.httpBasic().realmName(REALM);
		
		http.exceptionHandling()
	    	.authenticationEntryPoint(myBasicAuthenticationEntryPoint())
	    	.accessDeniedHandler(accessDeniedHandler());
	}
	
    @Bean
    public MyBasicAuthenticationEntryPoint myBasicAuthenticationEntryPoint(){
        return new MyBasicAuthenticationEntryPoint();
    }
    
    @Bean
    public MyAccessDeniedHandler accessDeniedHandler(){
        return new MyAccessDeniedHandler();
    }
	
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
    
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
}
