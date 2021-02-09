package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.component.PruebaCondicionFisicaValidator;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.enumerate.TipoPrueba;
import org.springframework.samples.petclinic.model.base.BaseVolleyballValidatorTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@RunWith(MockitoJUnitRunner.class)
public class PruebaCondicionFisicaValidatorTest extends BaseVolleyballValidatorTest{
	
	private PruebaCondicionFisicaValidator pruebaValidator;
	
	@BeforeEach
	private void setUp() {
		this.pruebaValidator = new PruebaCondicionFisicaValidator(this.pruebaCondicionFisicaService);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void pruebaCondicionCorrecto() {
		
		// Obtención de datos correctos
        PruebaCondicionFisica pruebaCondicion = getPruebaCondicionFisicaCorrecto();

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(pruebaCondicion, "");
        
        // Validar
        pruebaValidator.validate(pruebaCondicion, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(false);
	}
	
	@Test
	@Transactional(readOnly = true)
    public void fechaNullTest() {
        
        // Obtención de datos correctos
        PruebaCondicionFisica prueba = getPruebaCondicionFisicaCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "LocalDate";
        String field = "fecha";
        String value = null;
        String mensaje = ValidationConstant.VALOR_OBLIGATORIO;
        
        // Modificación del campo
        prueba.setFecha(null);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(prueba, "");
        
        // Validar
        pruebaValidator.validate(prueba, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
    }
	
	@Test
	@Transactional(readOnly = true)	
	public void fechaPosteriorAlDiaDeHoyTest() {
        
        // Obtención de datos correctos
        PruebaCondicionFisica prueba = getPruebaCondicionFisicaCorrecto();
        
        // Campo con el valor a validar y mensaje de validación

        LocalDate value = LocalDate.parse("2041/11/22", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        
        String mensaje = ValidationConstant.FECHA_ANTERIOR_ERROR;
        
        // Modificación del campo
        prueba.setFecha(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(prueba, "");
        
        // Validar
        pruebaValidator.validate(prueba, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
    }
	
	@Test
	@Transactional(readOnly = true)	
	public void datoNuloTest() {
        
        // Obtención de datos correctos
        PruebaCondicionFisica prueba = getPruebaCondicionFisicaCorrecto();
        
        // Campo con el valor a validar y mensaje de validación

        String type = "Double";
        String field = "dato";
        String value = null;
        String mensaje = ValidationConstant.DATO_PRUEBA_ERROR;
       
        
        // Modificación del campo
        prueba.setDato(null);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(prueba, "");
        
        // Validar
        pruebaValidator.validate(prueba, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)	
	public void datoNoPositivoTest() {
        
        // Obtención de datos correctos
        PruebaCondicionFisica prueba = getPruebaCondicionFisicaCorrecto();
        
        // Campo con el valor a validar y mensaje de validación

        String type = "Double";
        String field = "dato";
        String value = "-1.0";
        String mensaje = ValidationConstant.DATO_PRUEBA_ERROR;
       
        
        // Modificación del campo
        prueba.setDato(Double.valueOf(value));

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(prueba, "");
        
        // Validar
        pruebaValidator.validate(prueba, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)	
	public void datoNoEnteroEnAbdominalTest() {
        
        // Obtención de datos correctos
        PruebaCondicionFisica prueba = getPruebaCondicionFisicaCorrecto();
        
        // Campo con el valor a validar y mensaje de validación

        String type = "Double";
        String field = "dato";
        String value = "1.5";
        String mensaje = ValidationConstant.VALOR_NUMERICO_ENTERO_ERROR;
       
        
        // Modificación del campo
        prueba.setTipoPrueba(TipoPrueba.ABDOMINAL);
        prueba.setDato(Double.valueOf(value));

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(prueba, "");
        
        // Validar
        pruebaValidator.validate(prueba, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)	
	public void datoNoEnteroEnPulsacionesTest() {
        
        // Obtención de datos correctos
        PruebaCondicionFisica prueba = getPruebaCondicionFisicaCorrecto();
        
        // Campo con el valor a validar y mensaje de validación

        String type = "Double";
        String field = "dato";
        String value = "1.5";
        String mensaje = ValidationConstant.PULSACIONES_ERROR;
       
        
        // Modificación del campo
        prueba.setTipoPrueba(TipoPrueba.PULSACIONESMINIMAS);
        prueba.setDato(Double.valueOf(value));

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(prueba, "");
        
        // Validar
        pruebaValidator.validate(prueba, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)	
	public void datoNoEntre30Y200PulsacionesTest() {
        
        // Obtención de datos correctos
        PruebaCondicionFisica prueba = getPruebaCondicionFisicaCorrecto();
        
        // Campo con el valor a validar y mensaje de validación

        String type = "Double";
        String field = "dato";
        String value = "5.0";
        String mensaje = ValidationConstant.PULSACIONES_ERROR;
       
        
        // Modificación del campo
        prueba.setTipoPrueba(TipoPrueba.PULSACIONESMINIMAS);
        prueba.setDato(Double.valueOf(value));

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(prueba, "");
        
        // Validar
        pruebaValidator.validate(prueba, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)	
	public void datoMayor50FlexibilidadTest() {
        
        // Obtención de datos correctos
        PruebaCondicionFisica prueba = getPruebaCondicionFisicaCorrecto();
        
        // Campo con el valor a validar y mensaje de validación

        String type = "Double";
        String field = "dato";
        String value = "60.0";
        String mensaje = ValidationConstant.FLEXIBILIDAD_ERROR;
       
        
        // Modificación del campo
        prueba.setTipoPrueba(TipoPrueba.FLEXIBILIDAD);
        prueba.setDato(Double.valueOf(value));

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(prueba, "");
        
        // Validar
        pruebaValidator.validate(prueba, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)	
	public void datoSaltoMenorAlturaJugadorTest() {
        
        // Obtención de datos correctos
        PruebaCondicionFisica prueba = getPruebaCondicionFisicaCorrecto();
        
        // Campo con el valor a validar y mensaje de validación

        String type = "Double";
        String field = "dato";
        String value = "1.0";
        String mensaje = ValidationConstant.SALTOVERTICAL_ERROR;
       
        
        // Modificación del campo
        prueba.setTipoPrueba(TipoPrueba.SALTOVERTICAL);
        prueba.setDato(Double.valueOf(value));

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(prueba, "");
        
        // Validar
        pruebaValidator.validate(prueba, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	

}
