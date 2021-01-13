package org.springframework.samples.petclinic.web.base;

import java.util.HashSet;
import java.util.Set;

import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.User;

public class BaseUserControllerTest extends BaseConverterMethods {
	
	protected static final Integer USER_JUGADOR_ID = 1;
	protected static final Integer USER_ENTRENADOR_ID = 2;
	protected static final Integer USER_ESTADISTICO_ID = 3;
	
	protected User getUserJugador() {
		User user = new User();
		Authorities authority = new Authorities();
		authority.setId(USER_JUGADOR_ID);
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
		authority.setId(USER_ENTRENADOR_ID);
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
		authority.setId(USER_ESTADISTICO_ID);
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
