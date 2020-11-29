package org.springframework.samples.petclinic.model;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.component.EntrenadorValidator;
import org.springframework.validation.Errors;

class EntrenadorValidatorTest {

	@Autowired
	private EntrenadorValidator entrenadorValidator;
	
	@Test
	public void testEmailNotNull() {
		
		Errors result = null;
		
		Entrenador entrenador = new Entrenador(null,LocalDate.of(1997, 8, 19));
		entrenador.setFirstName("Ete");
		entrenador.setLastName("sech");
		entrenador.getUser().setUsername("EteSech");
		entrenador.getUser().setPassword("delocos");
		entrenador.getUser().setEnabled(true);
		
		entrenadorValidator.validate(entrenador, result);
		
		assertNotNull(result);
	}
	
	@Test
	public void testFechaNacimientoNotNull() {
		
		Errors result = null;
		
		Entrenador entrenador = new Entrenador("delocos@gmail.com",null);
		entrenador.setFirstName("Ete");
		entrenador.setLastName("sech");
		entrenador.getUser().setUsername("EteSech");
		entrenador.getUser().setPassword("delocos");
		entrenador.getUser().setEnabled(true);
		
		entrenadorValidator.validate(entrenador, result);
		
		assertNotNull(result);
	}

}
