package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.component.SustitucionValidator;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.base.BaseVolleyballValidatorTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@RunWith(MockitoJUnitRunner.class)
public class SustitucionValidatorTest extends BaseVolleyballValidatorTest{
	
	private SustitucionValidator sustitucionValidator;
	
	@BeforeEach
	private void setUp() {
		this.sustitucionValidator = new SustitucionValidator(this.sustitucionService);
	}
	

	
	@Test
	@Transactional(readOnly = true)	
	public void minutoSustitucionNullTest() {
        
        // Obtención de datos correctos
        Sustitucion sustitucion = getSustitucionCorrecto();
        
        // Campo con el valor a validar y mensaje de validación

        String type = "Integer";
        String field = "minutoSustitucion";
        String value = null;
        String mensaje = ValidationConstant.MINUTO_SUSTITUCION_ERROR;
       
        
        // Modificación del campo
        sustitucion.setMinutoSustitucion(null);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(sustitucion, "");
        
        // Validar
        sustitucionValidator.validate(sustitucion, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)	
	public void minutoSustitucionMenor0Test() {
        
        // Obtención de datos correctos
        Sustitucion sustitucion = getSustitucionCorrecto();
        
        // Campo con el valor a validar y mensaje de validación

        String type = "Integer";
        String field = "minutoSustitucion";
        String value = "-1";
        String mensaje = ValidationConstant.MINUTO_SUSTITUCION_ERROR;
       
        
        // Modificación del campo
        sustitucion.setMinutoSustitucion(Integer.valueOf(value));

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(sustitucion, "");
        
        // Validar
        sustitucionValidator.validate(sustitucion, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)	
	public void jugadorEntraNullTest() {
        
        // Obtención de datos correctos
        Sustitucion sustitucion = getSustitucionCorrecto();
        
        // Campo con el valor a validar y mensaje de validación

        String type = "Jugador";
        String field = "jugadorEntra";
        String value = null;
        String mensaje = ValidationConstant.VALOR_OBLIGATORIO;
        boolean isModel = true;
        String paquete = "";
        
        // Modificación del campo
        sustitucion.setJugadorEntra(null);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(sustitucion, "");
        
        // Validar
        sustitucionValidator.validate(sustitucion, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje,isModel,paquete), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)	
	public void jugadorSaleNullTest() {
        
        // Obtención de datos correctos
        Sustitucion sustitucion = getSustitucionCorrecto();
        
        // Campo con el valor a validar y mensaje de validación

        String type = "Jugador";
        String field = "jugadorSale";
        String value = null;
        String mensaje = ValidationConstant.VALOR_OBLIGATORIO;
        boolean isModel = true;
        String paquete = "";
        
        // Modificación del campo
        sustitucion.setJugadorSale(null);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(sustitucion, "");
        
        // Validar
        sustitucionValidator.validate(sustitucion, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje,isModel,paquete), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)	
	public void partidoSustitucionNullTest() {
        
        // Obtención de datos correctos
        Sustitucion sustitucion = getSustitucionCorrecto();
        
        // Campo con el valor a validar y mensaje de validación

        String type = "Partido";
        String field = "partido";
        String value = null;
        String mensaje = ValidationConstant.VALOR_OBLIGATORIO;
        boolean isModel = true;
        String paquete = "";
        
        // Modificación del campo
        sustitucion.setPartido(null);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(sustitucion, "");
        
        // Validar
        sustitucionValidator.validate(sustitucion, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje,isModel,paquete), errors.getFieldError(field).toString());
    }

}
