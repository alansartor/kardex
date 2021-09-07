package com.alan.kardex.security.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.alan.kardex.security.domain.User;
import com.alan.kardex.security.repository.UserDaoImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ApplicationUserDetailsServiceTest {

	@MockBean
	private UserDaoImpl userDao;
	
	@InjectMocks
	private ApplicationUserDetailsService applicationUserDetailsService;
	
	@Test
	public void testLoadUserByUsername() {
		String username = "juan";
		User user = new User();
		user.setUsername(username);
		
		Mockito.when(userDao.find(username)).thenReturn(user);
		
		assertEquals(user, applicationUserDetailsService.loadUserByUsername(username));
	}
	
	@Test
	public void testExeptionThrowOnLoadUserByUsername() {
		String username = "juan";
		
		Mockito.when(userDao.find(username)).thenReturn(null);
		
		Throwable exception = assertThrows(UsernameNotFoundException.class, () -> applicationUserDetailsService.loadUserByUsername(username));
	    assertEquals("Invalid username", exception.getMessage());
	}
}
