package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.component.JugadorValidator;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.base.BaseVolleyballValidatorTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@RunWith(MockitoJUnitRunner.class)
public class JugadorValidatorTest extends BaseVolleyballValidatorTest{
	
	private JugadorValidator jugadorValidator;
	
	@BeforeEach
	private void setUp() {
		this.jugadorValidator = new JugadorValidator(this.jugadorService);
	}
	
	@Test
	@Transactional(readOnly = true)
    public void dniVacioTest() {
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
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
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void dniIncorrectoTest() {
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
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
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void nombreVacioTest() {
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
     // Campo con el valor a validar y mensaje de validación
        String type = "String";
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
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void nombreIncorrectoTest() {
        
        // Obtención de datos correctos
		Jugador jugador = getJugadorCorrecto();
        
     	// Campo con el valor a validar y mensaje de validación
		String type = "String";
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
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void apellidoVacioTest() {
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
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
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void apellidoIncorrectoTest() {
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
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
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void emailVacioTest() {
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
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
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void emailIncorrectoTest() {
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
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
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void emailYaExisteTest() {
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "email";
        String value = "ojitoconeldato@gmail.com";
        String mensaje = ValidationConstant.EMAIL_YAEXISTE_ERROR;
        
        // Modificación del campo
        jugador.setEmail(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void direccionVaciaTest() {
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "direccion";
        String value = "";
        String mensaje = ValidationConstant.DIRECCION_ERROR;
        
        // Modificación del campo
        jugador.setDireccion(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void direccionCortaTest() {
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "direccion";
        String value = "abc";
        String mensaje = ValidationConstant.DIRECCION_ERROR;
        
        // Modificación del campo
        jugador.setDireccion(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void localidadVaciaTest() {
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "localidad";
        String value = "";
        String mensaje = ValidationConstant.LOCALIDAD_ERROR;
        
        // Modificación del campo
        jugador.setLocalidad(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void localidadCortaTest() {
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "localidad";
        String value = "abc";
        String mensaje = ValidationConstant.LOCALIDAD_ERROR;
        
        // Modificación del campo
        jugador.setLocalidad(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void fechaVaciaTest() {
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        LocalDate value = null;
        
        // Modificación del campo
        jugador.setFechaNacimiento(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
    }
	
	@Test
	@Transactional(readOnly = true)
    public void fechaPosteriorTest() {
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        LocalDate value = LocalDate.parse("2041/11/22", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        
        // Modificación del campo
        jugador.setFechaNacimiento(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
    }
	
	@Test
	@Transactional(readOnly = true)
    public void alturaVaciaTest() {
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "Integer";
        String field = "altura";
        String value = null;
        String mensaje = ValidationConstant.VALOR_OBLIGATORIO;
        
        // Modificación del campo
        jugador.setAltura(null);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void alturaIncorrectaTest() {
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "Integer";
        String field = "altura";
        String value = "50";
        String mensaje = ValidationConstant.ALTURA_ERROR;
        
        // Modificación del campo
        jugador.setAltura(Integer.valueOf(value));

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void pesoVacioTest() {
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "Integer";
        String field = "peso";
        String value = null;
        String mensaje = ValidationConstant.VALOR_OBLIGATORIO;
        
        // Modificación del campo
        jugador.setPeso(null);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void pesoIncorrectoTest() {
        
        // Obtención de datos correctos
        Jugador jugador = getJugadorCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "Integer";
        String field = "peso";
        String value = "10";
        String mensaje = ValidationConstant.PESO_ERROR;
        
        // Modificación del campo
        jugador.setPeso(Integer.valueOf(value));

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(jugador, "");
        
        // Validar
        jugadorValidator.validate(jugador, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
}
