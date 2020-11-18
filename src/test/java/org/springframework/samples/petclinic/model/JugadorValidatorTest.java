package org.springframework.samples.petclinic.model;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.component.JugadorValidator;
import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.validation.Errors;

public class JugadorValidatorTest {
	
	
	@Autowired
	private JugadorValidator jugadorValidator;
	
	@Test
	public void testDniNotNull() {
		
		Errors result = null;
		
		Jugador jugador = new Jugador(null,"Calle Alcafran 12","aquinohayquienduerma@gmail.com","Guarroman",LocalDate.of(1997, 8, 19),173,75,Posicion.COLOCADOR,Posicion.OPUESTO);
		jugador.setFirstName("Javier");
		jugador.setLastName("Gutierrez Falcon");
		jugador.setPesoIdeal(70);
		jugador.setImc(22.5);
		jugador.setUser(new User());
		jugador.getUser().setUsername("Superjavi");
		jugador.getUser().setPassword("asdf1234");
		jugador.getUser().setEnabled(true);
		
		jugadorValidator.validate(jugador, result);
		
		assertNotNull(result);
	}

}
