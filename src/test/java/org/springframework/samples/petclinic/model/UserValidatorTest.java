package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.component.UserValidator;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.converter.UserConverter;
import org.springframework.samples.petclinic.model.auxiliares.UserEdit;
import org.springframework.samples.petclinic.model.base.BaseVolleyballValidatorTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

public class UserValidatorTest extends BaseVolleyballValidatorTest{
	
	private UserValidator userValidator;
	
	private UserConverter userConverter;
	
	@BeforeEach
	private void setUp() {
		this.userValidator = new UserValidator(this.userService);
		this.userConverter = new UserConverter();
	}
	
	@Test
	@Transactional(readOnly = true)
    public void passwordVaciaTest() {
        
        // Obtención de datos correctos
        User userC = getUserCorrecto();
        UserEdit userE = userConverter.convertUserToUserEdit(userC);
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "oldPassword";
        String value = "";
        String mensaje = ValidationConstant.VALOR_OBLIGATORIO;
        
        // Modificación del campo
        userE.setOldPassword(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(userE, "");
        
        // Validar
        userValidator.validate(userE, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void passwordDistintaTest() {
        
        // Obtención de datos correctos
        User userC = getUserCorrecto();
        UserEdit userE = userConverter.convertUserToUserEdit(userC);
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "oldPassword";
        String value = "ElCabesa123";
        String mensaje = ValidationConstant.PASSWORD_ERROR3;
        
        // Modificación del campo
        userE.setOldPassword(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(userE, "");
        
        // Validar
        userValidator.validate(userE, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void passwordPatronTest() {
        
        // Obtención de datos correctos
        User userC = getUserCorrecto();
        UserEdit userE = userConverter.convertUserToUserEdit(userC);
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "newPassword";
        String value = "supermario";
        String mensaje = ValidationConstant.PASSWORD_ERROR;
        
        // Modificación del campo
        userE.setNewPassword(value);
        userE.setConfirmPassword(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(userE, "");
        
        // Validar
        userValidator.validate(userE, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void passwordIgualTest() {
        
        // Obtención de datos correctos
        User userC = getUserCorrecto();
        UserEdit userE = userConverter.convertUserToUserEdit(userC);
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "confirmPassword";
        String value = "Elpepe15";
        String mensaje = ValidationConstant.PASSWORD_ERROR2;
        
        // Modificación del campo
        userE.setNewPassword(value);
        userE.setConfirmPassword(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(userE, "");
        
        // Validar
        userValidator.validate(userE, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }
	
	@Test
	@Transactional(readOnly = true)
    public void passwordConfirmacionTest() {
        
        // Obtención de datos correctos
        User userC = getUserCorrecto();
        UserEdit userE = userConverter.convertUserToUserEdit(userC);
        
        // Campo con el valor a validar y mensaje de validación
        String type = "String";
        String field = "confirmPassword";
        String value = "Elloco99";
        String value2 = "Elloco98";
        String mensaje = ValidationConstant.PASSWORD_ERROR4;
        
        // Modificación del campo
        userE.setNewPassword(value2);
        userE.setConfirmPassword(value);

        // Bindear de errores
        Errors errors = new BeanPropertyBindingResult(userE, "");
        
        // Validar
        userValidator.validate(userE, errors);
        
        assertThat(errors.hasErrors()).isEqualTo(true);
        assertEquals(buildCadenaError(type, field, value, mensaje), errors.getFieldError(field).toString());
    }

}
