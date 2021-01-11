package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.component.MaterialValidator;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.enumerate.EstadoMaterial;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.samples.petclinic.model.base.BaseVolleyballValidatorTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@RunWith(MockitoJUnitRunner.class)
public class MaterialValidatorTest extends BaseVolleyballValidatorTest{
	private MaterialValidator materialValidator;
	
	@BeforeEach
	private void setUp() {
		this.materialValidator = new MaterialValidator(this.materialService);
	}
	
	@Test
	@Transactional(readOnly = true)
    public void stockNegativoTest() {
        
        // Obtención de datos correctos
        Material material= getMaterialCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "Integer";
        String field = "stock";
        String value = "-1";
        String mensaje = ValidationConstant.CAMPO_NEGATIVO;
        
        // Modificación del campo
        material.setStock(Integer.valueOf(value));

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(material, "");
        
        // Validar
        materialValidator.validate(material, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void estadoTest() {
        
        // Obtención de datos correctos
        Material material = getMaterialCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "EstadoMaterial";
        String field = "estado";
        String value = "asdf";
        String paqueteNoModel = "enumerate";
        String mensaje = ValidationConstant.VALOR_ERROR_ENUM;
        
        // Modificación del campo
        EstadoMaterial a = EstadoMaterial.fromNombre(value);
        material.setEstado(a);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(material, "");
        
        // Validar
        materialValidator.validate(material, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, null, mensaje, paqueteNoModel), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void tipoTest() {
        
        // Obtención de datos correctos
        Material material = getMaterialCorrecto();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "TipoMaterial";
        String field = "tipo";
        String value = "asdf";
        String paqueteNoModel = "enumerate";
        String mensaje = ValidationConstant.VALOR_ERROR_ENUM;
        
        // Modificación del campo
        TipoMaterial a = TipoMaterial.fromNombre(value);
        material.setTipo(a);;

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(material, "");
        
        // Validar
        materialValidator.validate(material, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, null, mensaje, paqueteNoModel), errors.getFieldError(field).toString());
    }

}
