package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.component.JugadorValidator;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.base.BaseVolleyballValidatorTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

public class JugadorValidatorTest extends BaseVolleyballValidatorTest{
	
	@Test
    public void dniVacioTest() {
		// Inicializador del validator
        JugadorValidator jugadorValidator = new JugadorValidator();
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String field = "dni";
        String value = "";
        String mensaje = ValidationConstant.DNI_ERROR;
        
        // Modificación del campo
        jugador.setDni(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
    public void dniIncorrectoTest() {
		// Inicializador del validator
        JugadorValidator jugadorValidator = new JugadorValidator();
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String field = "dni";
        String value = "12345F789";
        String mensaje = ValidationConstant.DNI_ERROR;
        
        // Modificación del campo
        jugador.setDni(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
    public void nombreVacioTest() {
		// Inicializador del validator
        JugadorValidator jugadorValidator = new JugadorValidator();
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
     // Campo con el valor a validar y mensaje de validación
        String field = "firstName";
        String value = "";
        String mensaje = ValidationConstant.FIRSTNAME_ERROR;
        
        // Modificación del campo
        jugador.setFirstName(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
    public void nombreIncorrectoTest() {
		// Inicializador del validator
        JugadorValidator jugadorValidator = new JugadorValidator();
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
     // Campo con el valor a validar y mensaje de validación
        String field = "firstName";
        String value = "a1a2a3";
        String mensaje = ValidationConstant.FIRSTNAME_ERROR;
        
        // Modificación del campo
        jugador.setFirstName(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
    public void apellidoVacioTest() {
		// Inicializador del validator
        JugadorValidator jugadorValidator = new JugadorValidator();
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String field = "lastName";
        String value = "";
        String mensaje = ValidationConstant.LASTNAME_ERROR;
        
        // Modificación del campo
        jugador.setLastName(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
    public void apellidoIncorrectoTest() {
		// Inicializador del validator
        JugadorValidator jugadorValidator = new JugadorValidator();
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String field = "lastName";
        String value = "a1a2a3";
        String mensaje = ValidationConstant.LASTNAME_ERROR;
        
        // Modificación del campo
        jugador.setLastName(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
    public void emailVacioTest() {
		// Inicializador del validator
        JugadorValidator jugadorValidator = new JugadorValidator();
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String field = "email";
        String value = "";
        String mensaje = ValidationConstant.VALOR_OBLIGATORIO;
        
        // Modificación del campo
        jugador.setEmail(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
    public void emailIncorrectoTest() {
		// Inicializador del validator
        JugadorValidator jugadorValidator = new JugadorValidator();
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String field = "email";
        String value = "emailIncorrecto.com";
        String mensaje = ValidationConstant.EMAIL_FORMATO_ERROR;
        
        // Modificación del campo
        jugador.setEmail(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(field, value, mensaje), errors.getFieldError(field).toString());
    }
	
}
