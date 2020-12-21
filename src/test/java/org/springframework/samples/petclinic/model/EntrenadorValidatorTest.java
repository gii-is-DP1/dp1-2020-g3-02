package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.component.EntrenadorValidator;
import org.springframework.samples.petclinic.component.JugadorValidator;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.base.BaseVolleyballValidatorTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@RunWith(MockitoJUnitRunner.class)
public class EntrenadorValidatorTest extends BaseVolleyballValidatorTest{

	private EntrenadorValidator entrenadorValidator;
	
	@BeforeEach
	private void setUp() {
		this.entrenadorValidator = new EntrenadorValidator(this.entrenadorService);
	}
	
	@Test
	@Transactional(readOnly = true)
    public void nombreVacioTest() {
        
        // Obtención de datos correctos
        Entrenador entrenador = getEntrenadorCorrecto();
        
     // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "firstName";
        String value = "";
        String mensaje = ValidationConstant.FIRSTNAME_ERROR;
        
        // Modificación del campo
        entrenador.setFirstName(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(entrenador, "");
        
        // Validar
        entrenadorValidator.validate(entrenador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void nombreIncorrectoTest() {
        
        // Obtención de datos correctos
		Entrenador entrenador= getEntrenadorCorrecto();
        
     	// Campo con el valor a validar y mensaje de validación
		String type = "String";
        String field = "firstName";
        String value = "lopera15";
        String mensaje = ValidationConstant.FIRSTNAME_ERROR;
        
        // Modificación del campo
        entrenador.setFirstName(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(entrenador, "");
        
        // Validar
        entrenadorValidator.validate(entrenador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void apellidoVacioTest() {
        
        // Obtención de datos correctos
		Entrenador entrenador = getEntrenadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "lastName";
        String value = "";
        String mensaje = ValidationConstant.LASTNAME_ERROR;
        
        // Modificación del campo
        entrenador.setLastName(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(entrenador, "");
        
        // Validar
        entrenadorValidator.validate(entrenador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void apellidoIncorrectoTest() {
        
        // Obtención de datos correctos
		Entrenador entrenador = getEntrenadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "lastName";
        String value = "iwata9";
        String mensaje = ValidationConstant.LASTNAME_ERROR;
        
        // Modificación del campo
        entrenador.setLastName(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(entrenador, "");
        
        // Validar
        entrenadorValidator.validate(entrenador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void emailVacioTest() {
        
        // Obtención de datos correctos
		Entrenador entrenador = getEntrenadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "email";
        String value = "";
        String mensaje = ValidationConstant.VALOR_OBLIGATORIO;
        
        // Modificación del campo
        entrenador.setEmail(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(entrenador, "");
        
        // Validar
        entrenadorValidator.validate(entrenador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void emailIncorrectoTest() {
        
        // Obtención de datos correctos
		Entrenador entrenador = getEntrenadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "email";
        String value = "emailIncorrecto.com";
        String mensaje = ValidationConstant.EMAIL_FORMATO_ERROR;
        
        // Modificación del campo
        entrenador.setEmail(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(entrenador, "");
        
        // Validar
        entrenadorValidator.validate(entrenador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void emailYaExisteTest() {
        
        // Obtención de datos correctos
		Entrenador entrenador = getEntrenadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "email";
        String value = "mastodonquijote@gmail.com";
        String mensaje = ValidationConstant.EMAIL_YAEXISTE_ERROR;
        
        // Modificación del campo
        entrenador.setEmail(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(entrenador, "");
        
        // Validar
        entrenadorValidator.validate(entrenador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void fechaVaciaTest() {
        
        // Obtención de datos correctos
		Entrenador entrenador= getEntrenadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        LocalDate value = null;
        
        // Modificación del campo
        entrenador.setFechaNacimiento(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(entrenador, "");
        
        // Validar
        entrenadorValidator.validate(entrenador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
    }
	
	@Test
	@Transactional(readOnly = true)
    public void fechaPosteriorTest() {
        
        // Obtención de datos correctos
        Entrenador entrenador= getEntrenadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        LocalDate value = LocalDate.parse("2077/11/22", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        
        // Modificación del campo
        entrenador.setFechaNacimiento(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(entrenador, "");
        
        // Validar
        entrenadorValidator.validate(entrenador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
    }
	
	
}
