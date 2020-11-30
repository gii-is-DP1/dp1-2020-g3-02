package org.springframework.samples.petclinic.component;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EntrenadorValidator implements Validator{

	@Autowired
	private EntrenadorService entrenadorService;
	
	@Override
	public void validate(Object target, Errors errors) {
		
		Entrenador entrenador = (Entrenador) target;
		
		//Nombre Validation
		if ( entrenador.getFirstName() == null || entrenador.getFirstName().length() < 3) {
			errors.rejectValue("firstName", "error", ValidationConstant.FIRSTNAME_ERROR);
		}
		
		//Apellido validation
		if ( entrenador.getLastName() == null || entrenador.getLastName().length() < 3) {
			errors.rejectValue("lastName", "error", ValidationConstant.FIRSTNAME_ERROR);
		}
		
		//email validation
		if ( entrenador.getEmail() == null || entrenador.getEmail().length() < 5) {
			errors.rejectValue("email", "El email es requerido y debe tener al menos 5 caracteres","El email es requerido y debe tener al menos 5 caracteres");
		}
				
		//fecha nacimiento validation
		try {
			if (entrenador.getFechaNacimiento() == null || entrenador.getFechaNacimiento().isAfter(LocalDate.now())) {
				errors.rejectValue("fechaNacimiento", "La fecha de nacimiento es requerida y debe ser anterior al día de hoy","La fecha de nacimiento es requerida y debe ser anterior al día de hoy");
			}
		} catch (Exception e) {
			errors.rejectValue("fechaNacimiento", "La fecha debe tener el formato requerido(YYYY/MM/DD)","La fecha debe tener el formato requerido(YYYY/MM/DD)");
		}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Entrenador.class.isAssignableFrom(clazz);
	}
}

///** Versión de serialización. */
//private static final long serialVersionUID = 1L;
//
///** Servicio de usuario. */
//@Autowired
//private UsuarioService usuarioService;
//
//@Override
//public boolean supports(final Class<?> clazz) {
//	return UsuarioDTO.class.equals(clazz);
//}
//
//@Override
//public void validate(final Object target, final Errors errors) {
//	final UsuarioDTO usuario = (UsuarioDTO) target;
//	if (usuario.getId() == null) {
//		// validación contraseña obligatoria en alta
//		ValidationUtils.rejectIfEmpty(errors, "usuario.clave", ConstantesController.COD_MSJ_VAL_OBLIG);
//		// validación usuario único
//		final UsuarioDTO usuarioUnico = usuarioService.findByUsuario(usuario.getUsuario());
//		if (usuarioUnico != null) {
//			final Object[] args = new Object[] { "usuario" };
//			errors.rejectValue("usuario.usuario", ConstantesController.COD_MSJ_VAL_YAEXISTE_MASC, args, null);
//		}
//	}
//	// validación contraseña tamaño
//	if (StringUtils.isNotEmpty(usuario.getClave()) && usuario.getClave().length() < 4) {
//		final Object[] args = new Object[] { "usuario", "64", "4" };
//		errors.rejectValue("usuario.clave", ConstantesController.COD_MSJ_VAL_SIZE, args, null);
//	}
//}
