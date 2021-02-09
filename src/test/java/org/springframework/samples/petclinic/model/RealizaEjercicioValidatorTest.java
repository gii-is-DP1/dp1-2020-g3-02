package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.component.RealizaEjercicioValidator;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.converter.RealizaEjercicioConverter;
import org.springframework.samples.petclinic.model.auxiliares.RealizaEjercicioDTO;
import org.springframework.samples.petclinic.model.base.BaseVolleyballValidatorTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@RunWith(MockitoJUnitRunner.class)
public class RealizaEjercicioValidatorTest extends BaseVolleyballValidatorTest{
	
	private RealizaEjercicioValidator realizaEjercicioValidator;

	private RealizaEjercicioConverter realizaEjercicioConverter;
	
	@BeforeEach
	private void setUp() {
		this.realizaEjercicioValidator = new RealizaEjercicioValidator(this.realizaEjercicioService);
		this.realizaEjercicioConverter = new RealizaEjercicioConverter();
	}
	
	
	@Test
	@Transactional(readOnly = true)
	public void realizaEjercicioCorrecto() {
		
		// Obtención de datos correctos
        RealizaEjercicio realizaEjercicio= getRealizaEjercicioCorrecto();
        RealizaEjercicioDTO realizaDTO=realizaEjercicioConverter.converterEntityToDTO(realizaEjercicio);
        
        realizaDTO.setFecha("06/11/2020");
        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(realizaDTO, "");
        
        // Validar
        realizaEjercicioValidator.validate(realizaDTO, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(false);
	}
	
	@Test
	@Transactional(readOnly = true)	
	public void fechaVaciaTest() {
        
        // Obtención de datos correctos
        RealizaEjercicio realizaEjercicio = getRealizaEjercicioCorrecto();
        RealizaEjercicioDTO realizaEjericioDTO = realizaEjercicioConverter.converterEntityToDTO(realizaEjercicio);
        // Campo con el valor a validar y mensaje de validación

        String type = "String";
        String field = "fecha";
        String value = "";
        String mensaje = ValidationConstant.VALOR_OBLIGATORIO;
        //boolean isModel = true;
        //String paquete = "auxiliares";
        
        // Modificación del campo
        realizaEjericioDTO.setFecha(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(realizaEjericioDTO, "");
        
        // Validar
        realizaEjercicioValidator.validate(realizaEjericioDTO, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)	
	public void patternIncumplidoTest() {
        
        // Obtención de datos correctos
        RealizaEjercicio realizaEjercicio = getRealizaEjercicioCorrecto();
        RealizaEjercicioDTO realizaEjericioDTO = realizaEjercicioConverter.converterEntityToDTO(realizaEjercicio);
        // Campo con el valor a validar y mensaje de validación

        String type = "String";
        String field = "fecha";
        String value = "VivaElBetis";
        String mensaje = ValidationConstant.FECHA_FORMATO_ERRONEO;
        //boolean isModel = true;
        //String paquete = "auxiliares";
        
        // Modificación del campo
        realizaEjericioDTO.setFecha(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(realizaEjericioDTO, "");
        
        // Validar
        realizaEjercicioValidator.validate(realizaEjericioDTO, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)	
	public void fechaPosteriorAHoyTest() {
        
        // Obtención de datos correctos
        RealizaEjercicio realizaEjercicio = getRealizaEjercicioCorrecto();
        RealizaEjercicioDTO realizaEjericioDTO = realizaEjercicioConverter.converterEntityToDTO(realizaEjercicio);
        // Campo con el valor a validar y mensaje de validación

        String type = "String";
        String field = "fecha";
        String value = "11/11/2022";
        String mensaje = ValidationConstant.FECHA_POSTERIOR_ERROR;
        //boolean isModel = true;
        //String paquete = "auxiliares";
        
        // Modificación del campo
        realizaEjericioDTO.setFecha(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(realizaEjericioDTO, "");
        
        // Validar
        realizaEjercicioValidator.validate(realizaEjericioDTO, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	

}
