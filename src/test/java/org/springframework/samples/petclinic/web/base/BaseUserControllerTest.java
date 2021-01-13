package org.springframework.samples.petclinic.web.base;

import java.util.HashSet;
import java.util.Set;

import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.User;

public class BaseUserControllerTest extends BaseMockControllerTest {
	
	protected User getUserJugador() {
		User user = new User();
		Authorities authority = new Authorities();
		authority.setId(1);
		authority.setAuthority("jugador");
		authority.setUser(user);
		
		Set<Authorities> authorities = new HashSet<Authorities>();
		authorities.add(authority);
		
		user.setUsername("gonlalle");
		user.setPassword("asdf1234");
		user.setEnabled(true);
		user.setAuthorities(authorities);
		
		return user;
	}
	
	protected User getUserEntrenador() {
		User user = new User();
		Authorities authority = new Authorities();
		authority.setId(2);
		authority.setAuthority("entrenador");
		authority.setUser(user);
		
		Set<Authorities> authorities = new HashSet<Authorities>();
		authorities.add(authority);
		
		user.setUsername("gonlalle2");
		user.setPassword("asdf1234");
		user.setEnabled(true);
		user.setAuthorities(authorities);
		
		return user;
	}
	
	protected User getUserEstadistico() {
		User user = new User();
		Authorities authority = new Authorities();
		authority.setId(3);
		authority.setAuthority("estadistico");
		authority.setUser(user);
		
		Set<Authorities> authorities = new HashSet<Authorities>();
		authorities.add(authority);
		
		user.setUsername("gonlalle3");
		user.setPassword("asdf1234");
		user.setEnabled(true);
		user.setAuthorities(authorities);
		
		return user;
	}

}
