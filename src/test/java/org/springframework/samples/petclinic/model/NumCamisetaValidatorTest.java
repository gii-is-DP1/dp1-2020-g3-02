package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.component.NumCamisetaValidator;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.base.BaseVolleyballValidatorTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@RunWith(MockitoJUnitRunner.class)
class NumCamisetaValidatorTest extends BaseVolleyballValidatorTest {
	
	private NumCamisetaValidator numCamisetaValidator;
	
	@BeforeEach
	private void setUp() {
		this.numCamisetaValidator = new NumCamisetaValidator(this.numCamisetaService);
	}
	
	@Test
	@Transactional(readOnly = true)
    public void numCamisetaDuplicadoTest() {
        
        // Obtención de datos correctos
        NumCamiseta numCamiseta = getNumCamisetaCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "Integer";
        String field = "numero";
        String value = "13";
        String mensaje = ValidationConstant.NUM_CAMISETA_REPETIDO;
        
        // Modificación del campo
        numCamiseta.setNumero(Integer.valueOf(value));

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(numCamiseta, "");
        
        // Validar
        numCamisetaValidator.validate(numCamiseta, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void numCamisetaMuyBajo() {
        
        // Obtención de datos correctos
        NumCamiseta numCamiseta = getNumCamisetaCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "Integer";
        String field = "numero";
        String value = "0";
        String mensaje = ValidationConstant.NUM_CAMISETA_MENOR_QUE_1;
        
        // Modificación del campo
        numCamiseta.setNumero(Integer.valueOf(value));

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(numCamiseta, "");
        
        // Validar
        numCamisetaValidator.validate(numCamiseta, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void numCamisetaMuyAlto() {
        
        // Obtención de datos correctos
        NumCamiseta numCamiseta = getNumCamisetaCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "Integer";
        String field = "numero";
        String value = "100";
        String mensaje = ValidationConstant.NUM_CAMISETA_MAYOR_QUE_99;
        
        // Modificación del campo
        numCamiseta.setNumero(Integer.valueOf(value));

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(numCamiseta, "");
        
        // Validar
        numCamisetaValidator.validate(numCamiseta, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }

}
