package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.component.CapitanValidator;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.samples.petclinic.model.base.BaseVolleyballValidatorTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@RunWith(MockitoJUnitRunner.class)
public class CapitanValidatorTest extends BaseVolleyballValidatorTest {
	
	private CapitanValidator capitanValidator;
	
	@BeforeEach
	private void setUp() {
		this.capitanValidator = new CapitanValidator(this.capitanService);
	}
	
	@Test
	@Transactional(readOnly = true)
    public void tiemposMuertosNegativosTest() {
        
        // Obtención de datos correctos
        Capitan capitan = getCapitanCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "Integer";
        String field = "ntiemposmuertos";
        String value = "-1";
        String mensaje = ValidationConstant.CAMPO_NEGATIVO;
        
        // Modificación del campo
        capitan.setNtiemposmuertos(Integer.valueOf(value));

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(capitan, "");
        
        // Validar
        capitanValidator.validate(capitan, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void actitudTest() {
        
        // Obtención de datos correctos
        Capitan capitan = getCapitanCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "Actitud";
        String field = "actitud";
        String value = "asdf";
        String paqueteNoModel = "enumerate";
        String mensaje = ValidationConstant.VALOR_ERROR_ENUM;
        
        // Modificación del campo
        Actitud a = Actitud.fromNombre(value);
        capitan.setActitud(a);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(capitan, "");
        
        // Validar
        capitanValidator.validate(capitan, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, null, mensaje, paqueteNoModel), errors.getFieldError(field).toString());
    }

}
