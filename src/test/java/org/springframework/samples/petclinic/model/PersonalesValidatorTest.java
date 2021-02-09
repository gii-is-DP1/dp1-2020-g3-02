package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.component.PersonalesValidator;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.base.BaseVolleyballValidatorTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@RunWith(MockitoJUnitRunner.class)
public class PersonalesValidatorTest extends BaseVolleyballValidatorTest{
	private PersonalesValidator personalesValidator;

	@BeforeEach
	private void setUp() {
		this.personalesValidator = new PersonalesValidator(this.personalesService, this.jugadorService, this.entrenadorService);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void personalesCorrecto() {
		
		// Obtención de datos correctos
        Personales personales = getPersonalesCorrecto();

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(personales, "");
        
        // Validar
        personalesValidator.validate(personales, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(false);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void propietarioTest() {
		// Obtención de datos correctos
		Personales personales = getPersonalesCorrecto();

		// Campo con el valor a validar y mensaje de validación
		String type = "String";
		String field = "propietario";
		String value = "rg";
		String mensaje = ValidationConstant.PROPIETARIO_ERROR;

		// Modificación del campo
		personales.setPropietario(value);

		// Bindear de errores
		Errors errors = new BeanPropertyBindingResult(personales, "");

		// Validar
		personalesValidator.validate(personales, errors);

		assertThat(errors.hasErrors()).isEqualTo(true);
		assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
	}
	

}

