package org.springframework.samples.petclinic.model;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.component.CapitanValidator;
import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.validation.Errors;

public class CapitanValidatorTest {

	@Autowired
	private CapitanValidator capitanValidator;
	
	@Test
	public void testTiemposMuertosNotZero() {
		
		Errors result = null;
		
		Capitan capitan = new Capitan(0,Actitud.POSITIVA);
		
		capitanValidator.validate(capitan, result);
		
		assertNotNull(result);
	}
}
