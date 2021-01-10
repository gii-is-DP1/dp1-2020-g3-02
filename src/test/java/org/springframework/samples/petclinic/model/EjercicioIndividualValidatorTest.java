package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.component.EjercicioIndividualValidator;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.base.BaseVolleyballValidatorTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@RunWith(MockitoJUnitRunner.class)
class EjercicioIndividualValidatorTest extends BaseVolleyballValidatorTest {
	
	private EjercicioIndividualValidator ejercicioIndividualValidator;
	
	@BeforeEach
	private void setUp() {
		this.ejercicioIndividualValidator = new EjercicioIndividualValidator(this.ejercicioIndividualService);
	}

	@Test
	@Transactional(readOnly = true)
	public void descripcionVaciaTest()
	{
		// Obtención de datos correctos
        EjercicioIndividual ejercicio = getEjercicioIndividualCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "descripcion";
        String value = "";
        String mensaje = ValidationConstant.EJERCICIOS_DESCRIPCION_ERROR;
        
        // Modificación del campo
        ejercicio.setDescripcion(value);
        
        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(ejercicio, "");
        
        // Validar
        ejercicioIndividualValidator.validate(ejercicio, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
	}
	
	@Test
	@Transactional(readOnly = true)
	public void descripcionExtensaTest()
	{
		// Obtención de datos correctos
        EjercicioIndividual ejercicio = getEjercicioIndividualCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "descripcion";
        String value = "Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Esta es una descripcion de más de 10000 caracteres Estae";
        String mensaje = ValidationConstant.EJERCICIOS_DESCRIPCION_MUY_EXTENSA;
        
        // Modificación del campo
        ejercicio.setDescripcion(value);
        
        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(ejercicio, "");
        
        // Validar
        ejercicioIndividualValidator.validate(ejercicio, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
	}
	
	@Test
	@Transactional(readOnly = true)
	public void descripcionLimiteExtensionTest()
	{
		// Obtención de datos correctos
        EjercicioIndividual ejercicio = getEjercicioIndividualCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String value = "Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion de exactamente 10.000 caracteres Esta es una descripcion d";
        
        // Modificación del campo
        ejercicio.setDescripcion(value);
        
        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(ejercicio, "");
        
        // Validar
        ejercicioIndividualValidator.validate(ejercicio, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(false);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void nombreVacioTest()
	{
		// Obtención de datos correctos
        EjercicioIndividual ejercicio = getEjercicioIndividualCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "nombre";
        String value = "";
        String mensaje = ValidationConstant.EJERCICIOS_NOMBRE_ERROR;
        
        // Modificación del campo
        ejercicio.setNombre(value);
        
        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(ejercicio, "");
        
        // Validar
        ejercicioIndividualValidator.validate(ejercicio, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
	}
	
	@Test
	@Transactional(readOnly = true)
	public void nombreExtensoTest()
	{
		// Obtención de datos correctos
        EjercicioIndividual ejercicio = getEjercicioIndividualCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "nombre";
        String value = "Esto es un nombre de más de 300 caracteres Esto es un nombre de más de 300 caracteres Esto es un nombre de más de 300 caracteres Esto es un nombre de más de 300 caracteres Esto es un nombre de más de 300 caracteres Esto es un nombre de más de 300 caracteres Esto es un nombre de más de 300 caracterese";
		String mensaje = ValidationConstant.EJERCICIOS_NOMBRE_MUY_EXTENSO;
        
        // Modificación del campo
        ejercicio.setNombre(value);
        
        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(ejercicio, "");
        
        // Validar
        ejercicioIndividualValidator.validate(ejercicio, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
	}
	
	@Test
	@Transactional(readOnly = true)
	public void nombreLimiteExtensionTest()
	{
		// Obtención de datos correctos
        EjercicioIndividual ejercicio = getEjercicioIndividualCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String value = "Esto es un nombre de exactamente 300 caracteres Esto es un nombre de exactamente 300 caracteres Esto es un nombre de exactamente 300 caracteres Esto es un nombre de exactamente 300 caracteres Esto es un nombre de exactamente 300 caracteres Esto es un nombre de exactamente 300 caracteres Esto es un n";
        
        // Modificación del campo
        ejercicio.setNombre(value);
        
        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(ejercicio, "");
        
        // Validar
        ejercicioIndividualValidator.validate(ejercicio, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(false);
	}
	
	@Test
	@Transactional(readOnly = true)
    public void nombreYaExisteTest() {
		
		// Obtención de datos correctos
        EjercicioIndividual ejercicio = getEjercicioIndividualCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "nombre";
        String value = "Recepcion";
        String mensaje = ValidationConstant.EJERCICIOS_NOMBRE_DUPLICADO;
        
        // Modificación del campo
        ejercicio.setNombre(value);
        
        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(ejercicio, "");
        
        // Validar
        ejercicioIndividualValidator.validate(ejercicio, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
		
    }
}
