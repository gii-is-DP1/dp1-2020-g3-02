package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.component.LineaMaterialValidator;
import org.springframework.samples.petclinic.model.base.BaseVolleyballValidatorTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

public class LineaMaterialValidatorTest extends BaseVolleyballValidatorTest {
	
private LineaMaterialValidator lineaMaterialValidator;
	
	@BeforeEach
	private void setUp() {
		this.lineaMaterialValidator = new LineaMaterialValidator(this.lineaMaterialService, this.materialService, this.entrenamientoService);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void jugadorCorrecto() {
		
		// Obtención de datos correctos
        LineaMaterial linea = getLineaMaterialCorrecta();

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(linea, "");
        
        // Validar
        lineaMaterialValidator.validate(linea, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(false);
	}
	
	@Test
	@Transactional(readOnly = true)
    public void cantidadNulaOVaciaTest() {
        
        // Obtención de datos correctos
		LineaMaterial linea = getLineaMaterialCorrecta();
        
        // Campo con el valor a validar y mensaje de validación
        String type = "Integer";
        String field = "cantidad";
        String value = "";
        String mensaje = "La cantidad no debe ser nulo ni menor que 0";
        
        // Modificación del campo
        linea.setCantidad(null);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(linea, "");
        
        // Validar
        lineaMaterialValidator.validate(linea, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, null, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void cantidadSePasaTest() {
        
        // Obtención de datos correctos
		LineaMaterial linea = new LineaMaterial(materialService.findById(2).get(), getEntrenamientoCorrecto(), 100);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(linea, "");
        
        // Validar
        lineaMaterialValidator.validate(linea, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
    }
	
	@Test
	@Transactional(readOnly = true)
    public void cantidadCoincidenteTest() {
        
        // Obtención de datos correctos
		LineaMaterial linea = lineaMaterialService.findById(4).get();

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(linea, "");
        
        // Validar
        lineaMaterialValidator.validate(linea, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
    }

}
