package com.alan.kardex.security.controller;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;

import com.alan.kardex.security.AuthenticationFilter;
import com.alan.kardex.security.RolEnum;
import com.alan.kardex.security.domain.User;
import com.alan.kardex.security.service.ApplicationUserDetailsService;

@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
	   
	@Autowired
	private MockMvc mockMvc;
    
    @MockBean
    private AuthenticationManager authenticationManager;
    
    @MockBean
    private AuthenticationFilter authenticationFilter;
    
    @MockBean
    private ApplicationUserDetailsService userSrv;
    
	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		User user = new User();
		user.setUsername("empleado");
		user.setPassword("$2a$10$.Boj0h1UiN2qosirdYdNyuNlShb/wv1jZLDZmySVI2ByGa5Y9vMzq");
		user.setEnabled(true);
		user.setRol(RolEnum.ROLE_EMPLEADO);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        
        when(userSrv.loadUserByUsername("empleado")).thenReturn(user);
        when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authenticationFilter.attemptAuthentication(Mockito.any(HttpServletRequest.class), Mockito.any(HttpServletResponse.class))).thenReturn(authentication);
        
        String json = "{\"username\":\"empleado\",\"password\":\"123456\"}";
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
        	.andExpect(jsonPath("$.status").value(HttpStatus.OK.value())).andExpect(jsonPath("$.token").isNotEmpty())
	        .andDo(print())           
	        .andDo(document("login", 
	        	requestFields( 
	        		fieldWithPath("username").description("Username"),
	        		fieldWithPath("password").description("Password")),
	        	responseFields(
	                fieldWithPath("status").description("Siempre 200"),
	                fieldWithPath("token").description("Token de seguridad para consumir los demas metodos, expira dentre de 60 minutos"))));
	}
}
