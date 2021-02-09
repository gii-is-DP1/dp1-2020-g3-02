package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.component.EntrenamientoValidator;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.converter.EntrenamientoConverter;
import org.springframework.samples.petclinic.model.base.BaseVolleyballValidatorTest;
import org.springframework.samples.petclinic.model.ediciones.EntrenamientoEdit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@RunWith(MockitoJUnitRunner.class)
public class EntrenamientoValidatorTest extends BaseVolleyballValidatorTest{

	private EntrenamientoValidator entrenamientoValidator;
	private EntrenamientoConverter entrenamientoConverter;
	
	@BeforeEach
	private void setUp() {
		this.entrenamientoValidator = new EntrenamientoValidator(this.entrenamientoService, this.equipoService);
		this.entrenamientoConverter= new EntrenamientoConverter();
	}
	
	@Test
	@Transactional(readOnly = true)
    public void entrenamientoTest() {
		
		// Obtención de datos correctos
		Entrenamiento entrenamiento = getEntrenamientoCorrecto();
        EntrenamientoEdit entrenamientoEdit=entrenamientoConverter.convertEntrenamientoToEntrenamientoEdit(entrenamiento);
        entrenamientoEdit.setFecha("12/10/2022");
        entrenamientoEdit.setHora("16:00");
        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(entrenamientoEdit, "");
        
        // Validar
        entrenamientoValidator.validate(entrenamientoEdit, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(false);
    }
	
	@Test
	@Transactional(readOnly = true)
    public void fechaVaciaTest() {
        
        // Obtención de datos correctos
		Entrenamiento entrenamiento= getEntrenamientoCorrecto();
        EntrenamientoEdit entrenamientoE = entrenamientoConverter.convertEntrenamientoToEntrenamientoEdit(entrenamiento);
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "fecha";
        String value = "";
        String mensaje = ValidationConstant.VALOR_OBLIGATORIO;
        
        // Modificación del campo
        entrenamientoE.setFecha(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(entrenamientoE, "");
        
        // Validar
        entrenamientoValidator.validate(entrenamientoE, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void patronMalTest() {
        
        // Obtención de datos correctos
		Entrenamiento entrenamiento= getEntrenamientoCorrecto();
        EntrenamientoEdit entrenamientoE = entrenamientoConverter.convertEntrenamientoToEntrenamientoEdit(entrenamiento);
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "fecha";
        String value = "6/10/2000";
        String mensaje = ValidationConstant.FECHA_FORMATO_ERRONEO;
        
        // Modificación del campo
        entrenamientoE.setFecha(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(entrenamientoE, "");
        
        // Validar
        entrenamientoValidator.validate(entrenamientoE, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void fechaAnteriorTest() {
        
        // Obtención de datos correctos
		Entrenamiento entrenamiento= getEntrenamientoCorrecto();
        EntrenamientoEdit entrenamientoE = entrenamientoConverter.convertEntrenamientoToEntrenamientoEdit(entrenamiento);
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "fecha";
        String value = "06/10/2000";
        String mensaje = ValidationConstant.FECHA_ANTERIOR_ERROR;
        
        // Modificación del campo
        entrenamientoE.setFecha(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(entrenamientoE, "");
        
        // Validar
        entrenamientoValidator.validate(entrenamientoE, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void horaVaciaTest() {
        
        // Obtención de datos correctos
		Entrenamiento entrenamiento= getEntrenamientoCorrecto();
        EntrenamientoEdit entrenamientoE = entrenamientoConverter.convertEntrenamientoToEntrenamientoEdit(entrenamiento);
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "hora";
        String value = "";
        String mensaje = ValidationConstant.VALOR_OBLIGATORIO;
        
        // Modificación del campo
        entrenamientoE.setFecha("20/02/2021");
        entrenamientoE.setHora(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(entrenamientoE, "");
        
        // Validar
        entrenamientoValidator.validate(entrenamientoE, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void horaPatronMalTest() {
        
        // Obtención de datos correctos
		Entrenamiento entrenamiento= getEntrenamientoCorrecto();
        EntrenamientoEdit entrenamientoE = entrenamientoConverter.convertEntrenamientoToEntrenamientoEdit(entrenamiento);
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "hora";
        String value = "4:20";
        String mensaje = ValidationConstant.HORA_FORMATO_ERRONEO;
        
        // Modificación del campo
        entrenamientoE.setFecha("20/02/2021");
        entrenamientoE.setHora(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(entrenamientoE, "");
        
        // Validar
        entrenamientoValidator.validate(entrenamientoE, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void horaCoincideAnteriorTest() {
        
        // Obtención de datos correctos
		Entrenamiento entrenamiento= getEntrenamientoCorrecto();
        EntrenamientoEdit entrenamientoE = entrenamientoConverter.convertEntrenamientoToEntrenamientoEdit(entrenamiento);
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "hora";
        String value = "12:30";
        String mensaje = ValidationConstant.HORA_ENTRENAMIENTO_COINCIDEN_ANTERIOR;
        
        // Modificación del campo
        entrenamientoE.setFecha("07/12/2021");
        entrenamientoE.setHora(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(entrenamientoE, "");
        
        // Validar
        entrenamientoValidator.validate(entrenamientoE, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void horaCoincidePosteriorTest() {
        
        // Obtención de datos correctos
		Entrenamiento entrenamiento= getEntrenamientoCorrecto();
        EntrenamientoEdit entrenamientoE = entrenamientoConverter.convertEntrenamientoToEntrenamientoEdit(entrenamiento);
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "hora";
        String value = "11:30";
        String mensaje = ValidationConstant.HORA_ENTRENAMIENTO_COINCIDEN_POSTERIOR;
        
        // Modificación del campo
        entrenamientoE.setFecha("07/12/2021");
        entrenamientoE.setHora(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(entrenamientoE, "");
        
        // Validar
        entrenamientoValidator.validate(entrenamientoE, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
}
