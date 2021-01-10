package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.component.PartidoValidator;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.converter.PartidoConverter;
import org.springframework.samples.petclinic.model.base.BaseVolleyballValidatorTest;
import org.springframework.samples.petclinic.model.ediciones.PartidoEdit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

public class PartidoValidatorTest extends BaseVolleyballValidatorTest {
	
	private PartidoValidator partidoValidator;
	
	private PartidoConverter partidoConverter;
	
	@BeforeEach
	private void setUp() {
		this.partidoValidator = new PartidoValidator(this.partidoService, this.equipoService);
		this.partidoConverter = new PartidoConverter();
	}
	
	@Test
	@Transactional(readOnly = true)
    public void fechaVaciaTest() {
        
        // Obtención de datos correctos
        Partido partidoC = getPartidoCorrecto();
        PartidoEdit partido = partidoConverter.convertPartidoToPartidoEdit(partidoC);
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "fecha";
        String value = "";
        String mensaje = ValidationConstant.VALOR_OBLIGATORIO;
        
        // Modificación del campo
        partido.setFecha(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(partido, "");
        
        // Validar
        partidoValidator.validate(partido, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void fechaErroneaTest() {
        
        // Obtención de datos correctos
        Partido partidoC = getPartidoCorrecto();
        PartidoEdit partido = partidoConverter.convertPartidoToPartidoEdit(partidoC);
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "fecha";
        String value = "40/40/2000";
        String mensaje = ValidationConstant.FECHA_FORMATO_ERRONEO;
        
        // Modificación del campo
        partido.setFecha(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(partido, "");
        
        // Validar
        partidoValidator.validate(partido, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void fechaPasadaTest() {
        
        // Obtención de datos correctos
        Partido partidoC = getPartidoCorrecto();
        PartidoEdit partido = partidoConverter.convertPartidoToPartidoEdit(partidoC);
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "fecha";
        String value = "12/12/2000";
        String mensaje = ValidationConstant.FECHA_ANTERIOR_ERROR;
        
        // Modificación del campo
        partido.setFecha(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(partido, "");
        
        // Validar
        partidoValidator.validate(partido, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void horaVaciaTest() {
        
        // Obtención de datos correctos
        Partido partidoC = getPartidoCorrecto();
        PartidoEdit partido = partidoConverter.convertPartidoToPartidoEdit(partidoC);
        //La fecha se parsea correctamente en el controller, por lo que la arreglamos aquí antes con la fecha del partido traido
        partido.setFecha("06/11/2021");
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "hora";
        String value = "";
        String mensaje = ValidationConstant.VALOR_OBLIGATORIO;
        
        // Modificación del campo
        partido.setHora(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(partido, "");
        
        // Validar
        partidoValidator.validate(partido, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void horaErroneaTest() {
        
        // Obtención de datos correctos
        Partido partidoC = getPartidoCorrecto();
        PartidoEdit partido = partidoConverter.convertPartidoToPartidoEdit(partidoC);
        //La fecha se parsea correctamente en el controller, por lo que la arreglamos aquí antes con la fecha del partido traido
        partido.setFecha("06/11/2021");
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "hora";
        String value = "14-15";
        String mensaje = ValidationConstant.HORA_FORMATO_ERRONEO;
        
        // Modificación del campo
        partido.setHora(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(partido, "");
        
        // Validar
        partidoValidator.validate(partido, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void horaCoincideAnteriorTest() {
        
        // Obtención de datos correctos
        Partido partidoC = getPartidoCorrecto();
        PartidoEdit partido = partidoConverter.convertPartidoToPartidoEdit(partidoC);
        //La fecha se parsea correctamente en el controller, por lo que la arreglamos aquí antes con la fecha del partido traido
        partido.setFecha("07/11/2021");
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "hora";
        String value = "18:15";
        String mensaje = ValidationConstant.HORA_PARTIDO_COINCIDEN_ANTERIOR;
        
        // Modificación del campo
        partido.setHora(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(partido, "");
        
        // Validar
        partidoValidator.validate(partido, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void horaCoincidePosteriorTest() {
        
        // Obtención de datos correctos
        Partido partidoC = getPartidoCorrecto();
        PartidoEdit partido = partidoConverter.convertPartidoToPartidoEdit(partidoC);
        //La fecha se parsea correctamente en el controller, por lo que la arreglamos aquí antes con la fecha del partido traido
        partido.setFecha("07/11/2021");
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "hora";
        String value = "17:15";
        String mensaje = ValidationConstant.HORA_PARTIDO_COINCIDEN_POSTERIOR;
        
        // Modificación del campo
        partido.setHora(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(partido, "");
        
        // Validar
        partidoValidator.validate(partido, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
}
