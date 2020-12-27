package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.component.EquipoValidator;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.base.BaseVolleyballValidatorTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@RunWith(MockitoJUnitRunner.class)
public class EquipoValidatorTest extends BaseVolleyballValidatorTest {
	
	private EquipoValidator equipoValidator;
	
	@BeforeEach
	private void setUp() {
		this.equipoValidator = new EquipoValidator(this.equipoService);
	}
	
	@Test
	@Transactional(readOnly = true)
    public void categoriaVaciaTest() {
        
        // Obtención de datos correctos
        Equipo equipo = getEquipoCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "categoria";
        String value = "";
        String mensaje = ValidationConstant.CATEGORIA_ERROR;
        
        // Modificación del campo
        equipo.setCategoria(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(equipo, "");
        
        // Validar
        equipoValidator.validate(equipo, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void categoriaCortaTest() {
        
        // Obtención de datos correctos
        Equipo equipo = getEquipoCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "categoria";
        String value = "ab";
        String mensaje = ValidationConstant.CATEGORIA_ERROR;
        
        // Modificación del campo
        equipo.setCategoria(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(equipo, "");
        
        // Validar
        equipoValidator.validate(equipo, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void ligaVaciaTest() {
        
        // Obtención de datos correctos
        Equipo equipo = getEquipoCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "liga";
        String value = "";
        String mensaje = ValidationConstant.LIGA_ERROR;
        
        // Modificación del campo
        equipo.setLiga(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(equipo, "");
        
        // Validar
        equipoValidator.validate(equipo, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void ligaCortaTest() {
        
        // Obtención de datos correctos
        Equipo equipo = getEquipoCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "liga";
        String value = "a";
        String mensaje = ValidationConstant.LIGA_ERROR;
        
        // Modificación del campo
        equipo.setLiga(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(equipo, "");
        
        // Validar
        equipoValidator.validate(equipo, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }

}
